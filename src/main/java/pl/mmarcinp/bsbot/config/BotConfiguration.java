package pl.mmarcinp.bsbot.config;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mmarcinp.bsbot.listeners.EventListener;

import java.util.List;

@Configuration
public class BotConfiguration {

    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<EventListener<T>> eventListeners) {
        String token = "ODY4MTU4NTA0Mjk1MzU0Mzgw.YPrlnQ.BV1F21UK_gnIGB1UwSHeUc76I0g";
        GatewayDiscordClient client = DiscordClientBuilder.create(token)
                .build()
                .login()
                .block();
        for(EventListener<T> listener : eventListeners) {
            assert client != null;
            client.on(listener.getEventType())
                    .flatMap(listener::execute)
                    .onErrorResume(listener::handleError)
                    .subscribe();
        }
        return client;
    }
}
