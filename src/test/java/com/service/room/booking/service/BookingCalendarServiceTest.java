package com.service.room.booking.service;

import com.service.room.booking.entity.BookingCalendar;
import com.service.room.booking.entity.Room;
import com.service.room.booking.repository.BookingCalendarRepo;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class BookingCalendarServiceTest {

    @Mock
    private BookingCalendarRepo bookingCalendarRepoMock;
    @InjectMocks
    private BookingCalendarService bookingCalendarService;


    @Test
    void getBookingsByValidRoomAndActive() {
        when(bookingCalendarRepoMock.findAllByRoomAndActive(new Room(), true)).thenReturn(new ArrayList<BookingCalendar>());
        List<BookingCalendar> result = bookingCalendarService.getBookingsByRoomAndActive(new Room(), true);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void getBookingsByActive() {
        BookingCalendar bookingCalendar = new BookingCalendar();
        bookingCalendar.setActive(true);
        bookingCalendar.setCheckinDate(new Date());
        bookingCalendar.setCheckoutDate(new Date());
        bookingCalendar.setRoom(new Room());
        when(bookingCalendarRepoMock.findAllByActive(true)).thenReturn(Arrays.asList(bookingCalendar));
        List<BookingCalendar> result = bookingCalendarService.getBookingsByActive(true);
        Assert.assertThat(result, Matchers.contains(bookingCalendar));
    }

    @Test
    void getBookingsByInActive() {
        BookingCalendar bookingCalendar = new BookingCalendar();
        bookingCalendar.setActive(false);
        bookingCalendar.setCheckinDate(new Date());
        bookingCalendar.setCheckoutDate(new Date());
        bookingCalendar.setRoom(new Room());
        when(bookingCalendarRepoMock.findAllByActive(true)).thenReturn(new ArrayList<>());
        List<BookingCalendar> result = bookingCalendarService.getBookingsByActive(true);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void getBookingByIdAndActive() {
        BookingCalendar bookingCalendar = new BookingCalendar();
        bookingCalendar.setId(1);
        bookingCalendar.setActive(true);
        when(bookingCalendarRepoMock.findByIdAndActive(1, true)).thenReturn(bookingCalendar);
        BookingCalendar result = bookingCalendarService.getBookingByIdAndActive(1, true);
        assertEquals(bookingCalendar, result);
    }

    @Test
    void updateBooking() {
        BookingCalendar bookingCalendar = new BookingCalendar();
        bookingCalendar.setId(1);
        bookingCalendar.setActive(true);
        Date checkin = new Date();
        Date checkout = new Date();
        bookingCalendar.setCheckinDate(checkin);
        bookingCalendar.setCheckoutDate(checkout);
        bookingCalendar.setPeriod(0);
        bookingCalendar.setRoom(new Room());
        when(bookingCalendarRepoMock.save(any(BookingCalendar.class))).thenReturn(bookingCalendar);
        BookingCalendar result = bookingCalendarService.updateBooking(new Room(), checkin, checkout);
        assertEquals(bookingCalendar, result);
    }

    @Test
    void deleteBooking() {
        BookingCalendar bookingCalendar = new BookingCalendar();
        bookingCalendar.setActive(false);
        when(bookingCalendarRepoMock.save(any(BookingCalendar.class))).thenReturn(bookingCalendar);
        BookingCalendar result = bookingCalendarService.deleteBooking(bookingCalendar);
        assertEquals(bookingCalendar, result);
    }
}