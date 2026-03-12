package com.eventmanagement.controller;

import com.eventmanagement.dto.request.EventRequestDto;
import com.eventmanagement.dto.response.EventResponseDto;
import com.eventmanagement.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDto> createEvent(@Valid @RequestBody EventRequestDto requestDto) {
        log.info("POST /api/events - creating event");
        EventResponseDto response = eventService.createEvent(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        log.info("GET /api/events - fetching all events");
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getEventById(@PathVariable Long id) {
        log.info("GET /api/events/{} - fetching event", id);
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long id,
                                                         @Valid @RequestBody EventRequestDto requestDto) {
        log.info("PUT /api/events/{} - updating event", id);
        return ResponseEntity.ok(eventService.updateEvent(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        log.info("DELETE /api/events/{} - deleting event", id);
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
