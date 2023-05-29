package com.example.Backend.auth;

import com.example.Backend.Services.NotificationService;
import com.example.Backend.config.FirebaseConfig;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  @Autowired
  private NotificationService notificationService;

  private final AuthenticationService service;

  @PostMapping( "/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {

    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    notificationService.sendNotification("title", "body");
    return ResponseEntity.ok(service.authenticate(request));
  }
  @GetMapping("/exemple")
  public String exemple(Authentication authentication )
  {
    String x= authentication.getAuthorities().toString();
    System.out.println(authentication.getAuthorities());
    return x;
  }
}
