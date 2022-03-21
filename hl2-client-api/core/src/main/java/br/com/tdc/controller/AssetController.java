package br.com.tdc.controller;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.hyperledger.fabric.gateway.ContractException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tdc.exception.ApiError;
import br.com.tdc.exception.FabricHttpServiceException;
import br.com.tdc.model.Asset;
import br.com.tdc.model.UpdateAsset;
import br.com.tdc.service.AssetService;
import br.com.tdc.utils.FabricUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/tdc")
public class AssetController {
	
    @Autowired
    private ModelMapper modelMapper;

	@Autowired
	AssetService assetService;
	
	@Operation(summary = "Pesquisa o asset por ID", description = "Consultar os dados do asset passando o identificador.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Dados do ativo", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Asset.class)) }),
			@ApiResponse(responseCode = "404", description = "Ativo não encontrado", content = @Content) })
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
	public Asset get(@PathVariable(required = true) String id) {
		try {
			return assetService.findByCodigo(id);
		} catch (ContractException ce) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asset " + id + " não encontrado.");
		} catch (Exception e) {
			throw new FabricHttpServiceException(e);
		}
	}
	

	@Operation(summary = "Consulta todos os assets cadastrados", description = "Listar todos os assets")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Dados do asset", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Asset.class))) }),
			@ApiResponse(responseCode = "404", description = "Asset não encontrado", content = @Content) })
	@RequestMapping(value = { "/" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Asset> getAll() {
		try {
			return assetService.findAll();
		} catch (Exception e) {
			throw new FabricHttpServiceException(e);
		}
	}

	@ResponseBody
	@Operation(summary = "Adiciona um novo asset", description = "Realiza a inclusão de um novo asset.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created", content = @Content),
			@ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content) })
	@RequestMapping(value = { "/" }, method = { RequestMethod.POST }, produces="application/json")
	public ResponseEntity<Object> create(@RequestBody Asset asset) {
		String id = null;
		try {
			byte[] idB = assetService.create(asset);
			id = new String(idB, StandardCharsets.UTF_8);
		} catch (ContractException e) {
			return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST, FabricUtils.getDisplayMessage(e.getMessage()), e));
		} catch (Exception e) {
			throw new FabricHttpServiceException(e);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id)
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@Operation(summary = "Atualiza os dados do asset", description = "Realiza a atualização de um asset.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Created", content = @Content),
			@ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content) })
	@RequestMapping(value = { "/{id}" }, method = { RequestMethod.PUT })
	public ResponseEntity<Object> update(@PathVariable(required = true) String id, @RequestBody UpdateAsset asset) {
		try {
			Asset updateAsset = modelMapper.map(asset, Asset.class);
			updateAsset.setAssetID(id);
			assetService.update(updateAsset);
		} catch (Exception e) {
			throw new FabricHttpServiceException(e);
		}
		return ResponseEntity.ok().build();
	}
	

}
