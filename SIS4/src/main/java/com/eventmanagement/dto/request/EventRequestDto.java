package com.eventmanagement.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestDto {

    @NotNull(message = "Title is required")
    @NotBlank(message = "Title must not be blank")
    private String title;

    private String description;

    @NotNull(message = "Organizer email is required")
    @Email(message = "Organizer email must be a valid email address")
    private String organizerEmail;

    private String location;

    @NotNull(message = "Event date is required")
    @Future(message = "Event date must be in the future")
    private LocalDateTime eventDate;

    @NotNull(message = "Ticket price is required")
    @Min(value = 0, message = "Ticket price must be at least 0")
    private BigDecimal ticketPrice;

    @NotNull(message = "Minimum age is required")
    @Min(value = 0, message = "Minimum age must be at least 0")
    private Integer minAge;
}
