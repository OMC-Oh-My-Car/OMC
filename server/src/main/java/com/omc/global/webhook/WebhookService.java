package com.omc.global.webhook;

import static com.omc.global.webhook.DiscordWebhook.*;

import java.awt.*;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WebhookService {

	@Value("${webhook.discord.url}")
	private String webhookUrl;

	public void initWebhook(String ip, String hostName) throws IOException {
		DiscordWebhook webhook = new DiscordWebhook(webhookUrl);

		webhook.setTts(true);
		webhook.setContent(hostName + " computer " + ip + " 의 서버가 구동되었습니다.");
		webhook.execute();
	}

	public void sendWebhook(String message) throws IOException {
		DiscordWebhook webhook = new DiscordWebhook(webhookUrl);
		webhook.setContent(message);
		webhook.execute();
	}

	public void sendErrorWebhook(Exception e) throws IOException {
		DiscordWebhook webhook = new DiscordWebhook(webhookUrl);
		webhook.addEmbed(makeErrorEmbed(e));
		webhook.setTts(true);
		webhook.execute();
	}

	private EmbedObject makeErrorEmbed(Exception e) throws IOException {
		EmbedObject embed = new EmbedObject();
		embed.setAuthor("Error", null, null);
		embed.setDescription(e.getMessage());
		embed.setColor(Color.RED);
		embed.addField("에러 내용", e.toString(), false);
		embed.addField("에러 발생 위치", e.getStackTrace().toString(), false);

		return embed;
	}

}
