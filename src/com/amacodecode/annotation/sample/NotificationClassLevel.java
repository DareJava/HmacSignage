package com.amacodecode.annotation.sample;
import com.amacodecode.annotation.MacSigned;

import lombok.Data;

@MacSigned
@Data
public class NotificationClassLevel {
	private Long id;
	private String type;
	private String name;
	
	public NotificationClassLevel(Long id, String type, String name) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
	}
	
	
}
