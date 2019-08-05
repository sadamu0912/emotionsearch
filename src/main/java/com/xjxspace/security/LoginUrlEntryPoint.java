package com.xjxspace.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * map the login url pattern to loginEntryPoint(loginProcessing url)
 */
public class LoginUrlEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private Map<String,String> authLoginUrlEntryMap;
    //loginFormUrl is the default login Url
    public LoginUrlEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
        authLoginUrlEntryMap = new HashMap<>();
        authLoginUrlEntryMap.put("/admin/**","/admin/login");
        authLoginUrlEntryMap.put("/user/**","/user/login");
    }

    /**
     * 根据请求跳转到指定的页面，父类是默认使用loginFormUrl
     * @param request
     * @param response
     * @param exception
     * @return
     */
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String uri = request.getRequestURI().replace(request.getContextPath(),"");
        String url = request.getRequestURL().toString();
        for(Map.Entry<String,String> entry:authLoginUrlEntryMap.entrySet()){
            if(pathMatcher.match(entry.getKey(),uri)){
                return entry.getValue();
            }
        }

        return super.determineUrlToUseForThisRequest(request, response, exception);
    }
}
