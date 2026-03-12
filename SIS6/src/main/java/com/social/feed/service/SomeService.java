package com.social.feed.service;

import com.social.feed.dto.SomeItemResponse;
import com.social.feed.entity.SomeItem;
import com.social.feed.repository.SomeItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SomeService {

    private final SomeItemRepository feedItemRepository;

    @Transactional(readOnly = true)
    public List<SomeItemResponse> getFeedByUserId(String userId) {
        log.info("Fetching feed for userId={}", userId);

        List<SomeItem> items = feedItemRepository.findByUserIdOrderByReceivedAtDesc(userId);
        log.debug("Found {} feed items for userId={}", items.size(), userId);

        return items.stream().map(this::toResponse).toList();
    }

    private SomeItemResponse toResponse(SomeItem item) {
        return SomeItemResponse.builder()
                .postId(item.getPostId().toString())
                .userId(item.getUserId())
                .content(item.getContent())
                .hashtags(item.getHashtags() != null
                        ? Arrays.asList(item.getHashtags().split(","))
                        : List.of())
                .receivedAt(item.getReceivedAt())
                .build();
    }
}
