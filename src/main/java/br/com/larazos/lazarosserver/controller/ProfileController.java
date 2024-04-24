package br.com.larazos.lazarosserver.controller;

import br.com.larazos.lazarosserver.controller.request.ProfileRequest;
import br.com.larazos.lazarosserver.controller.response.ProfileResponse;
import br.com.larazos.lazarosserver.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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

@Tag(name = "Profile", description = "Profile API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/profiles")
public class ProfileController {

  private final ProfileService profileService;

  @PostMapping
  @Operation(summary = "Create a new profile")
  public ResponseEntity<ProfileResponse> create(@RequestBody @Valid ProfileRequest profileRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(profileService.create(profileRequest));
  }

  @PutMapping("/{profileId}")
  @Operation(summary = "Update a profile")
  public ResponseEntity<Void> update(
    @RequestBody @Valid ProfileRequest profileRequest,
    @PathVariable UUID profileId
  ) {
    profileService.update(profileRequest, profileId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{profileId}")
  @Operation(summary = "Delete a profile")
  public ResponseEntity<Void> delete(@PathVariable UUID profileId) {
    profileService.delete(profileId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{profileId}")
  @Operation(summary = "Find a profile by id")
  public ResponseEntity<ProfileResponse> findById(@PathVariable UUID profileId) {
    return ResponseEntity.ok(profileService.findById(profileId));
  }

  @GetMapping
  @Operation(summary = "Find all profiles")
  public ResponseEntity<List<ProfileResponse>> findAll() {
    return ResponseEntity.ok(profileService.findAll());
  }
}
