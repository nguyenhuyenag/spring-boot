package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.FileStore;
import com.entity.MultiFile;
import com.entity.MyFile;
import com.service.FileStoreService;
import com.util.Base64Utils;
import com.util.MediaTypeUtils;

@Controller
@RequestMapping("ftp")
public class FTPController {

	private static final String DEFAULT_ID = "J9VWJBPIJKQCMFY4F8UM";

	@Autowired
	FileStoreService fileStoreService;

	@RequestMapping("download")
	public ResponseEntity<?> download(@RequestParam(defaultValue = DEFAULT_ID) String fileid) {
		FileStore file = fileStoreService.findByFileId(fileid);
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(file.getFileName());
		// System.out.println("mediaType: " + mediaType);
		// System.out.println("fileName: " + file.getFileName());
		String fileContent = file.getFileContent();
		byte[] data = Base64Utils.decodeToByte(fileContent);
		return ResponseEntity.ok() //
				.contentType(mediaType) //
				.contentLength(data.length) //
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName()) //
				.body(new ByteArrayResource(data));
	}

	@GetMapping("upload")
	public String upload() {
		return "upload";
	}

	@PostMapping("upload")
	public String upload(MyFile myFile) {
		try {
			MultipartFile multipartFile = myFile.getMultipartFile();
			String fileName = multipartFile.getOriginalFilename();
			FileStore fileStore = new FileStore();
			fileStore.setFileName(fileName);
			String content = Base64Utils.encodeToString(multipartFile.getBytes());
			fileStore.setFileContent(content);
			fileStoreService.save(fileStore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "upload";
	}

	@GetMapping("multi-upload")
	public String multiUpload() {
		return "multi-upload";
	}

	@PostMapping("multi-upload")
	public String multiUpload(MultiFile myFile) {
		try {
			MultipartFile[] multipartFiles = myFile.getMultipartFile();
			for (MultipartFile multipartFile : multipartFiles) {
				String fileName = multipartFile.getOriginalFilename();
				FileStore fileStore = new FileStore();
				fileStore.setFileName(fileName);
				String content = Base64Utils.encodeToString(multipartFile.getBytes());
				fileStore.setFileContent(content);
				fileStoreService.save(fileStore);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "multi-upload";
	}

}
