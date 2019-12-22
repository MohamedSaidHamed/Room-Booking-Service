package com.service.room.booking.service;

import com.service.room.booking.entity.BookingCalendar;
import com.service.room.booking.entity.Room;
import com.service.room.booking.entity.RoomType;
import com.service.room.booking.repository.RoomRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class RoomServiceTest {
    @Mock
    private RoomRepo roomRepoMock;
    @InjectMocks
    private RoomService roomService;

    @Test
    void getAllRooms() {
        when(roomRepoMock.findAll()).thenReturn(new ArrayList<Room>());
        List<Room> result = roomService.getAllRooms();
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void getRoomById() {
        Room room = new Room();
        room.setId(1);
        when(roomRepoMock.findById(1)).thenReturn(Optional.of(room));
        Room result = roomService.getRoomById(1);
        assertEquals(room, result);
    }

    @Test
    void createRoom() {
        Room room = new Room();
        room.setBookingCalendarList(new ArrayList<>());
        room.setType(RoomType.Double);
        when(roomRepoMock.save(any(Room.class))).thenReturn(room);
        Room result = roomService.createRoom("Double");
        assertEquals(room, result);
    }
}