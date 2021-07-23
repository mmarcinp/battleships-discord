//package pl.mmarcinp.bsbot;
//
//import discord4j.core.DiscordClient;
//import discord4j.core.GatewayDiscordClient;
//import discord4j.core.event.domain.message.MessageCreateEvent;
//import discord4j.core.object.entity.Message;
//import discord4j.core.object.entity.channel.MessageChannel;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.concurrent.Flow;
//
//import static org.springframework.web.bind.annotation.RequestMethod.GET;
//
////@Controller
////@RequestMapping("/")
//public final class ExampleBot implements Flow.Subscriber {
//    public static final String token = "ODY4MTU4NTA0Mjk1MzU0Mzgw.YPrlnQ.BV1F21UK_gnIGB1UwSHeUc76I0g";
//
////    @RequestMapping(value = "/test", method = GET)
//    public void main() {
//        final DiscordClient client = DiscordClient.create(token);
//        final GatewayDiscordClient gateway = client.login().block();
//
//        gateway.on(MessageCreateEvent.class).subscribe(event -> {
//            final Message message = event.getMessage();
//            if ("!ping".equals(message.getContent())) {
//                final MessageChannel channel = message.getChannel().block();
//                channel.createMessage("Pong!").block();
//            }
//        });
//
//        gateway.onDisconnect().block();
//    }
//}