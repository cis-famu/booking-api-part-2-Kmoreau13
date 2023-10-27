package edu.famu.hotelprodject.Models;


import com.google.cloud.Timestamp;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AReservation {

protected Timestamp checkIn;
protected Timestamp checkOut;
protected double total;
protected ArrayList rooms;
protected int adults;
protected int children;
protected double pricePerNight;

    public void setReservationCheckIn(String checkIn) throws ParseException {
        this.checkIn = Timestamp.fromProto(Timestamps.parse(checkIn));
    }

    public void updateReservationCheckIn(Timestamp checkIn) { this.checkIn = checkIn; }


    public void setReservationCheckOut(String checkOut) throws ParseException {
        this.checkOut = Timestamp.fromProto(Timestamps.parse(checkOut));
    }

    public void updateReservationCheckOut(Timestamp checkOut) { this.checkOut = checkOut; }

}
