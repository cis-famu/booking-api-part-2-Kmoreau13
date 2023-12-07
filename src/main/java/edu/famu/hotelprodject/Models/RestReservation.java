package edu.famu.hotelprodject.Models;


import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestReservation extends AReservation{

    private DocumentReference customerRef;
    private DocumentReference hotelRef;
    private DocumentReference roomRef;

    public void setCustomer(String customerRef) {
        // Perform Firebase Firestore query to retrieve DocumentReference for passengerID
       this.customerRef = retrieveDocumentReference("User", customerRef);
    }

    public void setHotel(String hotelRef) {
        // Perform Firebase Firestore query to retrieve DocumentReference for passengerID
        this.hotelRef = retrieveDocumentReference("Hotel", hotelRef);
    }

    public void setRoom(String roomRef) {
        // Perform Firebase Firestore query to retrieve DocumentReference for passengerID
        this.roomRef = retrieveDocumentReference("Room", roomRef);
    }

    private DocumentReference retrieveDocumentReference(String collection, String id) {
        Firestore db = FirestoreClient.getFirestore();
        return db.collection(collection).document(id);
    }


}
