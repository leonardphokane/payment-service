package com.example.payment.service;

import com.example.payment.entity.Profile;
import com.example.payment.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepo;

    public ProfileService(ProfileRepository profileRepo) {
        this.profileRepo = profileRepo;
    }

    public List<Profile> findAll() {
        return profileRepo.findAll();
    }

    public Optional<Profile> findById(Long id) {
        return profileRepo.findById(id);
    }

    public Profile save(Profile profile) {
        return profileRepo.save(profile);
    }

    public void delete(Long id) {
        profileRepo.deleteById(id);
    }
}
