package com.social.post.controller;

import com.social.exception.ErrorResponse;
import com.social.post.dto.CreatePostRequest;
import com.social.post.dto.PostResponse;
import com.social.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Tag(name = "Post Service", description = "Create and retrieve posts (Kafka producer)")
public class PostController {

    private final PostService postService;

    @Operation(summary = "Publish a new post", description = "Creates a post, saves to DB, and publishes PostCreatedEvent to Kafka topic 'posts'")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Post published successfully",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation error - userId blank, content blank or > 280 chars",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody CreatePostRequest request) {
        log.info("POST /posts - publishing new post");
        PostResponse response = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get post by ID", description = "Returns a single post by its UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Post found",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(responseCode = "404", description = "Post not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(
            @Parameter(description = "UUID of the post", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable String postId) {
        log.info("GET /posts/{} - fetching post", postId);
        return ResponseEntity.ok(postService.getPostById(postId));
    }
}
