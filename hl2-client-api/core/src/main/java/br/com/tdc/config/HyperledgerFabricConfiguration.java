package br.com.tdc.config;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.gateway.Identity;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.tdc.config.properties.HyperledgerFabricGatewayProperties;
import br.com.tdc.utils.FabricMockContract;
import lombok.extern.java.Log;

@EnableConfigurationProperties({ br.com.tdc.config.properties.HyperledgerFabricGatewayProperties.class })
@Configuration()
@Log
public class HyperledgerFabricConfiguration {

	@Autowired
	private HyperledgerFabricGatewayProperties hyperledgerFabricGatewayBuilderProperties;

	@Autowired
	private Wallet wallet;

	@Autowired(required = false)
	private Gateway.Builder gatewayBuilder;

	@Autowired(required = false)
	private Gateway gateway;

	@Bean
	public Wallet wallet() throws IOException {
		return Wallets.newFileSystemWallet(Paths.get(hyperledgerFabricGatewayBuilderProperties.getWalletPath()));
	}

	@Bean
	public Gateway.Builder gatewayBuilderFactory() throws Exception {
		checkIfUserExists();
		if (hyperledgerFabricGatewayBuilderProperties.isEnabled()) {
			Gateway.Builder builder = Gateway.createBuilder();
			builder.discovery(hyperledgerFabricGatewayBuilderProperties.isDiscovery())
					.networkConfig(hyperledgerFabricGatewayBuilderProperties.getNetworkConfig())
					.identity(wallet, hyperledgerFabricGatewayBuilderProperties.getUserId());
			return builder;
		}
		return null;
	}
	
	@Bean
	public Gateway gateway() throws IOException {
		if (gatewayBuilder != null) {
			return gatewayBuilder.connect();
		}
		return null;
	}
	
	@Bean
	public Contract mainContract() throws Exception {
		if (gateway != null) {
			Network net = gateway.getNetwork(hyperledgerFabricGatewayBuilderProperties.getChannelName());
			return net.getContract(hyperledgerFabricGatewayBuilderProperties.getContractName());
		}
		return new FabricMockContract();
	}

	private void checkIfUserExists() throws Exception {
		if (wallet.get(hyperledgerFabricGatewayBuilderProperties.getUserId()) == null) {
			FileReader pemReader = new FileReader(hyperledgerFabricGatewayBuilderProperties.getUserMspPath() + "signcerts/User1@tdc.teste.com.br-cert.pem");
	        X509Certificate certificate = Identities.readX509Certificate(pemReader);
			FileReader pvtKeyReader = new FileReader(hyperledgerFabricGatewayBuilderProperties.getUserMspPath() + "keystore/priv_sk");
	        PrivateKey privateKey = Identities.readPrivateKey(pvtKeyReader);
			Identity user = Identities.newX509Identity(hyperledgerFabricGatewayBuilderProperties.getMspId(), certificate, privateKey);
			wallet.put(hyperledgerFabricGatewayBuilderProperties.getUserId(), user);
			log.info("Successfully imported user into the wallet");
		}
	}


}
