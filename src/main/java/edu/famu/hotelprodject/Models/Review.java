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
public class Review {

    private String availability;
    private String capacity;
    private Timestamp createdAt;
    private String description;
    private String hotelID;
    private String image;
    private float price;
    private String roomID;
    private String roomType;

    public void setCreatedAt( String createdAt) throws ParseException
    {
        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }

}
