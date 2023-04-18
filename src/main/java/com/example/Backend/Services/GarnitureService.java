
package com.example.Backend.Services;


import com.example.Backend.Entity.Garniture;
import com.example.Backend.Repository.GarnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GarnitureService {

    @Autowired
    private GarnitureRepository garnitureRepository;

    public Garniture addgar(Garniture garniture ){
        return garnitureRepository.save(garniture);
        }



}

