package com.glinboy.demo.jps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

import com.glinboy.demo.jps.todo.JpsTodoClient;

@AutoConfiguration
@EnableConfigurationProperties(JsonPlaceholderServiceProperties.class)
public class JsonPlaceholderServiceConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(JsonPlaceholderServiceProperties.class);
	private final JsonPlaceholderServiceProperties jpsProperties;
	
	public JsonPlaceholderServiceConfiguration(JsonPlaceholderServiceProperties jpsProperties) {
		this.jpsProperties = jpsProperties;
	}

	@Bean("jsonPlaceholderRestClient")
	RestClient restClient(RestClient.Builder builder) {
		return builder
				.baseUrl(jpsProperties.baseUrl())
				.build();
	}

	@Bean
	JpsTodoClient jpsTodoClient(RestClient restClient) {
		return new JpsTodoClient(restClient);
	}
}
