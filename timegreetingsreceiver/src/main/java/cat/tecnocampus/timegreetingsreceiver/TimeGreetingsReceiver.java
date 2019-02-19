package cat.tecnocampus.timegreetingsreceiver;

import cat.tecnocampus.timegreetingsreceiver.configuration.ReceiverChannels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(ReceiverChannels.class)
public class TimeGreetingsReceiver {

    private static Logger logger = LoggerFactory.getLogger(TimeGreetingsReceiver.class);

    @StreamListener(ReceiverChannels.TIME_CHANNEL)
    public void timeReceiver(Object payload) {
        logger.info("Time Received: " + payload);
    }

    @StreamListener(ReceiverChannels.GREETINGS_CHANNEL)
    public void greetingsReceiver(Object payload) {
        logger.info("Greetings Received: " + payload);
    }

}
