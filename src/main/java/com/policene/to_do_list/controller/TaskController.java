package com.policene.to_do_list.controller;

import com.policene.to_do_list.dto.TaskFilterDTO;
import com.policene.to_do_list.dto.TaskRequestDTO;
import com.policene.to_do_list.dto.TaskResponseDTO;
import com.policene.to_do_list.domain.model.enums.TaskPriority;
import com.policene.to_do_list.domain.model.enums.TaskStatus;
import com.policene.to_do_list.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> save(@Valid @RequestBody TaskRequestDTO request) {
        return new ResponseEntity<>(taskService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponseDTO>> listAll (
            @PageableDefault(page = 0, size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            TaskFilterDTO filter
            ) {
        return new ResponseEntity<>(taskService.getActiveTasks(pageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getById (@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getById(id), HttpStatus.OK);
    }

}
