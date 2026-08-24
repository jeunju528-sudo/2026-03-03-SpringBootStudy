package com.sist.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 매개변수가 있는 생성자
@NoArgsConstructor // 매개변수가 없는 생성자
public class ChatMessage {
	private String sender;
	private String message;
	private String time;
}
