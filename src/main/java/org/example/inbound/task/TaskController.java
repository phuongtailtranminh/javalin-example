package org.example.inbound.task;

import com.google.inject.Singleton;
import io.javalin.http.Context;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.http.HttpStatus;
import org.example.common.config.util.LogUtil;
import org.example.common.config.web.annotation.GetMapping;
import org.example.common.config.web.annotation.PostMapping;
import org.example.inbound.task.dto.CreateTaskRequest;
import org.example.inbound.task.dto.CreateTaskResponse;
import org.example.inbound.task.dto.GetTaskResponse;

@Slf4j
@Singleton
public class TaskController {

  private final TaskService taskService;

  @Inject
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping(path = "/tasks/:id")
  public void getTask(Context ctx) {
    String id = ctx.pathParam("id");

    LogUtil.logGetTaskIdRequest(log, id, null);
    GetTaskResponse response = taskService.getTask(id);
    LogUtil.logGetTaskIdResponse(log, HttpStatus.OK_200, response);
    ctx.json(response);
  }

  @PostMapping(path = "/tasks")
  public void createTask(Context ctx) {
    CreateTaskRequest req = ctx.bodyValidator(CreateTaskRequest.class).get();

    LogUtil.logCreateTaskRequest(log, null, req);
    CreateTaskResponse response = CreateTaskResponse.builder().id(taskService.createTask(req)).build();
    LogUtil.logCreateTaskResponse(log, HttpStatus.CREATED_201, response);
    ctx.status(HttpStatus.CREATED_201);
    ctx.json(response);
  }

}
