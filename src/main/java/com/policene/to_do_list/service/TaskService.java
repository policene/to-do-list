package com.policene.to_do_list.service;

import com.policene.to_do_list.domain.model.enums.TaskStatus;
import com.policene.to_do_list.domain.specification.TaskSpecification;
import com.policene.to_do_list.dto.TaskFilterDTO;
import com.policene.to_do_list.dto.TaskRequestDTO;
import com.policene.to_do_list.dto.TaskResponseDTO;
import com.policene.to_do_list.mapper.TaskMapper;
import com.policene.to_do_list.domain.model.Task;
import com.policene.to_do_list.domain.model.enums.TaskPriority;
import com.policene.to_do_list.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponseDTO save (TaskRequestDTO dto) {
       Task task = TaskMapper.toEntity(dto);
       Task saved = taskRepository.save(task);
       return TaskMapper.toDto(saved);
    }

    public Page<TaskResponseDTO> getActiveTasks (Pageable pageable, TaskFilterDTO filter) {

        Specification<Task> specification = TaskSpecification.isActive()
                .and(TaskSpecification.hasStatus(filter.status()))
                .and(TaskSpecification.hasPriority(filter.priority()));

        Page<Task> tasksFound = taskRepository.findAll(specification, pageable);

        return tasksFound.map(TaskMapper::toDto);

    }

}
