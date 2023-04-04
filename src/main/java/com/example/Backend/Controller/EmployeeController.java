package com.example.Backend.Controller;

import com.example.Backend.Entity.Employee;
import com.example.Backend.Services.EmployeeService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/Emp")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

        @GetMapping("/ok")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }
    @GetMapping("/all")
    public List<Employee> getEmps() {
        System.out.println(employeeService.getAllEmps());
        return employeeService.getAllEmps();
    }
    @PostMapping("/resetpassword")
    public ResponseEntity<String> resetPassword(@RequestParam("email") String email) {
        employeeService.sendPasswordByEmail(email);
        return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
    }
    @GetMapping( "/salem")
    public ResponseEntity<String> salem() {
        return ResponseEntity.ok("salem");
    }

    @PutMapping("/updateemp/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        Optional<Employee> existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee.isPresent()) {
            Employee updatedEmployee = existingEmployee.get();
            updatedEmployee.setEmail(employee.getEmail());
            updatedEmployee.setFirstname(employee.getFirstname());
            updatedEmployee.setLastname(employee.getLastname());
            updatedEmployee.setEnterprise(employee.getEnterprise());
            updatedEmployee.setPosition(employee.getPosition());
            updatedEmployee.setPhone(employee.getPhone());
            updatedEmployee.setPhoto(employee.getPhoto());
            updatedEmployee = employeeService.updateEmployee(updatedEmployee);
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/empinfo")
    public ResponseEntity<Optional<Employee>> getEmployee(@RequestHeader("Authorization") String token) {
        if (!token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String jwt = token.substring(7);

        try {
            String SECRET_KEY = "28472B4B6250655368566D5971337336763979244226452948404D635166546A";

            byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(keyBytes).build().parseClaimsJws(jwt);

            String email = claims.getBody().getSubject();
            Optional<Employee> employee = employeeService.getEmp(email);
            return ResponseEntity.ok(employee);
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @GetMapping("/getVerificationCode/{email}")
    public  ResponseEntity<String > getVerificationCode(@PathVariable  String email) throws Exception {
        try {
            String code = employeeService.sendEmailVerification(email);
            return ResponseEntity.ok(code);
        } catch (Exception e) {

            return new ResponseEntity<>("Error sending verification code: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<String> SetNewPassword(@PathVariable Integer id,@RequestBody Map<String, String> requestBody){
            try {
                String newPassword =requestBody.get("password");
                employeeService.SetnewPassword(id,newPassword);
                return ResponseEntity.ok("password have been updated"+newPassword.toString());
            }
            catch (Exception e)
            {
                return new ResponseEntity<>("Error in updating password "+ e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }


}
