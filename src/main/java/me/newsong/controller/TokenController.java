package me.newsong.controller;


import lombok.extern.slf4j.Slf4j;
import me.newsong.domain.common.LoginDTO;
import me.newsong.exception.LoginException;
import me.newsong.exception.ValidationException;
import me.newsong.security.domain.JWTUser;
import me.newsong.security.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by SinjinSong on 2017/4/27.
 */

@CrossOrigin
@RequestMapping("/tokens")
@RestController
@Slf4j
public class TokenController {
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 1.用户登录时，先经过自定义的passcard_filter过滤器，
     * 该过滤器继承了AbstractAuthenticationProcessingFilter，
     * 2.执行attemptAuthentication方法，可以通过request获取登录页面传递的参数，
     * 实现自己的逻辑，并且把对应参数set到AbstractAuthenticationToken的实现类中
     * 3.验证逻辑走完后，调用 this.getAuthenticationManager().authenticate(token)方法，
     * 执行AuthenticationProvider的实现类的supports方法
     * 4.如果返回true则继续执行authenticate方法
     * 5.在authenticate方法中，首先可以根据用户名获取到用户信息，
     * 再者可以拿自定义参数和用户信息做逻辑验证，如密码的验证
     * 6.自定义验证通过以后，获取用户权限set到User中，用于springSecurity做权限验证
     * 7.this.getAuthenticationManager().authenticate(token)方法执行完后，
     * 会返回Authentication，如果不为空，则说明验证通过
     */
    @RequestMapping(method = RequestMethod.POST)
    public String login(@Valid @RequestBody LoginDTO loginDTO, BindingResult result) {

       //登录信息不完整
        if (result.hasErrors()) {
            throw new ValidationException(result.getFieldErrors());
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new LoginException();
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //到这里验证成功
        //如果之前已经登录过，那么清除之前登录的token
        tokenManager.deleteToken(loginDTO.getUsername());
        //申请新的token
        String token = tokenManager.createToken(loginDTO.getUsername());
        return token;
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public void logout(@AuthenticationPrincipal JWTUser user) {
        tokenManager.deleteToken(user.getUsername());
    }
}
