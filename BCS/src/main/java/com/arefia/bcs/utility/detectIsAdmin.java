package com.arefia.bcs.utility;

import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class detectIsAdmin {
	public boolean isAdmin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Iterator<? extends GrantedAuthority> auit = auth.getAuthorities().iterator();
		
		while (auit.hasNext()) {
			if(auit.next().toString().equals("ROLE_ADMIN")) {
				return true;
			}
		}

	    return false;
	}
}
