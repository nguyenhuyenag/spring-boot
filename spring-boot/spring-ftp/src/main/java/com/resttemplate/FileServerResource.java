package com.resttemplate;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileserver")
public class FileServerResource {

	@PostMapping(path = "/singlefileupload/")
	public ResponseEntity<String> processFile(@RequestParam("file") MultipartFile file) throws IOException {

		byte[] bytes = file.getBytes();

		System.out.println("File Name: " + file.getOriginalFilename());
		System.out.println("File Content Type: " + file.getContentType());
		System.out.println("File Content:\n" + new String(bytes));

		return (new ResponseEntity<>("Successful", null, HttpStatus.OK));
	}

	@PostMapping(path = "/multiplefileupload/")
	public ResponseEntity<String> processFile(@RequestParam("files") List<MultipartFile> files) throws IOException {

		for (MultipartFile file : files) {
			byte[] bytes = file.getBytes();

			System.out.println("File Name: " + file.getOriginalFilename());
			System.out.println("File Content Type: " + file.getContentType());
			System.out.println("File Content:\n" + new String(bytes));
		}

		return (new ResponseEntity<>("Successful", null, HttpStatus.OK));
	}

}
