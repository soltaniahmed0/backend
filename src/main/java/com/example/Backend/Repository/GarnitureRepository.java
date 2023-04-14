package com.example.Backend.Repository;

import com.example.Backend.Entity.Garniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarnitureRepository extends JpaRepository<Garniture, Integer> {


}
