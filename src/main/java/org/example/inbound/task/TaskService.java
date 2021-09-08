package org.example.inbound.task;

import com.google.inject.Singleton;
import java.util.Optional;
import java.util.UUID;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.common.config.web.exception.EntityNotFoundException;
import org.example.inbound.task.dto.CreateTaskRequest;
import org.example.inbound.task.dto.GetTaskResponse;
import org.example.jooq.model.tables.pojos.Task;

@Slf4j
@Singleton
public class TaskService {

  private final TaskRepository taskRepository;

  @Inject
  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public GetTaskResponse getTask(String id) {
    return map(
        Optional.ofNullable(taskRepository.findById(id))
            .orElseThrow(
                () -> new EntityNotFoundException(String.format("Task with id %s not found", id))));
  }

  public String createTask(CreateTaskRequest req) {
    String id = UUID.randomUUID().toString();
    taskRepository.insert(map(id, req));
    return id;
  }

  private GetTaskResponse map(Task task) {
    return GetTaskResponse.builder()
        .id(task.getId())
        .name(task.getName())
        .description(task.getDescription())
        .userId(task.getUserId())
        .build();
  }

  private Task map(String id, CreateTaskRequest req) {
    return new Task(id, req.getName(), req.getDescription(), req.getUserId());
  }
}
