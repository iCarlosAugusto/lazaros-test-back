package br.com.larazos.lazarosserver.controller;

import br.com.larazos.lazarosserver.controller.request.UserRequest;
import br.com.larazos.lazarosserver.controller.response.UserResponse;
import br.com.larazos.lazarosserver.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "User API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  @Operation(summary = "Create a new user")
  public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRequest));
  }

  @PutMapping("/{userId}")
  @Operation(summary = "Update a user")
  public ResponseEntity<Void> update(
    @RequestBody @Valid UserRequest userRequest,
    @PathVariable UUID userId
  ) {
    userService.update(userRequest, userId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}")
  @Operation(summary = "Delete a user")
  public ResponseEntity<Void> delete(@PathVariable UUID userId) {
    userService.delete(userId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{userId}")
  @Operation(summary = "Find a user by id")
  public ResponseEntity<UserResponse> findById(@PathVariable UUID userId) {
    return ResponseEntity.ok(userService.findById(userId));
  }

  @GetMapping
  @Operation(summary = "Find all users")
  public ResponseEntity<Page<UserResponse>> findAll(
    @PageableDefault(size = 5, page = 0) Pageable pageable
  ) {
    return ResponseEntity.ok(userService.findAll(pageable));
  }
}
