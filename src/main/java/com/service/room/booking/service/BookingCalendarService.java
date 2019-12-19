package com.service.room.booking.service;

import com.service.room.booking.entity.BookingCalendar;
import com.service.room.booking.entity.Room;
import com.service.room.booking.repository.BookingCalendarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class BookingCalendarService {
    @Autowired
    private BookingCalendarRepo bookingCalendarRepo;

    public List<BookingCalendar> getBookingsByRoomIdAndActive(Room roomId, boolean active) {
        return bookingCalendarRepo.findAllByRoomAndActive(roomId, active);
    }
    public List<BookingCalendar> getBookingsByActive(boolean active) {
        return bookingCalendarRepo.findAllByActive(active);
    }
    public BookingCalendar getBookingByIdAndActive(int id, boolean active) {
        return bookingCalendarRepo.findByIdAndActive(id,active);
    }

    public BookingCalendar updateBooking(Room room, Date checkin, Date checkout) {
        BookingCalendar bookingCalendar = new BookingCalendar();
        bookingCalendar.setRoom(room);
        bookingCalendar.setCheckinDate(checkin);
        bookingCalendar.setCheckoutDate(checkout);
        Period period = Period.between(convertToLocalDateViaInstant(checkin),
                convertToLocalDateViaInstant(checkout));
        bookingCalendar.setPeriod((double) period.getDays());
        bookingCalendar.setActive(true);
        return bookingCalendarRepo.save(bookingCalendar);
    }
    public BookingCalendar deleteBooking(BookingCalendar bookingCalendar) {
        bookingCalendar.setActive(false);
        return bookingCalendarRepo.save(bookingCalendar);
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
