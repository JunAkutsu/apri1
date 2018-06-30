package com.apri.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;


@Component
public class MyLogoutHandler implements LogoutHandler{

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication){
        // 何かの処理
    	// authentication.getName() でログインIDが取れる
    	System.out.println(authentication.getName());
    }
}
