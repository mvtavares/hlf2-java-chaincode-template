package br.com.tdc.config.properties;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("hyperledger-fabric.gateway")
public class HyperledgerFabricGatewayProperties {

	private boolean discovery = true;
	private boolean enabled = true;
	private Path networkConfig;
	private String walletPath;
	private String mspId;
	private String userMspPath;
	private String userId;
	private String channelName;
	private String contractName;

}
