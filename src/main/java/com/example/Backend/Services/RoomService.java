package com.example.Backend.Services;

import com.example.Backend.Entity.Room;
import com.example.Backend.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomsRepository;

    public List<Room> getrooms() {
        return roomsRepository.findAll();
    }

    public void addroom(Room room ){
        roomsRepository.save(room);
        }

    public Room getroom(int id) {
        return  roomsRepository.findById(id).orElse(null);
    }

    public Room update(Room room) {

        return roomsRepository.save(room);

    }
    public void deleteroom(int id){
        roomsRepository.deleteById(id);
    }


}
