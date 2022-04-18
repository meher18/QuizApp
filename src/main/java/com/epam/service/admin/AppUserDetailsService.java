package com.epam.service.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.data.repository.UserRepository;
import com.epam.dto.UserPrinciple;
import com.epam.entity.User;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserPrinciple loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByUserName(username);

		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

		return user.map(UserPrinciple::new).get();

	}

}
