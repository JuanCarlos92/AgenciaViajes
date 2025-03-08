package org.juancarlos.security.service;

import org.juancarlos.security.entity.RoleEntity;
import org.juancarlos.security.entity.UserEntity;
import org.juancarlos.security.repository.RoleRepository;
import org.juancarlos.security.repository.UserRepository;
import org.juancarlos.security.dto.LoginDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserEntity authenticate(LoginDTO input) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getUsername(),
                            input.getPassword()
                    )
            );

        return userRepository.findByUser(input.getUsername())
                .orElseThrow();
    }

    public RoleEntity getRol(String user) {

        return roleRepository.findByUser(user)
                .orElseThrow();
    }
}
