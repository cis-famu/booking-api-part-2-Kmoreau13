package edu.famu.hotelprodject.Service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.hotelprodject.Models.Reservation;
import edu.famu.hotelprodject.Models.User;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;

public class ReservationService {

    private Firestore firestore;

    public ReservationService(){
        this.firestore = FirestoreClient.getFirestore();
    }
    private Reservation documentSnapshotToBooking(DocumentSnapshot document) throws ExecutionException, InterruptedException, ParseException {
        if (document.exists()) {
            Reservation reservation = new Reservation();
            reservation.setReservationCheckIn(document.getString("checkIn"));
            reservation.updateReservationCheckIn(document.getTimestamp("checkIn"));
            reservation.setReservationCheckOut(document.getString("checkOut"));
            reservation.updateReservationCheckOut(document.getTimestamp("checkOut"));
            reservation.setTotal(document.getDouble("total"));



            // Retrieve passenger details
            DocumentReference userRef = (DocumentReference) document.get("customer");
            if (userRef != null) {
                DocumentSnapshot passengerSnapshot = userRef.get().get();
                if (passengerSnapshot.exists()) {
                    UserService service = new UserService();
                    User user = UserService.documentSnapshotToUser(UserSnapshot);
                    reservation.setCustomer(User);
                }
            }

            // Retrieve flight details
            DocumentReference flightRef = (DocumentReference) document.get("flightNumber");
            if (flightRef != null) {
                DocumentSnapshot flightSnapshot = flightRef.get().get();
                if (flightSnapshot.exists()) {
                    FlightService service = new FlightService();
                    Flight flight = service.documentSnapshotToFlight(flightSnapshot);
                    booking.setFlightNumber(flight);
                }
            }

            // Retrieve aircraft details
            DocumentReference paymentRef = (DocumentReference) document.get("paymentID");
            if (paymentRef != null) {
                DocumentSnapshot paymentSnapshot = paymentRef.get().get();
                if (paymentSnapshot.exists()) {
                    PaymentService service = new PaymentService();
                    Payment payment = service.documentSnapshotToPayment(paymentSnapshot);
                    booking.setPaymentID(payment);
                }
            }

            return booking;
        }
        return null;
    }


}
