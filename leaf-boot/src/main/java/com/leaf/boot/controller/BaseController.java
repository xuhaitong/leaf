package com.leaf.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
public class BaseController {

	@GetMapping("/hello")
	public String hello(String name) {
		return name+":hello world";
	}
}
