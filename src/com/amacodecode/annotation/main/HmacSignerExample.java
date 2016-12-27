package com.amacodecode.annotation.main;

import com.amacodecode.annotation.ObjectHmacSigner;
import com.amacodecode.annotation.sample.NotificationClassLevel;
import com.amacodecode.annotation.sample.NotificationFieldLevel;

public class HmacSignerExample {

	public static void main(String[] args) throws Exception {
		NotificationClassLevel n = new NotificationClassLevel(1L, "sd", "af");
		String testKey = "zsdgzgfdhdhgfh545754757547457457sdhdhfggjgjgfjgfjgfjgj";
		NotificationFieldLevel b = new NotificationFieldLevel(1L, "sd", "af");
		ObjectHmacSigner s = new ObjectHmacSigner();
		System.out.print(s.generateMac(testKey, ":", n));
	}
}
