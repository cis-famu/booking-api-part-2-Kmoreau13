package     edu.famu.hotelprodject.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.hotelprodject.Models.Hotel;
import edu.famu.hotelprodject.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class HotelService {


    private final Firestore firestore;
    public HotelService()
    {
        this.firestore = FirestoreClient.getFirestore();
    }

    private static Hotel documentSnapshotToUser(DocumentSnapshot document)
    {
        Hotel hotel = null;
        ArrayList<Hotel> amenities =null;

        if(document.exists())

            hotel = new Hotel(document.getString("address"),document.getString("contactInformation"),document.getString("description"), document.getTimestamp("createdAt"),document.getId(),document.getString("name"),document.getString("rating"));
        for(Hotel x : amenities)
            {return x;}
        return hotel;

    }// checks if doc exist

    public ArrayList<Hotel> getAllHotels() throws ExecutionException, InterruptedException {
        CollectionReference HotelCollection  = firestore.collection("Hotel");
        ApiFuture<QuerySnapshot> future = HotelCollection.get();

        ArrayList<Hotel> hotelList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            Hotel hotel = documentSnapshotToUser(document);
            if (hotel != null) {
                hotelList.add(hotel);
            }
        }
        return hotelList;
    }

    public Hotel getHotelById(String hotelId) throws ExecutionException, InterruptedException {
        CollectionReference hotelCollection = firestore.collection("Hotel");
        ApiFuture<DocumentSnapshot> future = hotelCollection.document(hotelId).get();
        DocumentSnapshot document = future.get();;

        return documentSnapshotToUser(document);

    }// gets one passenger turns into an object


}
