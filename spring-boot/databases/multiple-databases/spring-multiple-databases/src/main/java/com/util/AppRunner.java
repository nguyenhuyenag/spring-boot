package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.service.JdbcTemplateService;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	JdbcTemplateService service;

	@Override
	public void run(String... args) throws Exception {
		// service.showDataSourceURL();
		service.findAll();
	}

}
