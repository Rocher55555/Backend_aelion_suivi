package com.aelion.suivi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aelion.suivi.helpers.Request;

import com.aelion.suivi.entities.UserRole;
import com.aelion.suivi.entities.UserAuthEntity;
import com.aelion.suivi.repositories.UserAuthRepository;
import com.aelion.suivi.repositories.UserRoleRepository;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserAuthRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserAuthEntity> oUser = this.userRepository.findByUserName(username);
		if (oUser.isEmpty()) {
			throw new UsernameNotFoundException("User was not found");
		}
		
		UserAuthEntity user = oUser.get();
		
		List<UserRole> roles = user.getUserRoles()
				.stream()
				.collect(Collectors.toList());
		
		List<GrantedAuthority> grantedAuthorities = roles
				.stream()
				.map(r -> {
					return new SimpleGrantedAuthority(r.getRole());
				})
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(username, user.getUserPass(), grantedAuthorities);
	}
	
	public void saveUser(Request request) {
		Optional<UserAuthEntity> oUser = this.userRepository.findByUserName(request.getUserName());
		
		if (oUser.isPresent()) {
			throw new RuntimeException("User already exists");
		}
		
		UserAuthEntity user = new UserAuthEntity();
		user.setUserName(request.getUserName());
		user.setUserPass(this.passwordEncoder.encode(request.getUserPass()));
		
		user.setUserRoles(request.getRoles().stream().map(r -> {
			UserRole userRole = new UserRole();
			userRole.setRole(r);
			userRoleRepository.save(userRole);
			return userRole;
			
			}).collect(Collectors.toSet()));
		
		this.userRepository.save(user);
	}

}