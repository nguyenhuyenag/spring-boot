package com.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.exception.TokenExpiredException;
import com.response.ApiError;
import com.util.JsonUtils;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	@Autowired
	RedisTemplate<String, String> redis;

	public JWTAuthenticationFilter(RedisTemplate<String, String> redisTemplate, AuthenticationManager am) {
		super(am);
		this.redis = redisTemplate;
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String header) throws TokenExpiredException {
		String jwt = header.replace(TokenHandler.PREFIX, "");
		String username = TokenHandler.getUsername(jwt);
		String jwtRedis = redis.opsForValue().get(username);
		System.out.println(jwtRedis);
		boolean expiration = TokenHandler.isExpiration(jwt);
		if (expiration) {
			LOG.warn("Token expiration");
			throw new TokenExpiredException("Token expiration");
		} else {
			String role = TokenHandler.getRole(jwt);
			if (username != null) {
				return new UsernamePasswordAuthenticationToken(username, null,
						Collections.singleton(new SimpleGrantedAuthority(role)));
			}
		}
		return null;
	}

	/**
	 * Xác thực bằng API bằng JWT
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isEmpty(header) || !header.startsWith(TokenHandler.PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		try {
			SecurityContextHolder.getContext().setAuthentication(getAuthentication(header));
		} catch (TokenExpiredException e) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
			String url = req.getRequestURI();
			String json = JsonUtils.writeAsString(new ApiError(401, "Unauthorized", e.getMessage(), url));
			res.getWriter().write(json);
			// res.flushBuffer();
			return;
		}
		super.doFilterInternal(req, res, chain);
	}

}