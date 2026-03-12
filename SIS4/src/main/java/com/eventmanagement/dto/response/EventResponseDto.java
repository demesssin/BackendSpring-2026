package com.eventmanagement.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponseDto {

    private Long id;
    private String title;
    private String description;
    private String organizerEmail;
    private String location;
    private LocalDateTime eventDate;
    private BigDecimal ticketPrice;
    private Integer minAge;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
