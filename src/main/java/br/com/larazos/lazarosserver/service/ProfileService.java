package br.com.larazos.lazarosserver.service;

import br.com.larazos.lazarosserver.controller.request.ProfileRequest;
import br.com.larazos.lazarosserver.controller.response.ProfileResponse;
import java.util.List;
import java.util.UUID;

public interface ProfileService {

  ProfileResponse create(ProfileRequest profileRequest);

  void update(ProfileRequest profileRequest, UUID profileId);

  void delete(UUID profileId);

  ProfileResponse findById(UUID profileId);

  List<ProfileResponse> findAll();
}
