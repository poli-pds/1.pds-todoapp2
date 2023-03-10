package co.edu.politecnicojic.todoapp2.controller;

import co.edu.politecnicojic.todoapp2.exceptions.TaskException;
import co.edu.politecnicojic.todoapp2.persistence.entity.Task;
import co.edu.politecnicojic.todoapp2.persistence.entity.TaskStatus;
import co.edu.politecnicojic.todoapp2.services.TaskService;
import co.edu.politecnicojic.todoapp2.services.dto.TaskInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO) {
        return taskService.createTask(taskInDTO);
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllByTaskStatus(@PathVariable("status") TaskStatus status) {

        return taskService.findAllByTaskStatus(status);
    }

    @GetMapping()
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @PatchMapping("mark_as_finished/{taskId}")
    public Task markTaskAsFinished(@PathVariable("taskId") Long taskId) {
        Task task = this.taskService.markTaskAsFinished(taskId);
        if (Objects.isNull(task))
            throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
        return task;
    }

    @DeleteMapping("/{taskId}")
    public Task deleteTaskById(@PathVariable("taskId") Long taskId) {
        Task task = this.taskService.deleteTaskById(taskId);
        if (Objects.isNull(task))
            throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
        return task;
    }

}