package com.jedco.jedcoengineeringspring.services;
import com.jedco.jedcoengineeringspring.exceptions.AuthenticationException;
import com.jedco.jedcoengineeringspring.models.RoleDefinition;
import com.jedco.jedcoengineeringspring.models.User;
import com.jedco.jedcoengineeringspring.models.UserAction;
import com.jedco.jedcoengineeringspring.repositories.RoleDefinitionRepository;
import com.jedco.jedcoengineeringspring.repositories.UserActionRepository;
import com.jedco.jedcoengineeringspring.repositories.UserRepository;
import com.jedco.jedcoengineeringspring.rest.request.LoginRequest;
import com.jedco.jedcoengineeringspring.rest.response.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleDefinitionRepository roleDefinitionRepository;
    private final UserActionRepository userActionRepository;

    @Override
    public JwtAuthenticationResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        } catch (org.springframework.security.core.AuthenticationException ex) {
            throw new AuthenticationException("Bad credentials!");
        }
        var user = usersRepository.findByUsername(request.username())
                .orElseThrow(() -> new AuthenticationException("Bad credentials!"));
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt, user.getFirstName(), toAuthoritySet(user), true, "Authentication Successful!");
    }

    @Override
    public JwtAuthenticationResponse refreshToken() {
        return null;
    }

    private Set<String> toAuthoritySet(User user) {
        Set<String> authorities = new HashSet<>();
        List<RoleDefinition> roleDefinitions = roleDefinitionRepository.findByUserRole(user.getUserRole());
        roleDefinitions.forEach(roleDefinition -> {
            UserAction action = userActionRepository.findById(roleDefinition.getUserAction().getId()).get();
            authorities.add(action.getActionName());
        });
        return authorities;
    }
}
