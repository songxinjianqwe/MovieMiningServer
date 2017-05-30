package me.newsong.security.endpoint;


import lombok.extern.slf4j.Slf4j;
import me.newsong.exception.base.BaseRESTException;
import me.newsong.exception.domain.RESTError;
import me.newsong.propeties.AuthenticationProperties;
import me.newsong.util.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * This is invoked when user tries to access a secured REST resource without supplying any credentials
 */
@Component
@Slf4j
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final String UNAUTHORIZED = JsonUtil.json(new RESTError(HttpStatus.UNAUTHORIZED, 1, null, ""));
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.info("到达JWTAuthenticationEntryPoint");
        if(request.getAttribute(AuthenticationProperties.EXCEPTION_ATTR_NAME) != null){
            BaseRESTException exception = (BaseRESTException) request.getAttribute(AuthenticationProperties.EXCEPTION_ATTR_NAME);
            response.setStatus(exception.getStatus().value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().append(JsonUtil.json(new RESTError(exception.getStatus(),exception.getCode(),exception.getErrors(),exception.getMoreInfoURL())));
        }else{
            response.setStatus(401);
            response.getWriter().append(UNAUTHORIZED);
        }
    }
}
