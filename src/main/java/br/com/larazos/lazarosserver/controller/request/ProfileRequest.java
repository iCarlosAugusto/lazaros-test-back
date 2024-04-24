package br.com.larazos.lazarosserver.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProfileRequest {

  @NotBlank(message = "The name is required")
  @Length(min = 5, message = "The name must have at least 5 characters")
  @Schema(example = "ADMIN")
  private String description;
}
