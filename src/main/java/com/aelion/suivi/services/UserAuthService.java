/**
 * 
 */
package com.aelion.suivi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.aelion.suivi.entities.UserEntity;
import com.aelion.suivi.entities.UserRole;
import com.aelion.suivi.helpers.Request;
import com.aelion.suivi.repositories.UserRepository;
import com.aelion.suivi.repositories.UserRoleRepository;

/**
 * @author Aelion
 *
 */
@Service
public class UserAuthService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository roleRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> oUser = this.userRepository.findByUserName(username);
		if(oUser.isEmpty()) {
			throw new UsernameNotFoundException("User was not found!!");
		}
		UserEntity user = oUser.get();
		
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
		Optional<UserEntity> oUser = this.userRepository.findByUserName(request.getUserName());

		if (oUser.isPresent()) {
			throw new RuntimeException("User already exists");
		}
		
		UserEntity user = new UserEntity();
		user.setUserName(request.getUserName());
		user.setUserPass(this.passwordEncoder.encode(request.getUserPass()));
		
		user.setUserRoles(request.getRoles().stream().map(r -> {
			UserRole userRole = new UserRole();
			userRole.setRole(r);
			roleRepository.save(userRole);
			return userRole;
			
			}).collect(Collectors.toSet()));
		
		this.userRepository.save(user);
	}

}
