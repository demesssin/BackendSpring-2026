package com.eventmanagement.service;

import com.eventmanagement.dto.request.EventRequestDto;
import com.eventmanagement.dto.response.EventResponseDto;
import com.eventmanagement.entity.Event;
import com.eventmanagement.exception.EventNotFoundException;
import com.eventmanagement.mapper.EventMapper;
import com.eventmanagement.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Transactional
    public EventResponseDto createEvent(EventRequestDto requestDto) {
        log.info("Creating new event with title: {}", requestDto.getTitle());
        log.debug("Event details: organizer={}, date={}", requestDto.getOrganizerEmail(), requestDto.getEventDate());

        Event event = eventMapper.toEntity(requestDto);
        Event saved = eventRepository.save(event);

        log.info("Event created successfully with id: {}", saved.getId());
        return eventMapper.toResponseDto(saved);
    }

    @Transactional(readOnly = true)
    public List<EventResponseDto> getAllEvents() {
        log.info("Fetching all events");

        List<Event> events = eventRepository.findAll();

        log.debug("Found {} events", events.size());
        return eventMapper.toResponseDtoList(events);
    }

    @Transactional(readOnly = true)
    public EventResponseDto getEventById(Long id) {
        log.info("Fetching event with id: {}", id);

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        log.debug("Event found: {}", event.getTitle());
        return eventMapper.toResponseDto(event);
    }

    @Transactional
    public EventResponseDto updateEvent(Long id, EventRequestDto requestDto) {
        log.info("Updating event with id: {}", id);

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        eventMapper.updateEntityFromDto(requestDto, event);
        Event updated = eventRepository.save(event);

        log.info("Event updated successfully with id: {}", updated.getId());
        return eventMapper.toResponseDto(updated);
    }

    @Transactional
    public void deleteEvent(Long id) {
        log.info("Deleting event with id: {}", id);

        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException(id);
        }

        eventRepository.deleteById(id);
        log.info("Event deleted successfully with id: {}", id);
    }
}
