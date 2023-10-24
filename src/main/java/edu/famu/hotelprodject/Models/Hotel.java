package edu.famu.hotelprodject.Models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
@DocumentId
    private @Nullable String hotelID;
    private String address;
    private ArrayList amenities;
    private String contactInformation;
    private Timestamp createdAt;
    private String description;
    private String name;
    private String rating;

    public Hotel(String address, String contactInformation, String description, Timestamp createdAt, String id, String name, String rating) {
    }
    public void setCreatedAt(String createdAt) throws ParseException
    {
        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }
}
