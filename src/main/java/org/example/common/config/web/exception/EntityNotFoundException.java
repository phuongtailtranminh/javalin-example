package org.example.common.config.web.exception;

import org.eclipse.jetty.http.HttpStatus;

public class EntityNotFoundException extends AppException {

  public EntityNotFoundException(String reason) {
    super(HttpStatus.NOT_FOUND_404, ErrorCode.ENTITY_NOT_FOUND, reason);
  }
}
