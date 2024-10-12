package com.tc.chatbot.controller;

import jakarta.validation.constraints.NotBlank;

public record RecommendationRequestDto(@NotBlank String question) {
}
