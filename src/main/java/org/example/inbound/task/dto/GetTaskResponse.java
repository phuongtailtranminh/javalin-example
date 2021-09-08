package org.example.inbound.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTaskResponse {
  private String id;
  private String name;
  private String description;
  private String userId;
}
