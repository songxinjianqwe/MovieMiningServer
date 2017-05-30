package me.newsong.service.impl;


import me.newsong.domain.entity.UserDO;
import me.newsong.security.domain.JWTUser;
import me.newsong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by SinjinSong on 2017/5/8.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } 
        return new JWTUser(
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
