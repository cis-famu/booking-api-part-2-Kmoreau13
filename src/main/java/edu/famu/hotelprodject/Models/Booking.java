package edu.famu.hotelprodject.Models;

import com.google.cloud.Timestamp;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    private String bookingID;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private Timestamp createdAt;
    private String paymentStatus;
    private String status;
    private float totalPrice;
    private String userID;

    public void setCheckInDate( String checkInDate) throws ParseException
    {

        this.checkInDate = Timestamp.fromProto(Timestamps.parse(checkInDate));
    }

    public void setCheckOutDate( String checkOutDate) throws ParseException
    {

        this.checkOutDate = Timestamp.fromProto(Timestamps.parse(checkOutDate));
    }

    public void setCreatedAt( String createdAt) throws ParseException
    {

        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }
}
