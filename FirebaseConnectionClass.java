package goodday;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Nobu on 2017/05/06.
 */
public class FirebaseConnectionClass {

    //private final String JSON_FILE_NAME = "src/GoodDayFirebase-f6bbd78d2cde.json";
    private final String JSON_FILE_NAME = "src/goodday/ReadOnlyAccount.json";
    private final String URL = "https://gooddayfirebase.firebaseio.com/";

    private DatabaseReference reference;
    private ArrayList<String> resultContent = new ArrayList<>();

    public FirebaseConnectionClass() {
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

    public ArrayList<String> searchContentsFromFirebase(String content, String category) {
        //ArrayList<String> resultContent = new ArrayList<>();

        reference.child(content).child(category).orderByKey().startAt("0").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (int i = 0; ; i++) {
                    if (dataSnapshot.child(String.valueOf(i)).getValue() == null) break;
                    resultContent.add((String) dataSnapshot.child(String.valueOf(i)).getValue());
                }

                // Shuffle and delete over 5 items
                if (resultContent.size() > 5) {
                    Collections.shuffle(resultContent);
                    for (int i = resultContent.size() - 1; i >= 5; i--) {
                        resultContent.remove(i);
                    }
                }
                setResultContent(resultContent);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.resultContent;
    }

    public void setResultContent(ArrayList<String> resultContent){
        this.resultContent = resultContent;
    }

    public ArrayList<String> getResultContent(){
        return this.resultContent;
    }

}
