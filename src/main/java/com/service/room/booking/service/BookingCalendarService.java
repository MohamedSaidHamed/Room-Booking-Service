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

    /**
     * A method to retrieve all active bookings by room
     *
     * @param room
     * @param active
     * @return
     */
    public List<BookingCalendar> getBookingsByRoomAndActive(Room room, boolean active) {
        return bookingCalendarRepo.findAllByRoomAndActive(room, active);
    }

    /**
     * A method to get all active bookings
     *
     * @param active
     * @return
     */
    public List<BookingCalendar> getBookingsByActive(boolean active) {
        return bookingCalendarRepo.findAllByActive(active);
    }

    /**
     * A method to get booking by booking id and active
     *
     * @param id
     * @param active
     * @return
     */
    public BookingCalendar getBookingByIdAndActive(int id, boolean active) {
        return bookingCalendarRepo.findByIdAndActive(id, active);
    }

    /**
     * A method to create new booking
     *
     * @param room
     * @param checkin
     * @param checkout
     * @return
     */
    public BookingCalendar updateBooking(Room room, Date checkin, Date checkout) {
        BookingCalendar bookingCalendar = new BookingCalendar();
        bookingCalendar.setRoom(room);
        bookingCalendar.setCheckinDate(checkin);
        bookingCalendar.setCheckoutDate(checkout);
        Period period = Period.between(convertToLocalDateViaInstant(checkin),
                convertToLocalDateViaInstant(checkout));
        bookingCalendar.setPeriod(period.getDays());
        bookingCalendar.setActive(true);
        return bookingCalendarRepo.save(bookingCalendar);
    }

    /**
     * A method to delete "mark as Inactive for the data history and analytical purposes" a booking
     *
     * @param bookingCalendar
     * @return
     */
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
