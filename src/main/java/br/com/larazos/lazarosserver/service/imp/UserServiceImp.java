package br.com.larazos.lazarosserver.service.imp;

import br.com.larazos.lazarosserver.controller.request.UserRequest;
import br.com.larazos.lazarosserver.controller.response.UserResponse;
import br.com.larazos.lazarosserver.exceptions.ResourceAlreadyRegisteredException;
import br.com.larazos.lazarosserver.exceptions.ResourceNotFoundException;
import br.com.larazos.lazarosserver.model.Profile;
import br.com.larazos.lazarosserver.model.User;
import br.com.larazos.lazarosserver.repository.ProfileRepository;
import br.com.larazos.lazarosserver.repository.UserRepository;
import br.com.larazos.lazarosserver.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService {

  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;
  private final ModelMapper mapper;

  @Override
  public UserResponse create(UserRequest userRequest) {
    verifyUser(userRequest);
    User user = mapper.map(userRequest, User.class);
    userRequest
      .getProfiles()
      .forEach(profileRequest -> addProfile(profileRequest, user));

    return mapper.map(userRepository.save(user), UserResponse.class);
  }

  @Override
  public void update(UserRequest userRequest, UUID userId) {
    verifyUser(userRequest);
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    mapper.map(userRequest, user);
    userRequest
      .getProfiles()
      .forEach(profileRequest -> addProfile(profileRequest, user));
    userRepository.save(user);
  }

  @Override
  public void delete(UUID userId) {
    userRepository.deleteById(userId);
  }

  @Override
  public UserResponse findById(UUID userId) {
    return userRepository.findById(userId)
      .map(user -> mapper.map(user, UserResponse.class))
      .orElseThrow(() -> new ResourceNotFoundException("User not found"));
  }

  @Override
  public Page<UserResponse> findAll(Pageable pageable) {
    return userRepository
      .findAll(pageable)
      .map(user -> mapper.map(user, UserResponse.class));
  }

  private void addProfile(String profileRequest, User user) {
    Profile profile = profileRepository.findByDescription(profileRequest)
      .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    user.addProfile(profile);
  }

  private void verifyUser(UserRequest userRequest) {
    boolean existsByName = userRepository.existsByName(userRequest.getName());
    if (existsByName) {
      throw new ResourceAlreadyRegisteredException("User already registered");
    }

  }
}
