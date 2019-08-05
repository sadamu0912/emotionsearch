package com.xjxspace.security;

import com.xjxspace.model.system.User;
import com.xjxspace.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * implements a authenticationProvider backed by jdbc . and provide authentication service.
 * 自定义验证   AuthenticationProvider由AuthenticationProviderManager管理，而且authenticationToken也是受这个
 * 管理 ,one authetication maybe be authenticated by multi AuthenticationProvider
 *
 */
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private IUserService userService;

    private final Md5PasswordEncoder encoder = new Md5PasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String inputUserAccount = authentication.getName();
        String inputPassword =(String) authentication.getCredentials();
        User user = userService.findByUserAccount(inputUserAccount);
        if(user==null){
            throw new AuthenticationCredentialsNotFoundException("credentials not found ");
        }

        if(this.encoder.isPasswordValid(user.getPassword(),inputPassword,user.getId())){
            return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorityList());
        }

        throw new BadCredentialsException("authError");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
