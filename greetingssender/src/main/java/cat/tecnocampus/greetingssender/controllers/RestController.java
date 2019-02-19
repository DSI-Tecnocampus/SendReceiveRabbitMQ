package cat.tecnocampus.greetingssender.controllers;

import cat.tecnocampus.greetingssender.messaging.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private MessageSender messageSender;

    @Autowired
    public RestController(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @RequestMapping("/greetings/{name}")
    ResponseEntity<String> hi(@PathVariable String name) {
        String message = "Hello, " + name + "!";

        messageSender.sendGreetings(message);

        return ResponseEntity.ok(message);
    }

}
