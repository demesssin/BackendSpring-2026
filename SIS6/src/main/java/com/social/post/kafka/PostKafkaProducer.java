package com.social.post.kafka;

import com.social.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostKafkaProducer {

    private final KafkaTemplate<String, PostCreatedEvent> kafkaTemplate;

    public void publishPostCreated(PostCreatedEvent event) {
        log.info("Publishing PostCreatedEvent to topic 'posts': postId={}", event.getPostId());
        kafkaTemplate.send("posts", event.getPostId(), event);
        log.debug("Event published successfully for postId={}", event.getPostId());
    }
}
