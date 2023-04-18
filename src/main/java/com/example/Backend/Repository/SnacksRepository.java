package com.example.Backend.Repository;


import com.example.Backend.Entity.Snack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnacksRepository extends JpaRepository<Snack, Integer> {


}
