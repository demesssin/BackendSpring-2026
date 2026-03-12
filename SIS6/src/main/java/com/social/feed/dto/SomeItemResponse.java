package com.social.feed.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Feed item in user's feed")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SomeItemResponse {

    @Schema(description = "Post UUID", example = "550e8400-e29b-41d4-a716-446655440000")
    private String postId;

    @Schema(description = "Author user ID", example = "user-42")
    private String userId;

    @Schema(description = "Post content", example = "Hello Kafka world!")
    private String content;

    @Schema(description = "Hashtags", example = "[\"kafka\", \"spring\"]")
    private List<String> hashtags;

    @Schema(description = "Received timestamp", example = "2026-03-12T10:30:00")
    private LocalDateTime receivedAt;
}
