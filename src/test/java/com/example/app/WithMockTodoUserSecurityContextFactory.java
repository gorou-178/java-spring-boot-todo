package com.example.app;

import com.example.app.annotation.WithMockTodoUser;
import com.example.app.entity.TodoUser;
import com.example.app.service.TodoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockTodoUserSecurityContextFactory implements WithSecurityContextFactory<WithMockTodoUser> {

    @Autowired
    private TodoUserService todoUserService;

    public SecurityContext createSecurityContext(WithMockTodoUser user) {
        TodoUser userDetail = todoUserService.build(user.username(), user.password());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, userDetail.getPassword(), userDetail.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }
}
