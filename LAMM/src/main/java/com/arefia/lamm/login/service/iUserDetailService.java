package com.arefia.lamm.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.arefia.tools.passwordEncrypt;
import com.arefia.lamm.login.dao.loginDao;
import com.arefia.lamm.login.entity.loginEntity;

public class iUserDetailService implements UserDetailsService {
	@Autowired
	loginDao loginDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		loginEntity loginUser = loginDao.findLoginVerify(username);
		
		if (loginUser == null) {
			throw new UsernameNotFoundException("The User [" + username + "] Is Not Exist!!");
		}
		
		String passwd = passwordEncrypt.decodingPassword(loginUser.getPasswd());
		String isenable = loginUser.getIsenable();
		String isadmin = loginUser.getIsadmin();
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		if (isadmin.equals("1")) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		
		return new User(username, passwd, isenable.equals("1"), true, true, true, grantedAuthorities);
	}
}
