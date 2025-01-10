package com.userManagement.user_registration.aop;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.userManagement.user_registration.service.UserService.saveUser(..))")
    public void logBeforeUserRegistration() {
        System.out.println("Logging: A new user registration is about to happen.");
    }

    // Logging before getUserById
    @Before("execution(* com.userManagement.user_registration.service.UserService.getUserById(..))")
    public void logBeforeGetUserById() {
        System.out.println("Logging: Retrieving a user by ID.");
    }

    // Logging before deleteUserById
    @Before("execution(* com.userManagement.user_registration.service.UserService.deleteUserById(..))")
    public void logBeforeDeleteUserById() {
        System.out.println("Logging: Deleting a user by ID.");
    }

    // Logging before getAllUsers
    @Before("execution(* com.userManagement.user_registration.service.UserService.getAllUsers(..))")
    public void logBeforeGetAllUsers() {
        System.out.println("Logging: Retrieving all users.");
    }

}
