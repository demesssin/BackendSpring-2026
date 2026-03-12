package com.social.notification.kafka;

import com.social.event.PostCreatedEvent;
import com.social.notification.repository.Uved;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UvedKafkaConsumer {

    private final Uved notificationRepository;

    @KafkaListener(topics = "posts", groupId = "notification-group")
    public void consumePostCreated(PostCreatedEvent event) {
        log.info("🔔 Sending push notification to followers of user {} — new post {}",
                event.getUserId(), event.getPostId());

        com.social.notification.entity.Uved notification = com.social.notification.entity.Uved.builder()
                .postId(UUID.fromString(event.getPostId()))
                .userId(event.getUserId())
                .message("New post from " + event.getUserId() + ": " + event.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
        log.debug("Notification saved for postId={}", event.getPostId());
    }
}
