package cat.tecnocampus.timegreetingsreceiver.configuration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ReceiverChannels {
    String TIME_CHANNEL = "receiverTimeChannel";
    String GREETINGS_CHANNEL = "receiverGreetingsChannel";

    @Input(TIME_CHANNEL)
    SubscribableChannel receiverTimeChannel();

    @Input(GREETINGS_CHANNEL)
    SubscribableChannel receiverGreetingsChannel();

}
