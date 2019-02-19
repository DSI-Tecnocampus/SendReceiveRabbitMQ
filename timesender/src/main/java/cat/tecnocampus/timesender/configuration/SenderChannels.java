package cat.tecnocampus.timesender.configuration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SenderChannels {

    String TIME_CHANNEL = "senderTimeChannel";

    @Output(TIME_CHANNEL)
    MessageChannel senderTimeChannel();
}
