package br.com.larazos.lazarosserver.service;

import br.com.larazos.lazarosserver.controller.request.UserRequest;
import br.com.larazos.lazarosserver.controller.response.UserResponse;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  UserResponse create(UserRequest userRequest);

  void update(UserRequest userRequest, UUID userId);

  void delete(UUID userId);

  UserResponse findById(UUID userId);

  Page<UserResponse> findAll(Pageable pageable);
}
