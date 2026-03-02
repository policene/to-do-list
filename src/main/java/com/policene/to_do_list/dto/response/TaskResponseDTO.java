package com.policene.to_do_list.dto.response;

import com.policene.to_do_list.domain.model.enums.TaskPriority;
import com.policene.to_do_list.domain.model.enums.TaskStatus;

import java.time.Instant;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        Instant createdAt,
        Instant updatedAt,
        boolean active
) {
}
