CREATE TABLE feed_items (
    id BIGSERIAL PRIMARY KEY,
    post_id UUID NOT NULL,
    user_id VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    hashtags TEXT,
    received_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
