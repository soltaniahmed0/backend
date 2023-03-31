package com.example.Backend.auth;


import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.Role;
import com.example.Backend.Repository.EmployeeRepository;
import com.example.Backend.config.JwtService;
import com.example.Backend.token.Token;
import com.example.Backend.token.TokenRepository;
import com.example.Backend.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EmployeeRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Employee.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.Rcashier)
                .company(request.getCompany())
                .position(request.getPosition())
                .theme(request.isTheme())
                .availability(request.isAvailability())
                .phone(request.getPhone())
                .employee_id(request.getEmployee_id())
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
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
        List<Token> token=tokenRepository.findAllValidTokenByUser(user.getId());
        var jwtToken="";
        if(token.isEmpty())
        {
             jwtToken = jwtService.generateToken(user);

            saveUserToken(user, jwtToken);
        }
        else {
for (Token t:token){
    jwtToken=t.getToken();
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
