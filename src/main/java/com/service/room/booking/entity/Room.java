package com.service.room.booking.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "room")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType type;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "room")
    @Transient
    private List<BookingCalendar> bookingCalendarList;

    public Room() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public List<BookingCalendar> getBookingCalendarList() {
        return bookingCalendarList;
    }

    public void setBookingCalendarList(List<BookingCalendar> bookingCalendarList) {
        this.bookingCalendarList = bookingCalendarList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                type == room.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
