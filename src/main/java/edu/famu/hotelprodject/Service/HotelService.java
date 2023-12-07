package     edu.famu.hotelprodject.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.hotelprodject.Models.Hotel;
import edu.famu.hotelprodject.Models.Room;
import edu.famu.hotelprodject.Models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

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
            hotels = new Hotel(document.getId(),document.getString("address"),amenities,document.getString("contactInformation"),document.getTimestamp("createdAt"),document.getString("description"),document.getString("name"),document.getString("rating") );
        }
        return hotels;

    }// checks if doc exist


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

        String [ ] allowed = {"name","rating", "description","address,contactinformation"};
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
    @DeleteMapping("/{hotelId}")
    public void deleteHotel(String hotelId){
        DocumentReference hotelDoc = firestore.collection("Hotel").document(hotelId);
        hotelDoc.delete();
    }

}
