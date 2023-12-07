package edu.famu.hotelprodject.Models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
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
public class User {
    @DocumentId
   private @Nullable String userID;
   private String address;
   private Timestamp createdAt;
   private String email;
   private String name;
   private String phone;
   private PaymentInformation paymentInformation;

    public void setCreatedAt( String createdAt) throws ParseException
    {

        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }
}
