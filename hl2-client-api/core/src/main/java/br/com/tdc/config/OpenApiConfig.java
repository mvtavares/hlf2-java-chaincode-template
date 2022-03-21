package br.com.tdc.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Autowired
    private ResourceLoader resourceLoader;

	@Bean
	public OpenAPI customOpenAPI() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:API-desc.md");
        InputStream fileAsStream = resource.getInputStream(); 
		Info info = new Info();
		info.title("TDC - API de Integração com o Blockchain")
			.version("1.0")
		    .description(IOUtils.toString(fileAsStream, StandardCharsets.UTF_8))
			.license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html"))
			.contact(new Contact().name("Marcus Vinicius Tavares").email("mvtavares@gmail.com")
					.url("https://thedevconf.com/"));
		return new OpenAPI().info(info);
		//+ "    [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/a09044034b5c3c1b01a9))")
	}
}
