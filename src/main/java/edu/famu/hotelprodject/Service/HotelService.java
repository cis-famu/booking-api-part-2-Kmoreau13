package     edu.famu.hotelprodject.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.hotelprodject.Models.Hotel;
import edu.famu.hotelprodject.Models.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class HotelService {


    private final Firestore firestore;
    public HotelService()
    {
        this.firestore = FirestoreClient.getFirestore();
    }

    private static Hotel documentSnapshotToHotel(DocumentSnapshot document)
    {
        Hotel hotels = null;
        if(document.exists()){
            ArrayList<String> amenities = (ArrayList<String>) document.get("amenities");
            hotels = new Hotel(document.getString("address"),document.getString("contactInformation"),amenities,document.getString("description"),document.getTimestamp("createdAt"),document.getId(),document.getString("name"),document.getString("rating") );
        }
        return hotels;

    }// checks if doc exist
    //hotel = new Hotel(document.getString("address"),document.getString("contactInformation"),document.getString("amenities"),document.getString("description"), document.getTimestamp("createdAt"),document.getId(),document.getString("name"),document.getString("rating"));

    public ArrayList<Hotel> getAllHotels() throws ExecutionException, InterruptedException {
        CollectionReference HotelCollection  = firestore.collection("Hotel");
        ApiFuture<QuerySnapshot> future = HotelCollection.get();

        ArrayList<Hotel> hotelList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            Hotel hotel = documentSnapshotToHotel(document);
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

        return documentSnapshotToHotel(document);

    }// gets one passenger turns into an object

    public String createNewHotel(Hotel hotel) throws ExecutionException, InterruptedException
    {
        String hotelId = null;
        ApiFuture<DocumentReference> future = firestore.collection("Hotel").add(hotel);
        DocumentReference postRef = future.get();
        hotelId = postRef.getId();
        return hotelId;
    }

    public void updateHotel(String id, Map<String,String> updatedValues)
    {
        String [ ] allowed = {"name","rating", "description","address"};

        List<String> list = Arrays.asList(allowed);
        Map<String,Object> formattedValues = new HashMap<>();

        for(Map.Entry<String,String> entry: updatedValues.entrySet())
        {
            String key = entry.getKey();
            if(list.contains(key))
                formattedValues.put(key,entry.getValue());
        }
        DocumentReference hotelDoc = firestore.collection("Hotel").document(id);
        if(hotelDoc != null)
            hotelDoc.update(formattedValues);
    }

}
