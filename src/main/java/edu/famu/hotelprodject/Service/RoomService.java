package edu.famu.hotelprodject.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.hotelprodject.Models.Hotel;
import edu.famu.hotelprodject.Models.Room;
import edu.famu.hotelprodject.Models.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class RoomService {

    private final Firestore firestore;
    public RoomService()
    {
        this.firestore = FirestoreClient.getFirestore();
    }

    private static Room documentSnapshotToUser(DocumentSnapshot document)
    {
        Room rooms = null;
        if(document.exists()){
            ArrayList<String> images = (ArrayList<String>) document.get("images");
            rooms = new Room(document.getId(),document.getString("hotelID"),document.getLong("capacity"),document.getTimestamp("createdAt"),document.getString("description"),document.getString("availability"),images,document.getDouble("price"), document.getString("roomType"));
        }
        return rooms;
    }// checks if doc exist
    //room = new Room(document.getId(),document.getString("availability"),document.getLong("capacity"),document.getTimestamp("createdAt"), document.getString("description"), document.getString("image"),document.getDouble("price"), document.getString("roomType"));
    public ArrayList<Room> getAllRooms() throws ExecutionException, InterruptedException {
        CollectionReference RoomCollection  = firestore.collection("Room");
        ApiFuture<QuerySnapshot> future = RoomCollection.get();

        ArrayList<Room> roomList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
           Room room = documentSnapshotToUser(document);
            if (room != null) {
                roomList.add(room);
            }
        }
        return roomList;
    }

    public Room getRoomById(String roomId) throws ExecutionException, InterruptedException {
        CollectionReference RoomCollection = firestore.collection("Room");
        ApiFuture<DocumentSnapshot> future = RoomCollection.document(roomId).get();
        DocumentSnapshot document = future.get();;

        return documentSnapshotToUser(document);

    }// gets one passenger turns into an object




    public  String createNewRoom(Room room) throws ExecutionException, InterruptedException
    {
        String roomId = null;
        ApiFuture<DocumentReference> future = firestore.collection("Room").add(room);
        DocumentReference postRef = future.get();
        roomId = postRef.getId();
        return roomId;
    }

    public void updateRoom(String id, Map<String,String> updatedValues)
    {
        String [ ] allowed = {"availability","capacity","price","roomType"};

        List<String> list = Arrays.asList(allowed);
        Map<String,Object> formattedValues = new HashMap<>();

        for(Map.Entry<String,String> entry: updatedValues.entrySet())
        {
            String key = entry.getKey();
            if(list.contains(key))
                formattedValues.put(key,entry.getValue());
        }
        DocumentReference roomDoc = firestore.collection("Room").document(id);
        if(roomDoc != null)
            roomDoc .update(formattedValues);
    }

    public void deleteRoom(String roomId){
        DocumentReference roomDoc = firestore.collection("Room").document(roomId);
       roomDoc.delete();
    }

}
