package goodday;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Nobu on 2017/05/06.
 */
public class FirebaseConnectionClass {

    //private final String JSON_FILE_NAME = "src/GoodDayFirebase-f6bbd78d2cde.json";
    private final String JSON_FILE_NAME = "src/goodday/ReadOnlyAccount.json";
    private final String URL = "https://gooddayfirebase.firebaseio.com/";

    private DatabaseReference reference;

    public FirebaseConnectionClass(){
        InputStream stream_json = null;
        try {
            stream_json = new FileInputStream(JSON_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileNotFound@FirebaseConnectionClass");
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(stream_json)
                .setDatabaseUrl(URL)
                .build();

        // Initialize
        FirebaseApp.initializeApp(options);

        this.reference = FirebaseDatabase.getInstance().getReference();

    }

    public void searchContentsFromFirebase(String content, int category){
        ArrayList<String> resultContent = new ArrayList<>();

        reference.child(content).child("Category" +String.valueOf(category)).orderByKey().equalTo("0").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
