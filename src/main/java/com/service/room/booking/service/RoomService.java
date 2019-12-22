package com.service.room.booking.service;

import com.service.room.booking.entity.Room;
import com.service.room.booking.entity.RoomType;
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

    /**
     * A method to get all available rooms with each room bookings
     * @return
     */
    public List<Room> getAllRooms() {
        List<Room> roomList = roomRepo.findAll();
        roomList.stream().forEach(room -> {
            room.setBookingCalendarList(bookingCalendarService.getBookingsByRoomAndActive(room, true));
        });
        return roomList;
    }

    /**
     * @param roomId
     * @return
     */
    public Room getRoomById(int roomId) {
        return roomRepo.findById(roomId).get();
    }

    /**
     * @param type
     * @return
     */
    public Room createRoom(String type) {
        Room room = new Room();
        room.setType(RoomType.valueOf(type));
        return roomRepo.save(room);
    }
}
