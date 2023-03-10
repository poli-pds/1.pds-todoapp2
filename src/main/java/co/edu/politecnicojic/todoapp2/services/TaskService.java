package co.edu.politecnicojic.todoapp2.services;

import co.edu.politecnicojic.todoapp2.mapper.TaskInDTOToTask;
import co.edu.politecnicojic.todoapp2.persistence.entity.Task;
import co.edu.politecnicojic.todoapp2.persistence.entity.TaskStatus;
import co.edu.politecnicojic.todoapp2.persistence.repository.TaskRepository;
import co.edu.politecnicojic.todoapp2.services.dto.TaskInDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskInDTOToTask taskInDTOToTask;

    public Task createTask(TaskInDTO taskInDTO) {
        return taskRepository.save(taskInDTOToTask.mapper(taskInDTO));
    }

    public List<Task> findAllByTaskStatus(TaskStatus status) {
        return taskRepository.findAllByTaskStatus(status);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional
    public Task markTaskAsFinished(Long taskId) {
        Optional<Task> task = this.taskRepository.findById(taskId);
        if (task.isEmpty()) return null;
        this.taskRepository.markTaskAsFinished(taskId);
        return task.orElse(null);
    }

    @Transactional
    public Task deleteTaskById(Long taskId) {
        Optional<Task> task = this.taskRepository.findById(taskId);
        if (task.isEmpty()) return null;
        this.taskRepository.deleteById(taskId);
        return task.orElse(null);
    }

}
