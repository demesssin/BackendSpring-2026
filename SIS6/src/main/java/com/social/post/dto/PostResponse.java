package com.social.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Response body containing post information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    @Schema(description = "Unique post ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private String postId;

    @Schema(description = "User ID of the author", example = "user-42")
    private String userId;

    @Schema(description = "Post content", example = "Hello Kafka world!")
    private String content;

    @Schema(description = "List of hashtags", example = "[\"kafka\", \"spring\"]")
    private List<String> hashtags;

    @Schema(description = "Post status", example = "PUBLISHED")
    private String status;

    @Schema(description = "Creation timestamp", example = "2026-03-12T10:30:00")
    private LocalDateTime createdAt;
}
