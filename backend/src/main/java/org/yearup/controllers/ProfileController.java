package org.yearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
import org.yearup.models.User;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
@CrossOrigin
@PreAuthorize("hasRole('ROLE_USER')")
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Profile> getProfile(Principal principal) {
        User user = userService.getByUserName(principal.getName());
        Profile profile = profileService.getByUserId(user.getId());
        if (profile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profile);
    }

    @PutMapping
    public ResponseEntity<Profile> updateProfile(Principal principal, @RequestBody Profile profile) {
        User user = userService.getByUserName(principal.getName());
        Profile updated = profileService.update(user.getId(), profile);
        return ResponseEntity.ok(updated);
    }
}
