package edu.famu.hotelprodject.Models;


import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestReservation extends AReservation{

    private DocumentReference customer;
    private DocumentReference hotel;
    private DocumentReference room;

    public void setCustomer(String customer) {
        // Perform Firebase Firestore query to retrieve DocumentReference for passengerID
       this.customer = retrieveDocumentReference("User", customer);
    }

    public void setHotel(String hotel) {
        // Perform Firebase Firestore query to retrieve DocumentReference for passengerID
        this.hotel = retrieveDocumentReference("Hotel", hotel);
    }

    public void setRoom(String room) {
        // Perform Firebase Firestore query to retrieve DocumentReference for passengerID
        this.room = retrieveDocumentReference("Room", room);
    }

    private DocumentReference retrieveDocumentReference(String collection, String id) {
        Firestore db = FirestoreClient.getFirestore();
        return db.collection(collection).document(id);
    }


}
