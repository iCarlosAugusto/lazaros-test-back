package br.com.larazos.lazarosserver.controller.response;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {

  private UUID id;
  private String name;
  private List<ProfileResponse> profiles;
}
