package com.eventmanagement.mapper;

import com.eventmanagement.dto.request.EventRequestDto;
import com.eventmanagement.dto.response.EventResponseDto;
import com.eventmanagement.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toEntity(EventRequestDto dto);

    EventResponseDto toResponseDto(Event entity);

    List<EventResponseDto> toResponseDtoList(List<Event> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EventRequestDto dto, @MappingTarget Event entity);
}
