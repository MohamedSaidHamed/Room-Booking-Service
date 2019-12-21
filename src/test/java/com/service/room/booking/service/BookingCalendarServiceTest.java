package com.service.room.booking.service;

import com.service.room.booking.entity.BookingCalendar;
import com.service.room.booking.entity.Room;
import com.service.room.booking.repository.BookingCalendarRepo;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class BookingCalendarServiceTest {

    @Mock
    private RoomRepo roomRepoMock;
    @Mock
    private BookingCalendarRepo bookingCalendarRepoMock;
    @InjectMocks
    private BookingCalendarService bookingCalendarServiceMock;


    @Test
    void getBookingsByValidRoomIdAndActive() {
        when(bookingCalendarRepoMock.findAllByRoomAndActive(new Room(),true)).thenReturn(new ArrayList<BookingCalendar>());
        List<BookingCalendar> result = bookingCalendarServiceMock.getBookingsByRoomIdAndActive(new Room(), true);
        assertEquals(Collections.emptyList(),result);
    }

    @Test
    void getBookingsByActive() {
    }

    @Test
    void getBookingByIdAndActive() {
    }

    @Test
    void updateBooking() {
    }

    @Test
    void deleteBooking() {
    }
}