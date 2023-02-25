package com.omc.global.webhook;

import java.io.IOException;
import java.net.InetAddress;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ServerDeployChecker {

	private final WebhookService webhookService;

	@EventListener(ApplicationReadyEvent.class)
	public void initData() throws IOException {
		String ip = InetAddress.getLocalHost().getHostAddress();
		String hostName = InetAddress.getLocalHost().getHostName();

		webhookService.initWebhook(ip, hostName);
	}
}
