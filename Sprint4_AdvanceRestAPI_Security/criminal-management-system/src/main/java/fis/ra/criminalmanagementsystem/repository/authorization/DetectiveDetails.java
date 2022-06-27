package fis.ra.criminalmanagementsystem.repository.authorization;

import fis.ra.criminalmanagementsystem.model.Detective;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor

public class DetectiveDetails implements UserDetails{

    Detective detective;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(detective.getRank().toString()));
    }

    @Override
    public String getPassword() {
        return detective.getPassword();
    }

    @Override
    public String getUsername() {
        return detective.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
