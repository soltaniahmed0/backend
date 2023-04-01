package com.example.Backend.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {



  @Query(value = """
      select t from Token t inner join Employee u\s
      on t.employee.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<Token> findAllValidTokenByUser(Integer id);

  @Query
          (value = """
      select t from Token t inner join Employee u\s
      on t.employee.id = u.id\s
      where u.email = :email and (t.expired = false or t.revoked = false)\s
      """)
  Optional<Token> findByEmployeeEmail(String email);
  Optional<Token> findByToken(String token);
  @Modifying
  @Query(value = "UPDATE Token set expired =true ,revoked=true  where token=:token")
  void setExpiredandRevokedtotrue(String token);
}
