package com.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.util.LoginAttemptService;
import com.util.RequestUtils;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private final Logger LOG = LoggerFactory.getLogger(LoginFailureHandler.class);

//	@Autowired
//	private UserRepository repository;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		System.out.println("[" + this.getClass().getSimpleName() + "] Exception: " + exception.getMessage());
		
		String email = request.getParameter("username");
		
//		Optional<User> opt = repository.findByUsername(email);
//		if (opt.isPresent()) {
//			// exception = handleError(opt.get());
//		}
		
		// Login failed by BadCredentialsException (username or password incorrect)
		if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			LOG.info("IP: {}", RequestUtils.getClientIPAddress(request));
			loginAttemptService.loginFailed(RequestUtils.getClientIPAddress(request));
		}

		super.setDefaultFailureUrl("/login?error=true");
		super.onAuthenticationFailure(request, response, exception);
	}

	// Set exception
//	private AuthenticationException handleError(User user) {
//		if (user == null) {
//			return new UsernameNotFoundException("Username_Not_Found_Exception");
//		}
//		if (user.getEnabled() == 0) {
//			return new DisabledException("Disabled_Exception");
//		}
//		return new BadCredentialsException("Bad_Credentials_Exception");
//	}

}
