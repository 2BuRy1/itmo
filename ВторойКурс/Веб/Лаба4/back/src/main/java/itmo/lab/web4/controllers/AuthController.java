package itmo.lab.web4.controllers;

import itmo.lab.web4.models.User;
import itmo.lab.web4.services.AuthService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/api")
public class AuthController {


    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user){

        String token = authService.login(user);

        Map<String, String> response = new HashMap<>();

        response.put("token", token);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) throws BadRequestException {

        String token = authService.register(user);

        Map<String, String> response = new HashMap<>();

        response.put("token", token);

        return ResponseEntity.ok(response);
    }







}
