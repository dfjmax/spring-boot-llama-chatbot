package com.tc.chatbot.controller;

import jakarta.validation.Valid;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class RecommendationController {

    private final ChatClient chatClient;

    public RecommendationController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:/prompts/movie-recommendation.st")
    private Resource movieRecommendationTemplate;

    @PostMapping("/recommendation-blocking")
    public String getRecommendationBlocking(@RequestBody @Valid RecommendationRequestDto requestDto) {

        final var promptTemplate = new PromptTemplate(movieRecommendationTemplate);
        promptTemplate.add("question", requestDto.question());

        return chatClient.prompt(promptTemplate.create()).call().content();
    }

    @PostMapping("/recommendation-flux")
    public Flux<String> getRecommendationFlux(@RequestBody @Valid RecommendationRequestDto requestDto) {

        final var promptTemplate = new PromptTemplate(movieRecommendationTemplate);
        promptTemplate.add("question", requestDto.question());

        return chatClient.prompt(promptTemplate.create()).stream().content();
    }

}
