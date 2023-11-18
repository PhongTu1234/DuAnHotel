package com.poly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poly.entity.Users;
import com.poly.Service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class YourCustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
//
//    private final UserService userService;
//
//    @Autowired
//    public YourCustomUserDetailsService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users user = userService.findById(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        return buildUserDetails(user);
//    }
//
//    private UserDetails buildUserDetails(Users user) {
//        List<SimpleGrantedAuthority> authorities = user.getAuthorities()
//                .stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getRole()))
//                .collect(Collectors.toList());
//
//        return new User(user.getUsername(), user.getPassword(), authorities);
//    }
}
