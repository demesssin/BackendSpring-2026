package com.social.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Schema(description = "Request body for creating a new post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostRequest {

    @Schema(description = "ID of the user creating the post", example = "user-42")
    @NotBlank(message = "userId is required")
    private String userId;

    @Schema(description = "Post content, max 280 characters", example = "Hello Kafka world!")
    @NotBlank(message = "Content is required")
    @Size(max = 280, message = "Content must not exceed 280 characters")
    private String content;

    @Schema(description = "List of hashtags without #", example = "[\"kafka\", \"spring\"]")
    private List<String> hashtags;
}
