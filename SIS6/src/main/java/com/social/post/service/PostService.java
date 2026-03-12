package com.social.post.service;

import com.social.event.PostCreatedEvent;
import com.social.exception.PostNotFoundException;
import com.social.post.dto.CreatePostRequest;
import com.social.post.dto.PostResponse;
import com.social.post.entity.Post;
import com.social.post.kafka.PostKafkaProducer;
import com.social.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostKafkaProducer kafkaProducer;

    @Transactional
    public PostResponse createPost(CreatePostRequest request) {
        log.info("Creating post for userId={}", request.getUserId());

        UUID postId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        String hashtagsStr = request.getHashtags() != null
                ? String.join(",", request.getHashtags())
                : null;

        Post post = Post.builder()
                .id(postId)
                .userId(request.getUserId())
                .content(request.getContent())
                .hashtags(hashtagsStr)
                .status("PUBLISHED")
                .createdAt(now)
                .build();

        postRepository.save(post);
        log.info("Post saved to DB with id={}, status=PUBLISHED", postId);

        PostCreatedEvent event = PostCreatedEvent.builder()
                .postId(postId.toString())
                .userId(request.getUserId())
                .content(request.getContent())
                .hashtags(request.getHashtags())
                .timestamp(now)
                .build();

        kafkaProducer.publishPostCreated(event);

        return toResponse(post);
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(String postId) {
        log.info("Fetching post with id={}", postId);

        UUID uuid;
        try {
            uuid = UUID.fromString(postId);
        } catch (IllegalArgumentException e) {
            throw new PostNotFoundException(postId);
        }

        Post post = postRepository.findById(uuid)
                .orElseThrow(() -> new PostNotFoundException(postId));

        log.debug("Post found: userId={}, content='{}'", post.getUserId(), post.getContent());
        return toResponse(post);
    }

    private PostResponse toResponse(Post post) {
        return PostResponse.builder()
                .postId(post.getId().toString())
                .userId(post.getUserId())
                .content(post.getContent())
                .hashtags(post.getHashtags() != null
                        ? java.util.Arrays.asList(post.getHashtags().split(","))
                        : java.util.List.of())
                .status(post.getStatus())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
