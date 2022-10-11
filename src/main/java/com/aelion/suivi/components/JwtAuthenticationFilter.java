/**
 * 
 */
package com.aelion.suivi.components;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aelion.suivi.services.UserAuthService;
import com.aelion.suivi.services.exception.JwtTokenMissingException;

import io.jsonwebtoken.io.IOException;




/**
 * @author Aelion
 *
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserAuthService userAuthService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Get Authorization header
		String header = request.getHeader("Authorization");
		
		if (header == null || !header.startsWith("Bearer")) {
			System.out.println(request.getRequestURL().toString());
			throw new JwtTokenMissingException("No JWT token found in the request headers");
		}
		
		String token = header.substring("Bearer".length() + 1);
		
		//this.jwtUtil.validateToken(token);
		
		String userName = this.jwtUtil.getUserName(token);
		
		UserDetails userDetails = this.userAuthService
				.loadUserByUsername(userName);
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails,
				null,
				userDetails.getAuthorities()
			);
		usernamePasswordAuthenticationToken.setDetails(
				new WebAuthenticationDetailsSource()
					.buildDetails(request)
			);
		
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		
		try {
			filterChain.doFilter(request, response);
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
