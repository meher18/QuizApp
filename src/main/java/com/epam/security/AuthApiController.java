package com.epam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.service.admin.AppUserDetailsService;

@RestController
public class AuthApiController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private AppUserDetailsService userDetailsService;

	@PostMapping(value = "/users")
	public ResponseEntity<?> createAuthenticiationToken(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username OR password");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

		String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
