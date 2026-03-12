package com.social.event;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Schema(hidden = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreatedEvent {

    private String postId;
    private String userId;
    private String content;
    private List<String> hashtags;
    private LocalDateTime timestamp;
}
