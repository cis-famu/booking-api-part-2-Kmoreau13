package edu.famu.hotelprodject.Models;

import com.google.cloud.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Reservation extends AReservation{


    private User customer;
private Hotel hotel;
private Room room;

    public Reservation(String reservationID,Timestamp checkIn, Timestamp checkOut, double total, ArrayList rooms, int adults, int children,
                       double pricePerNight, User customer, Hotel hotel, Room room) {
        super(reservationID,checkIn, checkOut, total, rooms, adults, children, pricePerNight);
        this.customer = customer;
        this.hotel = hotel;
        this.room = room;
    }
}
