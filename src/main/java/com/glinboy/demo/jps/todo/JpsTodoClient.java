package com.glinboy.demo.jps.todo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

public class JpsTodoClient {

	private static final Logger log = LoggerFactory.getLogger(JpsTodoClient.class);
	private final RestClient restClient;

	public JpsTodoClient(RestClient restClient) {
		this.restClient = restClient;
	}

	public List<Todo> findAll() {
		return restClient
				.get()
				.uri("/todos")
				.retrieve()
				.body(new ParameterizedTypeReference<>() {});
	}

	public Todo findById(Integer id) {
		return restClient
				.get()
				.uri("/todos/{id}", id)
				.retrieve()
				.body(Todo.class);
	}

	public Todo create(Todo todo) {
		return restClient
				.post()
				.uri("/todos")
				.body(todo)
				.retrieve()
				.body(Todo.class);
	}

	public Todo update(Integer id, Todo todo) {
		return restClient
				.put()
				.uri("/todos/{id}", id)
				.body(todo)
				.retrieve()
				.body(Todo.class);
	}

	public ResponseEntity<Void> delete(Integer id) {
		return restClient
				.delete()
				.uri("/todos/{id}", id)
				.retrieve()
				.toBodilessEntity();
	}

}
