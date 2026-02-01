package controllers;

import itmo.lab.web4.controllers.AuthController;
import itmo.lab.web4.models.User;
import itmo.lab.web4.services.AuthService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void testSuccessLogin() {
        User user = new User();
        user.setUsername("mama");
        user.setPassword("papa");

        when(authService.login(any(User.class))).thenReturn("token");

        Map<String, String> response = new HashMap<>();
        response.put("token", "token");

        assertEquals(ResponseEntity.ok(response), authController.login(user));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testFailureLogin() {
        User user = new User();
        user.setUsername("mama");
        user.setPassword("papa");

        when(authService.login(any(User.class)))
                .thenThrow(new UsernameNotFoundException("User not found"));

        authController.login(user);
    }

    @Test(expected = BadCredentialsException.class)
    public void testSuccessRegistration() {
        User user = new User();
        user.setUsername("mama");
        user.setPassword("papa");

        when(authService.register(any(User.class)))
                .thenThrow(new BadCredentialsException("User already exists!!"));

        authController.register(user);
    }
}