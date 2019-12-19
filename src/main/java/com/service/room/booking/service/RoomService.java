package com.service.room.booking.service;

import com.service.room.booking.entity.Room;
import com.service.room.booking.repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomService {

    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private BookingCalendarService bookingCalendarService;

    public List<Room> getAllRooms() {
        List<Room> roomList = roomRepo.findAll();
        roomList.stream().forEach(room -> {
            room.setBookingCalendarList(bookingCalendarService.getBookingsByRoomIdAndActive(room, true));
        });
        return roomList;
    }

    public Room getRoomById(int roomId) {
        return roomRepo.findById(roomId).get();
    }
}
