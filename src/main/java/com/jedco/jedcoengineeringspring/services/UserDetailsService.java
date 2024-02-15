package com.jedco.jedcoengineeringspring.services;



import com.jedco.jedcoengineeringspring.models.RoleDefinition;
import com.jedco.jedcoengineeringspring.models.User;
import com.jedco.jedcoengineeringspring.models.UserAction;
import com.jedco.jedcoengineeringspring.models.UserRole;
import com.jedco.jedcoengineeringspring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            final User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + username));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, getAuthorities(user.getUserRole()));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(final UserRole role) {
        return getGrantedAuthorities(getPrivileges(role));
    }

    private List<String> getPrivileges(final UserRole role) {
        final List<String> privileges = new ArrayList<>();
        final Set<RoleDefinition> roleDefinitions = role.getRoleDefinitions();
        final List<UserAction> collection = new ArrayList<>();
        for (final RoleDefinition roleDefinition : roleDefinitions) {
            collection.add(roleDefinition.getUserAction());
        }
        for (final UserAction item : collection) {
            privileges.add(item.getActionName());
        }

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
