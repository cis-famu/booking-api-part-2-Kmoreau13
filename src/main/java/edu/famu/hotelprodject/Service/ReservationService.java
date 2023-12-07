package edu.famu.hotelprodject.Service;


import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.hotelprodject.Models.Hotel;
import edu.famu.hotelprodject.Models.Reservation;
import edu.famu.hotelprodject.Models.User;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;
/*
public class ReservationService {
    private final Firestore firestore;
    public ReservationService()
    {
        this.firestore = FirestoreClient.getFirestore();
    }


    private Reservation documentSnapshotToBooking(DocumentSnapshot document) throws ExecutionException, InterruptedException, ParseException {
        if (document.exists()) {
            Reservation reservation = new Reservation();
            reservation.setReservationID(document.getId());
            reservation.updateReservationCheckIn(document.getTimestamp("checkIn"));
            reservation.setReservationCheckOut(document.getString("checkOut"));
            reservation.setTotal(document.getDouble("total"));


            // Retrieve User details
            DocumentReference customerRef = (DocumentReference) document.get("userID");
            if (customerRef != null) {
                DocumentSnapshot userSnapshot = customerRef.get().get();
                if (userSnapshot.exists()) {
                    UserService service = new UserService();
                    User user = service.documentSnapshotToUser(userSnapshot);
                    //reservation.setID(user);
                }
            }

            // Retrieve flight details
            DocumentReference hotelRef = (DocumentReference) document.get("flightNumber");
                if (hotelRef != null) {
                    DocumentSnapshot hotelSnapshot = hotelRef.get().get();
                    if (hotelSnapshot.exists()) {

                        HotelService service = new HotelService();
                       // Hotel hotel = service.documentSnapshotToHotel(hotelSnapshot);
                       // reservation.setFlightNumber(hotel);
                    }
                }
       }*/