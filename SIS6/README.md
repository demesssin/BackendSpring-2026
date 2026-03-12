# Kafka Social API

Kafka-powered social media backend with Spring Boot, PostgreSQL, Flyway, and Swagger.

## Architecture

Single Spring Boot project with two logical modules:

- **Post Service** (producer) — `POST /posts`, `GET /posts/{postId}`
- **Feed Service** (consumer, group: `feed-group`) — `GET /feed?userId=...`
- **Notification Service** (consumer, group: `notification-group`) — saves notifications to DB

Both consumers independently receive **every** event from the `posts` Kafka topic because they use different consumer group IDs.

## Tech Stack

- Java 17, Spring Boot 3.2.5
- Apache Kafka (KRaft mode, no Zookeeper)
- PostgreSQL 15
- Flyway (schema versioning)
- SpringDoc OpenAPI (Swagger)
- Bean Validation (Jakarta)
- Lombok

## Quick Start (Docker Compose)

```bash
docker-compose up --build
```

This starts PostgreSQL, Kafka (KRaft), and the Spring Boot app on port **8082**.

## Manual Start (docker run)

```bash
# 1. PostgreSQL
docker run -d --name postgres \
  -p 5432:5432 \
  -e POSTGRES_DB=social_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  postgres:15

# 2. Kafka (KRaft mode)
docker run -d --name kafka \
  -p 9092:9092 \
  -e KAFKA_NODE_ID=1 \
  -e KAFKA_PROCESS_ROLES=broker,controller \
  -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@localhost:9093 \
  -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093 \
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
  -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT,CONTROLLER:PLAINTEXT \
  -e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  -e CLUSTER_ID=Mk3OEYBSD34fcwNTJENDLw \
  confluentinc/cp-kafka:7.5.0

# 3. Run Spring Boot
mvn clean install
mvn spring-boot:run
```

## URLs

| URL | Description |
|---|---|
| `http://localhost:8082/swagger-ui.html` | Swagger UI |
| `http://localhost:8082/posts` | Post Service |
| `http://localhost:8082/feed?userId=u1` | Feed Service |

## API Endpoints

### Post Service
| Method | URL | Description |
|---|---|---|
| POST | `/posts` | Create a post (publishes to Kafka) |
| GET | `/posts/{postId}` | Get post by UUID |

### Feed Service
| Method | URL | Description |
|---|---|---|
| GET | `/feed?userId={id}` | Get feed items for a user |

## Flyway Migrations

- `V1__create_posts_table.sql`
- `V2__create_feed_items_table.sql`
- `V3__create_notifications_table.sql`

## Kafka Topics

- `posts` — receives PostCreatedEvent when a post is published

## Verify Kafka Messages

```bash
docker exec -it social-kafka kafka-console-consumer \
  --bootstrap-server localhost:9092 \
  --topic posts --from-beginning
```

## Error Handling

- **400** — validation errors (blank userId, blank/too long content)
- **404** — post not found
- **500** — unexpected server errors
- Feed consumer **skips** events with empty content (logs warning, doesn't crash)
