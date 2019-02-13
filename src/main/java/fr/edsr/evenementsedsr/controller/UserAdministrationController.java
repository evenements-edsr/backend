package fr.edsr.evenementsedsr.controller;

import fr.edsr.evenementsedsr.dto.ApiResponseDto;
import fr.edsr.evenementsedsr.dto.UserAdministrationDto;
import fr.edsr.evenementsedsr.repository.UserAdministrationRepository;
import fr.seblaporte.springsocial.exception.BadRequestException;
import fr.seblaporte.springsocial.model.AuthProvider;
import fr.seblaporte.springsocial.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class UserAdministrationController {

    private final UserAdministrationRepository userAdministrationRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAdministrationController(UserAdministrationRepository userAdministrationRepository,
                                        PasswordEncoder passwordEncoder) {
        this.userAdministrationRepository = userAdministrationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserAdministrationDto userAdministrationDto) {

        if(userAdministrationRepository.existsByEmail(userAdministrationDto.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        User user = new User();
        user.setName(userAdministrationDto.getName());
        user.setEmail(userAdministrationDto.getEmail());
        user.setPassword(userAdministrationDto.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userAdministrationRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponseDto(true, "User registered successfully"));
    }
}
