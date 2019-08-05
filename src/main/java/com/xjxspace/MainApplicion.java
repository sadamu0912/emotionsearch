package com.xjxspace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//MyBatis 支持
@MapperScan("com.xjxspace.mapper")
@SpringBootApplication
@RestController
public class MainApplicion {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MainApplicion.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello, xujunxia";
	}

}
