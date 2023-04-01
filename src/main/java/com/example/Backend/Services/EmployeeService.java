package com.example.Backend.Services;

import com.example.Backend.Entity.Employee;
import com.example.Backend.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmps() {
        return employeeRepository.findAll();
    }


    public Optional<Employee> getEmp(String email){
        return employeeRepository.findByEmail(email);
    }
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendPasswordByEmail(String email) {
        Optional<Employee> employeeOptional  = employeeRepository.findByEmail(email);
        if (employeeOptional.isPresent()) {
            Employee employee=employeeOptional.get();
            String newPassword = generateNewPassword();
            employee.setPassword(encodePassword(newPassword));
            employeeRepository.save(employee);
            // Send an email with the new password
            sendEmail(employee.getEmail(), "Your new password", "Your new password is: " + newPassword);
        }

    }

    private String generateNewPassword() {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=[]{}<>,.?/\\|";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }
        return password.toString();
    }

    private String encodePassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);

    }

    private void sendEmail(String toEmail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
