package com.policene.to_do_list.mapper;

import com.policene.to_do_list.dto.TaskRequestDTO;
import com.policene.to_do_list.dto.TaskResponseDTO;
import com.policene.to_do_list.domain.model.Task;
import com.policene.to_do_list.domain.model.enums.TaskStatus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskMapper {

   public static Task toEntity (TaskRequestDTO dto) {
        return Task
                .builder()
                .id(null)
                .title(dto.title())
                .description(dto.description())
                .status(TaskStatus.PENDING)
                .priority(dto.priority())
                .active(true)
                .build();
    }

    public static TaskResponseDTO toDto (Task entity) {
       return new TaskResponseDTO(
               entity.getId(),
               entity.getTitle(),
               entity.getDescription(),
               entity.getStatus(),
               entity.getPriority(),
               entity.getCreatedAt(),
               entity.getUpdatedAt(),
               entity.isActive()
       );
    }

}
