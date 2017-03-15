package com.gproject.paypal.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PayPalConfig {

	@Value("${PP_USER_SANDBOX}")
	private String username;
	@Value("${PP_PASSWORD_SANDBOX}")
	private String password;
	@Value("${PP_SIGNATURE_SANDBOX}")
	private String signature;
	@Value("${environment}")
	private String environment;


	public Map<String,String> getConfig(){
	    Map config=new HashMap<String,String>();
	    config.put("acct1.UserName", username);
	    config.put("acct1.Password", password);
	    config.put("acct1.Signature", signature);
	    config.put("mode", environment);
	    return config;
		
	}
	
	
}
