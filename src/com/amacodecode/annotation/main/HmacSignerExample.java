package com.amacodecode.annotation.main;

import com.amacodecode.annotation.ObjectHmacSigner;
import com.amacodecode.annotation.sample.NotificationClassLevel;
import com.amacodecode.annotation.sample.NotificationFieldLevel;

public class HmacSignerExample {

	public static void main(String[] args) throws Exception {
		NotificationClassLevel n = new NotificationClassLevel(1L, "sd", "af");
		String testKey = "38D2ADECE6203DF2DABFA68D87C55CAE0BC030876120E0505B0C775C88323D0F";
		NotificationFieldLevel b = new NotificationFieldLevel(1L, "sd", "af");
		ObjectHmacSigner s = new ObjectHmacSigner();
		System.out.print(s.generateMac(testKey, ":", n));
	}
}
