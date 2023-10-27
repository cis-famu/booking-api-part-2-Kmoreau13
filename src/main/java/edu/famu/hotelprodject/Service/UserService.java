package edu.famu.hotelprodject.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.hotelprodject.Models.PaymentInformation;
import edu.famu.hotelprodject.Models.User;
import edu.famu.hotelprodject.Util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final Firestore firestore;
    public UserService()
    {
        this.firestore = FirestoreClient.getFirestore();
    }

    public static User documentSnapshotToUser(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        User user = null;
        if (document.exists()) {
            PaymentInformationService paymentInformationService = new PaymentInformationService();
            ArrayList<PaymentInformation> paymentInformation = paymentInformationService.getPaymentInformation((DocumentReference) document.get("paymentInformation"));
            user = new User(document.getId(), document.getString("address"), document.getTimestamp("createdAt"), document.getString("email"), document.getString("name"),paymentInformation,document.getString("phone") );
        }
        return user;
    }// checks if doc exist
   // user = new User(document.getId(), document.getString("address"),document.getTimestamp("createdAt"), document.getString("email"),document.getString("name"),document.getString("phone"));
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


    public  String createNewUser(User user) throws ExecutionException, InterruptedException
    {
        String userId = null;
        ApiFuture<DocumentReference> future = firestore.collection("User").add(user);
        DocumentReference postRef = future.get();
        userId = postRef.getId();
        return userId;
    }

    public  String createNewReservation(User user) throws ExecutionException, InterruptedException
    {
        String userId = null;
        ApiFuture<DocumentReference> future = firestore.collection("User").add(user);
        DocumentReference postRef = future.get();
        userId = postRef.getId();
        return userId;
    }


    public void updateUser(String id, Map<String,String> updatedValues)
    {
        String [ ] allowed = {"name","email", "phone","address"};

        List<String> list = Arrays.asList(allowed);
        Map<String,Object> formattedValues = new HashMap<>();

        for(Map.Entry<String,String> entry: updatedValues.entrySet())
        {
            String key = entry.getKey();
            if(list.contains(key))
                formattedValues.put(key,entry.getValue());
        }
        DocumentReference userDoc = firestore.collection("User").document(id);
        if(userDoc != null)
            userDoc.update(formattedValues);
    }

    public void deleteUser(String userId){
        DocumentReference userDoc = firestore.collection("Hotel").document(userId);
        userDoc.delete();
    }

}
