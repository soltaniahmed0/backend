package com.example.Backend.Services;

import com.example.Backend.Entity.LostAndFoundItem;
import com.example.Backend.Entity.Snack;
import com.example.Backend.Repository.LostAndFoundRepository;
import com.example.Backend.Repository.SnacksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnacksService {

    @Autowired
    private SnacksRepository snacksRepository;

    public List<Snack> getsnaks() {
        return snacksRepository.findAll();
    }

    public void addsnak(Snack lost ){
        snacksRepository.save(lost);
        }

    public Snack getsnack(int id) {
        return  snacksRepository.findById(id).orElse(null);
    }

    public Snack update(Snack lost) {

        return snacksRepository.save(lost);

    }
    public void deleteSnack(int id){
        snacksRepository.deleteById(id);
    }


}
