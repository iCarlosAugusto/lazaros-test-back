package br.com.larazos.lazarosserver.service.imp;

import br.com.larazos.lazarosserver.controller.request.ProfileRequest;
import br.com.larazos.lazarosserver.controller.response.ProfileResponse;
import br.com.larazos.lazarosserver.exceptions.ResourceAlreadyRegisteredException;
import br.com.larazos.lazarosserver.exceptions.ResourceNotFoundException;
import br.com.larazos.lazarosserver.model.Profile;
import br.com.larazos.lazarosserver.repository.ProfileRepository;
import br.com.larazos.lazarosserver.service.ProfileService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProfileServiceImp implements ProfileService {

  private final ProfileRepository profileRepository;
  private final ModelMapper mapper;

  @Override
  public ProfileResponse create(ProfileRequest profileRequest) {
    verifyProfile(profileRequest);
    Profile profile = mapper.map(profileRequest, Profile.class);
    return mapper.map(profileRepository.save(profile), ProfileResponse.class);
  }

  @Override
  public void update(ProfileRequest profileRequest, UUID profileId) {
    verifyProfile(profileRequest);
    Profile profile = profileRepository.findById(profileId)
      .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    mapper.map(profileRequest, profile);
    profileRepository.save(profile);
  }

  @Override
  public void delete(UUID profileId) {
    profileRepository.deleteById(profileId);
  }

  @Override
  public ProfileResponse findById(UUID profileId) {

    return profileRepository.findById(profileId)
      .map(profile -> mapper.map(profile, ProfileResponse.class))
      .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
  }

  @Override
  public List<ProfileResponse> findAll() {
    return profileRepository
      .findAll()
      .stream()
      .map(profile -> mapper.map(profile, ProfileResponse.class))
      .toList();
  }

  private void verifyProfile(ProfileRequest profileRequest) {
    boolean existsByDescription = profileRepository.existsByDescription(profileRequest.getDescription());
    if (existsByDescription) {
      throw new ResourceAlreadyRegisteredException("Profile already exists");
    }
  }
}
