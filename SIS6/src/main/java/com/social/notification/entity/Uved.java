package com.social.notification.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(hidden = true)
@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Uved {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id", nullable = false)
    private UUID postId;

    @Column(name = "user_id", nullable = false, length = 100)
    private String userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
