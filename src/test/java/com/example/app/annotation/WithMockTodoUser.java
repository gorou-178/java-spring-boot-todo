package com.example.app.annotation;

import com.example.app.WithMockTodoUserSecurityContextFactory;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@WithSecurityContext(factory = WithMockTodoUserSecurityContextFactory.class)
public @interface WithMockTodoUser {
    int id() default 1;

    String username() default "";

    String[] roles() default { "USER" };

    String[] authorities() default {};

    String password() default "password";
}
