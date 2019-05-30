package com.arefia.lamm.utility;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.arefia.tools.passwordEncrypt;

public class iPasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence rawPassword) {
		return passwordEncrypt.encodingPasswd(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.equals(encodedPassword);
	}
}