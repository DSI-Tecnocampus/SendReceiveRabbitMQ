package cat.tecnocampus.greetingssender.configuration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface GreetingsSenderChannels {

    String GREETINGS_CHANNEL = "senderGreetingsChannel";

    @Output(GREETINGS_CHANNEL)
    MessageChannel senderGreetingsChannel();
}
