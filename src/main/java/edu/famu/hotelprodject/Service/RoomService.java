package edu.famu.hotelprodject.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.hotelprodject.Models.Room;
import edu.famu.hotelprodject.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Room room = null;
        if(document.exists())
            room = new Room(document.getId(),document.getString("availability"),document.getLong("capacity"),document.getTimestamp("createdAt"), document.getString("description"), document.getString("image"),document.getDouble("price"), document.getString("roomType"));
        return room;
    }// checks if doc exist

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


}
