package br.com.larazos.lazarosserver.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

  @NotBlank(message = "The name is required")
  @Length(min = 10, message = "The name must have at least 10 characters")
  @Schema(example = "John Doe Smith")
  private String name;
  @NotNull(message = "The profiles is required")
  private List<String> profiles;
}
