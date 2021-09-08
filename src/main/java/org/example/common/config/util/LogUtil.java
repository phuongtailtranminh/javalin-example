package org.example.common.config.util;


import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;

public class LogUtil {

  public static void logCreateTaskRequest(Logger log, String identifier, Object request) {
    logHttpRequest(log, LogInterface.CREATE_TASK, identifier, request);
  }

  public static void logCreateTaskResponse(Logger log, int httpStatusCode, Object response) {
    logHttpResponse(log, LogInterface.CREATE_TASK, httpStatusCode, response);
  }

  public static void logGetTaskIdRequest(Logger log, String identifier, Object request) {
    logHttpRequest(log, LogInterface.GET_TASK_BY_ID, identifier, request);
  }

  public static void logGetTaskIdResponse(Logger log, int httpStatusCode, Object response) {
    logHttpResponse(log, LogInterface.GET_TASK_BY_ID, httpStatusCode, response);
  }

  private static void logHttpRequest(Logger logger, LogInterface logInterface, String identifier, Object request) {
    if (logger.isDebugEnabled()) {
      String requestAsStr = toString(request);
      logger.debug("Request for {} with id {}.\nRequest: {}", logInterface, identifier, requestAsStr);
    } else {
      logger.info("Request for {} with id {}", logInterface, identifier);
    }
  }

  private static void logHttpResponse(Logger logger, LogInterface logInterface, int httpStatusCode, Object response) {
    if (logger.isDebugEnabled()) {
      String responseAsStr = toString(response);
      logger.debug("Response {} for {}.\nResponse {}", httpStatusCode, logInterface, responseAsStr);
    } else {
      logger.info("Response {} for {}", httpStatusCode, logInterface);
    }
  }

  private static String toString(Object content) {
    if (content == null) {
      return "null";
    } else if (content instanceof String) {
      return (String) content;
    } else if (content instanceof Collection) {
      return ((Collection<Object>) content).stream().map(Objects::toString).collect(Collectors.joining(","));
    } else if (content instanceof Number) {
      return String.valueOf(content);
    } else {
      return ReflectionToStringBuilder.toString(content);
    }
  }

  /**
   * Interfaces for logging
   * */
  private enum LogInterface {
    GET_TASK_BY_ID,
    CREATE_TASK
  }
}
