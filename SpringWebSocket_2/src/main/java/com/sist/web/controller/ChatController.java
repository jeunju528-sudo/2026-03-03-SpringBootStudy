package com.sist.web.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.vo.ChatMessage;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	
	private final SimpMessagingTemplate messagingTemplate;
	
	// 전체 채팅
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(ChatMessage message) {
		return message;
	}
	
	// 1:1 채팅
	@MessageMapping("/chat.private")
	public void privateMessage(ChatMessage message) {
		System.out.println("sender : " + message.getSender());
		System.out.println("receiver : " + message.getReceiver());
		System.out.println("message : " + message.getMessage());
		// 알림. 실시간 상담 등에 사용
		messagingTemplate.convertAndSend(
				"/queue/private/"+message.getReceiver(), // 받을 사람
				message									 // 메세지
		);
	}
	
	@GetMapping("/chat")
	public String chat_page() {
		return "chat";
	}
	
	
}
