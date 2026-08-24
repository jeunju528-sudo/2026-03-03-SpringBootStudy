package com.sist.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.vo.ChatMessage;

@Controller
public class ChatController {
	@GetMapping("/")
	public String chat_page() {
		return "chat";
	}
	
	@MessageMapping("/chat.send") // 클라이언트가 전송하는 URL
	@SendTo("/topic/public") // 전체 채팅 : public, 개인 메세지 : private
	public ChatMessage sendMessage(ChatMessage message) {
		message.setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		return message;
	}
	
}
