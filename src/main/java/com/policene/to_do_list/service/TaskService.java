package com.policene.to_do_list.service;

import com.policene.to_do_list.domain.model.enums.TaskStatus;
import com.policene.to_do_list.domain.specification.TaskSpecification;
import com.policene.to_do_list.dto.request.TaskFilterDTO;
import com.policene.to_do_list.dto.request.TaskCreateDTO;
import com.policene.to_do_list.dto.request.TaskUpdateDTO;
import com.policene.to_do_list.dto.response.TaskResponseDTO;
import com.policene.to_do_list.dto.request.TaskUpdateStatusDTO;
import com.policene.to_do_list.exception.InvalidStatusTransitionException;
import com.policene.to_do_list.exception.TaskNotFoundException;
import com.policene.to_do_list.mapper.TaskMapper;
import com.policene.to_do_list.domain.model.Task;
import com.policene.to_do_list.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponseDTO save (TaskCreateDTO dto) {
       Task task = TaskMapper.toEntity(dto);
       Task saved = taskRepository.save(task);
       return TaskMapper.toDto(saved);
    }

    public TaskResponseDTO update (Long id, TaskUpdateDTO request) {
        Task found = findTaskOrThrowException(id);

        validateTransaction(found.getStatus(), request.status());
        found.setTitle(request.title());
        found.setDescription(request.description());
        found.setPriority(request.priority());
        found.setStatus(request.status());

        taskRepository.save(found);
        return TaskMapper.toDto(found);
    }

    public TaskResponseDTO updateStatus (Long id, TaskUpdateStatusDTO request) {
        Task found = findTaskOrThrowException(id);

        validateTransaction(found.getStatus(), request.status());
        found.setStatus(request.status());

        taskRepository.save(found);
        return TaskMapper.toDto(found);
    }

    public Page<TaskResponseDTO> getActiveTasks (Pageable pageable, TaskFilterDTO filter) {
        Specification<Task> specification = TaskSpecification.isActive()
                .and(TaskSpecification.hasStatus(filter.status()))
                .and(TaskSpecification.hasPriority(filter.priority()));

        Page<Task> tasksFound = taskRepository.findAll(specification, pageable);

        return tasksFound.map(TaskMapper::toDto);
    }

    public TaskResponseDTO getById (Long id) {
        Task found = findTaskOrThrowException(id);
        return TaskMapper.toDto(found);
    }

    public void delete (Long id) {
        Task found = findTaskOrThrowException(id);
        found.setActive(false);
        taskRepository.save(found);
    }

    public Task findTaskOrThrowException (Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void validateTransaction (TaskStatus from, TaskStatus to) {
        if (!isValidTransaction(from, to)) throw new InvalidStatusTransitionException(from, to);
    }

    public boolean isValidTransaction (TaskStatus from, TaskStatus to) {
        if (from == to) return true;
        return switch (from) {
            case PENDING -> {
                if (to == TaskStatus.IN_PROGRESS) yield true;
                else yield false;
            }
            case IN_PROGRESS -> true;
            case DONE -> false;
        };
    }

}
