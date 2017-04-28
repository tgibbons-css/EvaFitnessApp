package cis3334.fitnessapp;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by evangelistachicheko on 4/27/17.
 */


public class UserFirebaseData {
    DatabaseReference myUserDbRef;
    public static final String UserDataTag = "User Data";

    public DatabaseReference open() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myUserDbRef = database.getReference(UserDataTag);
        return myUserDbRef;
    }

    public void close() {

    }

    public User createUser(String name, String age, String weight, String height, String bloodPressure, String heartRate, String date) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = myUserDbRef.child(UserDataTag).push().getKey();
        // ---- set up the user object
        User newUser = new User(key, name, age, weight, height, bloodPressure, heartRate, date);
        // ---- write the vote to Firebase
        myUserDbRef.child(key).setValue(newUser);
        return newUser;
    }

    /*public User createUser( String name, String weightInOz, String dateCaught, String locationLatitude, String locationLongitude) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = myFishDbRef.child(FishDataTag).push().getKey();
        // ---- set up the fish object
        Fish newFish = new Fish(key, species, weightInOz, dateCaught, locationLatitude,locationLongitude);
        // ---- write the vote to Firebase
        myFishDbRef.child(key).setValue(newFish);
        return newFish;
    }*/

    public void deleteUser(User user) {
        String key = user.getKey();
        myUserDbRef.child(key).removeValue();
    }

    public List<User> getAllUsers(DataSnapshot dataSnapshot) {
        List<User> userList = new ArrayList<User>();
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            User user = data.getValue(User.class);
            userList.add(user);
        }
        return userList;
    }

}
