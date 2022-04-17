package com.epam.service.admin;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.data.repository.UserRepository;
import com.epam.dto.UserPrinciple;
import com.epam.entity.User;

@Service
public class AppUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public AppUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("No user exists with user name " + username));
		return new UserPrinciple(user);
	}

}
