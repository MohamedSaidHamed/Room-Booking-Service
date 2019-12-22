package com.service.room.booking.controller;

import com.service.room.booking.entity.BookingCalendar;
import com.service.room.booking.entity.Room;
import com.service.room.booking.exceptionhandler.BookingNotFoundException;
import com.service.room.booking.exceptionhandler.RoomNotFoundException;
import com.service.room.booking.service.BookingCalendarService;
import com.service.room.booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BookingCalendarController {
    @Autowired
    private BookingCalendarService bookingCalendarService;
    @Autowired
    private RoomService roomService;

    /**
     * A method to create a new booking for an existing room.
     *
     * @param roomId
     * @param checkin
     * @param checkout
     * @return
     */
    @PostMapping(value = "/booking")
    public BookingCalendar roomBooking(@RequestParam int roomId, @RequestParam Date checkin, @RequestParam Date checkout) {
        Room room = roomService.getRoomById(roomId);
        if (room == null) {
            throw new RoomNotFoundException("INVALID_ROOM_ID");
        }
        return bookingCalendarService.updateBooking(room, checkin, checkout);
    }

    /**
     * A method to return all active bookings.
     *
     * @return
     */
    @GetMapping(value = "/bookings")
    public List<BookingCalendar> getAllActiveBookings() {
        return bookingCalendarService.getBookingsByActive(true);
    }

    /**
     * A method to delete "mark as Inactive, not entirely delete" a booking based on the provided booking id.
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "bookings/{id}")
    public ResponseEntity deleteBooking(@PathVariable int id) {
        BookingCalendar bookingCalendar = bookingCalendarService.getBookingByIdAndActive(id, true);
        if (bookingCalendar == null) {
            throw new BookingNotFoundException("INVALID_BOOKING_ID");
        }
        bookingCalendarService.deleteBooking(bookingCalendar);
        return new ResponseEntity(HttpStatus.OK);
    }
}
