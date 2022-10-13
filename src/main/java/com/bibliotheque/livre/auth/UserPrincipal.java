package com.bibliotheque.livre.auth;

import com.bibliotheque.livre.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;

    private User user;

    public UserPrincipal(User user){
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getMdp();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List< SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
            return authorities;
    }

    @Override
    public boolean isEnabled() {
        return user.getIsEnabled();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


}
