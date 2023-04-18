package com.example.Backend.Repository;

import com.example.Backend.Entity.LostAndFoundItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostAndFoundRepository extends JpaRepository<LostAndFoundItem, Integer> {


}
