package com.social.feed.controller;

import com.social.feed.dto.SomeItemResponse;
import com.social.feed.service.SomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
@Tag(name = "Feed Service", description = "Retrieve user feed (Kafka consumer with group 'feed-group')")
public class SomeController {

    private final SomeService feedService;

    @Operation(summary = "Get user feed", description = "Returns all feed items for a given user, ordered by most recent first")
    @ApiResponse(responseCode = "200", description = "Feed retrieved successfully")
    @GetMapping
    public ResponseEntity<List<SomeItemResponse>> getFeed(
            @Parameter(description = "User ID to fetch feed for", example = "user-42", required = true)
            @RequestParam String userId) {
        log.info("GET /feed?userId={} - fetching feed", userId);
        return ResponseEntity.ok(feedService.getFeedByUserId(userId));
    }
}
