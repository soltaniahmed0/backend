package com.example.Backend.auth;


import com.example.Backend.Entity.Company;
import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.Role;
import com.example.Backend.Repository.CompanyRepository;
import com.example.Backend.Repository.EmployeeRepository;
import com.example.Backend.config.JwtService;
import com.example.Backend.token.Token;
import com.example.Backend.token.TokenRepository;
import com.example.Backend.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EmployeeRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    public AuthenticationResponse register(RegisterRequest request) {
        String Password=generateNewPassword();
        Company company = companyRepository.findByCompanyName(request.getCompany().getCompanyName());
        var user = Employee.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(Password))
                .role(request.getRole())
                .company(company)
                .position(request.getPosition())
                .theme(false)
                .availability(true)
                .phone(request.getPhone())
                .photo(request.getPhoto().getBytes())
                .firstTime(true)
                .DeviceToken("")
                .Facebook("")
                .Linkedin("")
                .Instagram("")
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        sendEmail(request.getEmail(),"Your Password",Password);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    private void sendEmail(String toEmail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
    private String generateNewPassword() {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789\\|";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }
        return password.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        user.setDeviceToken(request.getDeviceToken());
        repository.save(user);
        List<Token> token = tokenRepository.findAllValidTokenByUser(user.getId());
        var jwtToken = "";
        if (token.isEmpty()) {
            jwtToken = jwtService.generateToken(user);

            saveUserToken(user, jwtToken);
        } else {
            for (Token t : token) {
                jwtToken = t.getToken();
            }
        }

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(Employee employee, String jwtToken) {
        var token = Token.builder()
                .employee(employee)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Employee employee) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(employee.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
