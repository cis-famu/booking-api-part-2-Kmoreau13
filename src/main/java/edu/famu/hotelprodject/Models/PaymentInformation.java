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
public class PaymentInformation {

    private String billingAddress;
    private String cardNumber;
    private String expDate;
    private Timestamp createdAt;

    public PaymentInformation(String billingAddress, String cardNumber, Timestamp expDate) {
    }

    public void setCreatedAt( String createdAt) throws ParseException
    {
        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }

}
