package edu.famu.hotelprodject.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.hotelprodject.Models.PaymentInformation;
import edu.famu.hotelprodject.Models.User;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class PaymentInformationService {

    private final Firestore firestore;

    public PaymentInformationService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    public PaymentInformation documentSnapshotToPaymentInformation(DocumentSnapshot document) {
        PaymentInformation paymentinfo = null;
        if(document.exists())
        {
            paymentinfo = new PaymentInformation(document.getString("billingAddress"),document.getString("cardNumber"),document.getTimestamp("expDate"));
        }
        return paymentinfo;
    }

    public PaymentInformation getPaymentInformation(DocumentReference docRef) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.toObject(PaymentInformation.class);

    }

/*
    public User getPaymentById(String userId) throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("PaymentInformation");
        ApiFuture<DocumentSnapshot> future = userCollection.document(userId).get();
        DocumentSnapshot document = future.get();;

      //  return documentSnapshotToPaymentInformation(document);

    }*/

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


    public void updatePayment(String id, Map<String,String> updatedValues)
    {
        String [ ] allowed = {"billingAddress"};

        List<String> list = Arrays.asList(allowed);
        Map<String,Object> formattedValues = new HashMap<>();

        for(Map.Entry<String,String> entry: updatedValues.entrySet())
        {
            String key = entry.getKey();
            if(list.contains(key))
                formattedValues.put(key,entry.getValue());
        }
        DocumentReference userDoc = firestore.collection("PaymentInformation").document(id);
        if(userDoc != null)
            userDoc.update(formattedValues);
    }

    public void deleteUser(String userId){
        DocumentReference userDoc = firestore.collection("Hotel").document(userId);
        userDoc.delete();
    }

}

