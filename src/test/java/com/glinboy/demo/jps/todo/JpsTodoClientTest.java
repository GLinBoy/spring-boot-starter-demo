package com.glinboy.demo.jps.todo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import com.glinboy.demo.jps.JsonPlaceholderServiceConfiguration;
import com.glinboy.demo.jps.JsonPlaceholderServiceProperties;

class JpsTodoClientTest {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(
					JsonPlaceholderServiceConfiguration.class,
					RestClientAutoConfiguration.class
					));

	@Test
	void shouldContainTodoRestClientBean() {
		contextRunner.run(ctx -> {
			assertTrue(ctx.containsBean("jsonPlaceholderRestClient"));
			assertTrue(ctx.containsBean("jpsTodoClient"));
		});
	}

	@Test
	void shouldContainDefaultBaseUrl() {
		contextRunner.run((context) -> {
			assertThat(context).hasSingleBean(JsonPlaceholderServiceProperties.class);
			assertThat(context.getBean(JsonPlaceholderServiceProperties.class).baseUrl())
					.isEqualTo("https://jsonplaceholder.typicode.com");
		});
	}

	@Test
	void shouldSetCustomBaseUrl() {
		contextRunner.withPropertyValues("json-placeholder-service.base-url=https://localhost:3000")
		.run((context) -> {
			assertThat(context).hasSingleBean(JsonPlaceholderServiceProperties.class);
			assertThat(context.getBean(JsonPlaceholderServiceProperties.class).baseUrl())
			.isEqualTo("https://localhost:3000");
		});
	}

	@Test
	void shouldFindAllTodos() {
		contextRunner.run((context) -> {
			JpsTodoClient todoClient = context.getBean(JpsTodoClient.class);
			assertEquals(200, todoClient.findAll().size());
		});
	}

}
