package com.amacodecode.annotation.sample;

import com.amacodecode.annotation.MacSigned;

import lombok.Data;

@Data
public class NotificationFieldLevel {
	
	@MacSigned
	private Long id;
	
	@MacSigned
	private String type;
	
	@MacSigned
	private String name;

	public NotificationFieldLevel(Long id, String type, String name) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
	}
	
	
}
