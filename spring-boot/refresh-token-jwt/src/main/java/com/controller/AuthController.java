package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.AuthService;

@Controller
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	// @Autowired
	// private UserService userService;

	@Autowired
	HttpServletRequest req;

	public String url() {
		return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
	}

//	@PostMapping("login-handle")
//	private ResponseEntity<?> login(@RequestBody(required = false) LoginRequest login) throws IOException {
//		ErrorResponse error = new ErrorResponse();
//		if (login == null) {
//			error.setError("Required request body is missing");
//			error.setMessage("");
//			error.setPath(req.getRequestURI());
//			return ResponseEntity.ok(error);
//		}
//		User user = userService.findByUsername(login.getUsername());
//		if (user != null) {
//			Date timeDisabled = user.getTimeLoginDisabled();
//			if (timeDisabled != null && timeDisabled.after(new Date())) {
//				// khoa tai khoan
//				// error.setStatus(401);
//				error.setError("Unauthorized");
//				error.setMessage("Your account is disabled!");
//				error.setPath(req.getRequestURI());
//				return ResponseEntity.ok(error);
//			} else {
//				user.setLoginDisabled(0);
//				userService.save(user);
//			}
//		}
//		HttpPost httpPost = new HttpPost(url() + "/auth/login");
//		StringEntity entity = new StringEntity(JsonUtils.toJSON(login));
//		httpPost.setEntity(entity);
//		try (CloseableHttpClient client = HttpClients.createDefault()) {
//			HttpResponse response = client.execute(httpPost);
//			String content = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
//			if (content.contains("token")) {
//				JsonNode jsonNode = JsonUtils.toJsonNode(content);
//				return ResponseEntity.status(HttpStatus.OK).body(jsonNode);
//			}
//			return new ResponseEntity<String>(content, HttpStatus.OK);
//		}
//	}

	@GetMapping("check-token")
	private ResponseEntity<?> checkToken(String token) {
		return ResponseEntity.ok(authService.checkToken(token));
	}

}
