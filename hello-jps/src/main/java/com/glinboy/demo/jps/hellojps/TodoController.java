package com.glinboy.demo.jps.hellojps;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glinboy.demo.jps.todo.JpsTodoClient;
import com.glinboy.demo.jps.todo.Todo;

@RestController
@RequestMapping("/todos")
public class TodoController {

	private final JpsTodoClient jpsTodoClient;
	
	public TodoController(JpsTodoClient jpsTodoClient) {
		this.jpsTodoClient = jpsTodoClient;
	}
	
	@GetMapping
	public List<Todo> findAll() {
		return jpsTodoClient.findAll();
	}
}
