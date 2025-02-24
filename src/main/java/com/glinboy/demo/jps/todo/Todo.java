package com.glinboy.demo.jps.todo;

public record Todo(
		Integer userId,
		Integer id,
		String title,
		Boolean complete
		) {}
