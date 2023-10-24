package edu.famu.hotelprodject.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.hotelprodject.Models.PaymentInformation;

import java.util.ArrayList;
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

    public ArrayList<PaymentInformation> getPaymentInformation(DocumentReference paymentInformation) throws ExecutionException, InterruptedException {
        CollectionReference PaymentInformationCollection = firestore.collection("PaymentInformation");
        ApiFuture<QuerySnapshot> future = PaymentInformationCollection.get();
        ArrayList<PaymentInformation> PaymentInformationList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            PaymentInformation paymentinfo = documentSnapshotToPaymentInformation(document);
            if (paymentinfo  != null) {
                PaymentInformationList.add(paymentinfo );
            }
        }
        return PaymentInformationList;
    }

}
