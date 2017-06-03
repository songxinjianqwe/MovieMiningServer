package me.newsong.security.domain;

import me.newsong.domain.entity.UserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by SinjinSong on 2017/5/8.
 */
public class JWTUser implements UserDetails {
    private String username;
    private String password;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private UserDO user;
    private Collection<? extends GrantedAuthority> authorities;
    
    public JWTUser(String username, String password, boolean isEnabled, boolean isAccountNonLocked, boolean isAccountNonExpired, boolean isCredentialsNonExpired, Collection<? extends GrantedAuthority> authorities,UserDO user) {
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.authorities = authorities;
        this.user = user;
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
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public UserDO getUser() {
        return user;
    }
}
