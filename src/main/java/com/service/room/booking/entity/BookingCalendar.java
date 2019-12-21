package com.service.room.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bookingcalendar")
public class BookingCalendar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "checkinDate")
    private Date checkinDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "checkoutDate")
    private Date checkoutDate;
    @Column(name = "period")
    private Integer period;
    @JsonIgnore
    @Column(name = "active")
    private Boolean active;
    @JsonIgnore
    @JoinColumn(name = "roomId", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
    @Transient
    private int roomId;

    public BookingCalendar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getRoomId() {
        if (room!=null){
            roomId = room.getId();
        }
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingCalendar that = (BookingCalendar) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(checkinDate, that.checkinDate) &&
                Objects.equals(checkoutDate, that.checkoutDate) &&
                Objects.equals(period, that.period) &&
                Objects.equals(active, that.active) &&
                Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkinDate, checkoutDate, period, active, room);
    }

    @Override
    public String toString() {
        return "BookingCalendar{" +
                "id=" + id +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", period=" + period +
                ", active=" + active +
                ", room=" + room +
                '}';
    }
}
