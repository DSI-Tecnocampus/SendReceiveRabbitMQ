package cat.tecnocampus.greetingssender.messaging;

import cat.tecnocampus.greetingssender.configuration.GreetingsSenderChannels;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(GreetingsSenderChannels.class)
public class MessageSender {
    private MessageChannel greetingsChannel;

    public MessageSender(GreetingsSenderChannels channels) {
        this.greetingsChannel = channels.senderGreetingsChannel();
    }

    public void sendGreetings(String message) {
        greetingsChannel.send(MessageBuilder.withPayload("Greetings: " + message).build());

    }
}
