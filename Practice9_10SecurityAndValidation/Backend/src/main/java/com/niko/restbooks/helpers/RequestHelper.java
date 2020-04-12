package com.niko.restbooks.helpers;

import com.niko.restbooks.entities.UserEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Some common methods to work with request
 */
@Component
public class RequestHelper {

    /*
     * Get current user from the request
     */
    public UserEntity getCurrentUser(HttpServletRequest request) {
        return (UserEntity) request.getAttribute("currentUser");
    }
}
