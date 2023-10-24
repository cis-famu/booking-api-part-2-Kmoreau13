package edu.famu.hotelprodject.Models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
@DocumentId
    private @Nullable String roomID;
    private String availability;
    private long capacity;
    private Timestamp createdAt;
    private String description;
    private String hotelID;
    private ArrayList image;
    private double price;
    private String roomType;

    public Room(String id, String availability, Long capacity, Timestamp createdAt, String description, String image, Double price, String roomType) {
    }


    public void setCreatedAt( String createdAt) throws ParseException
    {
        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }
}
