package br.com.projeto.politicalmanagement.security;

import br.com.projeto.politicalmanagement.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private UUID userId;
    private String username;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User userModel) {
        List<GrantedAuthority> authorities = userModel.getRoles().stream()
            .flatMap(grupo -> grupo.getPermissions().stream())
            .map(permissao -> new SimpleGrantedAuthority(permissao.getName().toUpperCase()))
            .collect(Collectors.toList());

//                userModel.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
//                .collect(Collectors.toList());
        // Parametros Padrões
        //return new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
        // Incluir outros parametros
        return new UserDetailsImpl(
            userModel.getId(),
            userModel.getUsername(),
            userModel.getPassword(),
            authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
