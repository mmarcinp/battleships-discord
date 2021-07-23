package pl.mmarcinp.bsbot.listeners;

import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mmarcinp.bsbot.game.Board;
import pl.mmarcinp.bsbot.game.Xy;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class MessageListener {
    @Autowired
    Board board;

    public Mono<Void> processCommand(Message eventMessage) {
        Map<String, Function<String, String>> keyWordToFunction = new HashMap<>();
        keyWordToFunction.put("bbhelp", this::displayHelp);
//        keyWordToFunction.put("bb start", );
        keyWordToFunction.put("bbshoot", this::shoot);
//        keyWordToFunction.put("bb surrender", );
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> keyWordToFunction.containsKey(message.getContent().toLowerCase().replaceAll("[^a-zA-Z]", "")))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage(keyWordToFunction.get(eventMessage.getContent().toLowerCase().replaceAll("[^a-zA-Z]", "")).apply(eventMessage.getContent().toLowerCase())))
                .then();
//        bb help, bb start, bb shoot , bb surrender
    }

    private String displayHelp(String value) {
        return "This is help:\n" +
                this.displayBoard(value);
    }

    private String displayBoard(String value) {
        return board.display();
    }

    private String shoot(String value) {
        Xy xy;
        if(value.contains(", ")) {
            xy = new Xy(value.split("\\s*,\\s*"));
        } else {
            xy = new Xy(value.split("\\s*,"));
        }
        board.shoot(xy);
        return board.display();
    }
}
