package com.niko.restbooks.interceptors;

import com.niko.restbooks.services.UserService;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor extends HandlerInterceptorAdapter {

    private UserService userService;

    public UserInterceptor(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        try {
            request.setAttribute("currentUser", userService.findByLogin(request.getUserPrincipal().getName()).get());
        } catch (Exception ignore) {
        }

        return true;

    }
}
