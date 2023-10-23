package edu.famu.hotelprodject.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.hotelprodject.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final Firestore firestore;
    public UserService()
    {
        this.firestore = FirestoreClient.getFirestore();
    }

    private static User documentSnapshotToUser(DocumentSnapshot document)
    {
        User user = null;
        if(document.exists())
            user = new User(document.getId(), document.getString("address"),document.getTimestamp("createdAt"), document.getString("email"),document.getString("name"), document.getString("phone"));
        return user;
    }// checks if doc exist

    public ArrayList<User> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference UserCollection  = firestore.collection("User");
        ApiFuture<QuerySnapshot> future = UserCollection.get();

        ArrayList<User> userList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            User user = documentSnapshotToUser(document);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }

    public User getUserById(String userId) throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("User");
        ApiFuture<DocumentSnapshot> future = userCollection.document(userId).get();
        DocumentSnapshot document = future.get();;

        return documentSnapshotToUser(document);

    }// gets one passenger turns into an object


}
