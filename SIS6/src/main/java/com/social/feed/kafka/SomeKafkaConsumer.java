package com.social.feed.kafka;

import com.social.event.PostCreatedEvent;
import com.social.feed.entity.SomeItem;
import com.social.feed.repository.SomeItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SomeKafkaConsumer {

    private final SomeItemRepository feedItemRepository;

    @KafkaListener(topics = "posts", groupId = "feed-group")
    public void consumePostCreated(PostCreatedEvent event) {
        if (event.getContent() == null || event.getContent().isBlank()) {
            log.warn("⚠️ Skipping event {} — content is empty", event.getPostId());
            return;
        }

        log.info("📰 Adding post {} by user {} to follower feeds — '{}'",
                event.getPostId(), event.getUserId(), event.getContent());

        String hashtagsStr = event.getHashtags() != null
                ? String.join(",", event.getHashtags())
                : null;

        SomeItem feedItem = SomeItem.builder()
                .postId(UUID.fromString(event.getPostId()))
                .userId(event.getUserId())
                .content(event.getContent())
                .hashtags(hashtagsStr)
                .receivedAt(LocalDateTime.now())
                .build();

        feedItemRepository.save(feedItem);
        log.debug("Feed item saved for postId={}", event.getPostId());
    }
}
