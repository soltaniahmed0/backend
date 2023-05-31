package com.example.Backend.auth;

import com.example.Backend.Entity.Company;
import com.example.Backend.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private String position;
  private Company company;
  private Integer phone;
  private boolean theme;
  private boolean availability;
  private Integer employee_id;
  private Role role;
  private String photo;
  private Boolean firstime;

}
