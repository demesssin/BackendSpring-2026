package com.social.post.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(hidden = true)
@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false, length = 100)
    private String userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String hashtags;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
