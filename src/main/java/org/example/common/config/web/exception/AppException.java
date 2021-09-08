package org.example.common.config.web.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"message", "localizedMessage", "cause", "stackTrace", "suppressed"})
public class AppException extends RuntimeException {
  private int code;
  private ErrorCode errorCode;
  private String reason;
}
