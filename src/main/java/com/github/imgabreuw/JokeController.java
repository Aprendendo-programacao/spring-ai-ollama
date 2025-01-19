package com.github.imgabreuw;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/joke")
public class JokeController {

    private final ChatClient client;

    public JokeController(ChatClient.Builder client) {
        this.client = client.build();
    }

    @GetMapping
    public String joke() {
        return client
                .prompt()
                .user("Please tell me a dad joke about computers.")
                .call()
                .content();
    }

}
