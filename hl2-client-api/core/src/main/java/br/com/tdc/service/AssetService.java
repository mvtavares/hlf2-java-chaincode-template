package br.com.tdc.service;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tdc.config.properties.HyperledgerFabricGatewayProperties;
import br.com.tdc.model.Asset;
import lombok.extern.java.Log;

@Service
@Log
public class AssetService {
	
	@Autowired
	private HyperledgerFabricGatewayProperties hyperledgerFabricGatewayBuilderProperties;

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	Contract mainContract;

	
	public Asset findByCodigo(final Serializable id) throws Exception {
		byte[] asset = getContract().evaluateTransaction("GetAllAssets", id.toString());
		return objectMapper.readValue(new String(asset, StandardCharsets.UTF_8), Asset.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Asset> findAll() throws Exception {
		byte[] queryAll = getContract().evaluateTransaction("GetAll");
		if (queryAll != null) {
			String ret = new String(queryAll, StandardCharsets.UTF_8);
			if  (ret != null && !ret.isEmpty()) {
				List items = objectMapper.readValue(ret, List.class);
				return items;
			}
		}
		return null;
	}

	public byte[] create(Asset obj) throws ContractException, JsonProcessingException, TimeoutException, InterruptedException {
		String valueJson = objectMapper.writeValueAsString(obj);
		if (hyperledgerFabricGatewayBuilderProperties.isEnabled()) {
			return getContract().createTransaction("CreateAsset").submit(valueJson);
		}
		return null;
	}
	
	public byte[] update(Asset obj) throws ContractException, JsonProcessingException, TimeoutException, InterruptedException {
		String valueJson = objectMapper.writeValueAsString(obj);
		if (hyperledgerFabricGatewayBuilderProperties.isEnabled()) {
			return getContract().createTransaction("UpdateAsset").submit(valueJson);
		}
		return null;
	}
	
	public byte[] delete(String assetID) throws ContractException, JsonProcessingException, TimeoutException, InterruptedException {
		if (hyperledgerFabricGatewayBuilderProperties.isEnabled()) {
			return getContract().createTransaction("DeleteAsset").submit(assetID);
		}
		return null;
	}
	
	
	protected Contract getContract() {
		return mainContract;
	}

}
