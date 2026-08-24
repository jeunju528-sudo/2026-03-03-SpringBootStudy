package com.sist.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // STOMP 기반의 웹소켓 기능을 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
	/*
	 * 클라이언트에서 접속 : new SockJS("/ws-chat")
	 * */
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 1. 클라이언트가 서버에 접속할 수 있는 URI 주소 설정
		registry.addEndpoint("/ws-chat")
				.setAllowedOriginPatterns("*") // 모든 클라이언트가 접속이 가능하도록 풀어줌, 실무에서는 특정 도메인만 허용하도록 설정
				.withSockJS();
	}

	// 채널 : 클라이언트가 서버에서 보낸 데이터를 읽어서 출력하는 역할
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		
		// 메세지를 보내는 곳
		registry.setApplicationDestinationPrefixes("/app");
	}
	
}
