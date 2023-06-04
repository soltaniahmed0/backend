package com.example.Backend.Services;

import com.example.Backend.Entity.Category;
import com.example.Backend.Entity.LostAndFoundItem;
import com.example.Backend.Repository.CategoryRepository;
import com.example.Backend.Repository.LostAndFoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostAndFoundService {

    @Autowired
    private LostAndFoundRepository lostAndFoundRepository;

    public List<LostAndFoundItem> getlost() {
        return lostAndFoundRepository.findAll();
    }

    public void addlost(LostAndFoundItem lost ){
        lostAndFoundRepository.save(lost);
        }

    public LostAndFoundItem getlost(int id) {
        return  lostAndFoundRepository.findById(id).orElse(null);
    }

    public LostAndFoundItem update(LostAndFoundItem lost) {

        LostAndFoundItem existinglost=getlost(lost.getItem_id());
        existinglost.setOwner(lost.getOwner());

        return lostAndFoundRepository.save(existinglost);


    }


}
