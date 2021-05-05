package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Vocabulary;
import com.service.VocabService;
import com.service.XSSFService;

@RestController
public class ApiController {

	@Autowired
	XSSFService xssfService;

	@Autowired
	private VocabService service;

	@GetMapping("import-data")
	private ResponseEntity<?> importExcel() {
		List<String> list = xssfService.importExcel();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("random-vocab")
	private ResponseEntity<Vocabulary> getRandomVocab(String flag) {
		Vocabulary dict = service.getRandomVocab2(flag);
		return new ResponseEntity<>(dict, HttpStatus.OK);
	}

}
