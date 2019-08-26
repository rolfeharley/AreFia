package com.arefia.lamm.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.arefia.lamm.model.linePushMessageObject;
import com.arefia.lamm.service.messagePush;

@Controller
public class websocketPushMessage {
	@Autowired
    messagePush mps;
	
	@MessageMapping("/pushMessage")
	public void pushMessage(linePushMessageObject message) {
		mps.push(message.getSourcer(), message.getMessageType(), message.getFileexts(), message.getMessage(), message.getPusher(), message.getPushfid());
	}
}
