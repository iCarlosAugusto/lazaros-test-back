package br.com.larazos.lazarosserver.controller.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfileResponse {

  private UUID id;
  private String description;
}
