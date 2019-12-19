package com.service.room.booking.repository;

import com.service.room.booking.entity.BookingCalendar;
import com.service.room.booking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingCalendarRepo extends JpaRepository<BookingCalendar, Integer> {

    List<BookingCalendar> findAllByRoomAndActive(Room roomId, boolean active);

    List<BookingCalendar> findAllByActive(boolean active);

    BookingCalendar findByIdAndActive(int id, boolean active);
}
