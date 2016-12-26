package com.amacodecode.annotation;

public interface HmacSigner {
	
	default String generateMac(String macKey, String optionDelimeter, Object object) throws Exception {
		return "This is default. Please Implement this method!";
	}
}
