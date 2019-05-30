package com.arefia.lamm.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class websocketConnectionInitial {
	@MessageMapping("/websocketConnect")
	public void startWebsocketConnetion() {}
}
