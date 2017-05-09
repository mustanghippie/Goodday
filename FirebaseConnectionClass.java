package goodday;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nobu on 2017/05/06.
 */
public class FirebaseConnectionClass {

    //private final String KEY = "src/GoodDayFirebase-f6bbd78d2cde.json";
    private final String KEY = "src/goodday/files/ReadOnlyAccount.json";
    private final String URL = "https://gooddayfirebase.firebaseio.com/";

    private List<String> contents = new ArrayList<String>() {{
        add("Activities");
        add("Items");
        add("WindCondition");
    }};
    private DatabaseReference reference;

    public FirebaseConnectionClass() {
        InputStream stream_json = null;
        try {
            stream_json = new FileInputStream(KEY);
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

        boolean fileMakeFlag = false;

        try {
            File fileCheck = new File("src/goodday/files/Contents.json");
            FileReader readCheck = new FileReader(fileCheck);
        } catch (FileNotFoundException e) {
            fileMakeFlag = true;
        }
        if (fileMakeFlag) {
            // --Starts to make json file-- //
            reference.orderByKey().startAt("0").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    makeContentsJson(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            for (; ; ) {
                try {
                    Thread.sleep(500);
                    File fileCheck = new File("src/goodday/files/Contents.json");
                    FileReader readCheck = new FileReader(fileCheck);
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    // Loop til make a file
                }
            }
        }


        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //System.out.println("Added");
                updateContentsJson(dataSnapshot, dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //System.out.println("Changed");
                updateContentsJson(dataSnapshot, dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //System.out.println("Removed");
                updateContentsJson(dataSnapshot, dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //System.out.println("Moved");
                updateContentsJson(dataSnapshot, dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        //reference.addChildEventListener(childEventListener);

    }

    private void updateContentsJson(DataSnapshot dataSnapshot, String key) {
        //System.out.println(dataSnapshot.child("Category1").getValue());

        try {
            File file = new File("src/goodday/files/Contents.json");
            FileReader fileReader = new FileReader(file);

            BufferedReader br = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONObject json = JSONObject.fromObject(sb.toString());

            System.out.println();

            String jsonCode = "{";

            for (int i = 1; ; i++) { // Category stats from 1
                if (dataSnapshot.child("Category" + String.valueOf(i)).getValue() == null) break;
                jsonCode += "\"Category" + String.valueOf(i) + "\":{";
                for (int j = 0; ; j++) {
                    if (dataSnapshot.child("Category" + String.valueOf(i)).child(String.valueOf(j)).getValue() == null)
                        break;
                    jsonCode += "\"" + j + "\":\"" + (String) dataSnapshot.child("Category" + String.valueOf(i)).child(String.valueOf(j)).getValue() + "\",";
                }
                jsonCode = jsonCode.substring(0, jsonCode.length() - 1);
                jsonCode += "},";
            }
            jsonCode = jsonCode.substring(0, jsonCode.length() - 1);
            jsonCode += "}";

            json.getJSONArray(key).set(0, jsonCode);

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(json.toString(4));
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void makeContentsJson(DataSnapshot dataSnapshot) {
        String jsonCode = "{";

        for (String content : contents) {
            jsonCode += "\"" + content + "\":[{";
            for (int i = 1; ; i++) { // Category stats from 1
                if (dataSnapshot.child(content).child("Category" + String.valueOf(i)).getValue() == null) break;
                jsonCode += "\"Category" + String.valueOf(i) + "\":{";
                for (int j = 0; ; j++) {
                    if (dataSnapshot.child(content).child("Category" + String.valueOf(i)).child(String.valueOf(j)).getValue() == null)
                        break;
                    jsonCode += "\"" + j + "\":\"" + (String) dataSnapshot.child(content).child("Category" + String.valueOf(i)).child(String.valueOf(j)).getValue() + "\",";
                }
                jsonCode = jsonCode.substring(0, jsonCode.length() - 1);
                jsonCode += "},";
            }
            jsonCode = jsonCode.substring(0, jsonCode.length() - 1);
            jsonCode += "}],";
        }
        jsonCode = jsonCode.substring(0, jsonCode.length() - 1);
        jsonCode += "}";

        JSONObject json = JSONObject.fromObject(jsonCode);

        try {
            File file = new File("src/goodday/files/Contents.json");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(json.toString(4));
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}