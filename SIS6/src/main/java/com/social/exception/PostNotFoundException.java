package com.social.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String postId) {
        super("Post not found with id: " + postId);
    }
}
