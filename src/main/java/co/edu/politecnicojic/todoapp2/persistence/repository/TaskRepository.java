package co.edu.politecnicojic.todoapp2.persistence.repository;

import co.edu.politecnicojic.todoapp2.persistence.entity.Task;
import co.edu.politecnicojic.todoapp2.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByTaskStatus(TaskStatus taskStatus);
}
