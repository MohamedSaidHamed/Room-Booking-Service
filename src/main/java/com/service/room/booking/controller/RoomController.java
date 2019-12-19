package com.service.room.booking.controller;

import com.service.room.booking.entity.Room;
import com.service.room.booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping(value = "/createRoom/{type}")
    public Room createRoom(@PathVariable String type) {
        return roomService.createRoom(type);
    }

}
