package cis3334.fitnessapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import java.util.List;

public class MainActivity extends Activity {

    Button buttonAdd, buttonDetails, buttonDelete;          // two button widgets
    ListView ListViewUsers;                                // listview to display all the users in the database
    ArrayAdapter<User> userAdapter;
    List<User> userList;
    UserFirebaseData userFirebaseData;
    DatabaseReference myUserDbRef;
    int positionSelected;
    User userSelected;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    UserFirebaseData fitnessDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetupFirebaseDataChange();
        setupListView();
        setupAddButton();
        setupDetailButton();
        setupDeleteButton();

        mAuth = FirebaseAuth.getInstance(); //declare object for Firebase

        mAuthListener = new FirebaseAuth.AuthStateListener() { //initialized mAuthListener
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //track the user when they sign in or out using the firebaseAuth
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // User is signed out
                    Log.d("CSS3334","onAuthStateChanged - User NOT is signed in");
                    Intent signInIntent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(signInIntent);
                }
            }
        };
    }

    public void onStart() {
        super.onStart(); // Creates the listner object
        mAuth.addAuthStateListener(mAuthListener); // Adds a state listener to the object
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) { // checks to see if there is a state listnerer and if there is then it will be removed
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private void SetupFirebaseDataChange() {
        fitnessDataSource = new UserFirebaseData();
        myUserDbRef = fitnessDataSource.open();
        myUserDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("CIS3334", "Starting onDataChange()");        // debugging log
                userList = fitnessDataSource.getAllUsers(dataSnapshot);
                // Instantiate a custom adapter for displaying each user
                userAdapter = new UserAdapter(MainActivity.this, android.R.layout.simple_list_item_single_choice, userList);
                // Apply the adapter to the list
                ListViewUsers.setAdapter(userAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("CIS3334", "onCancelled: ");
            }
        });
    }

    private void setupListView() {
        ListViewUsers = (ListView) findViewById(R.id.ListViewUsers);
        ListViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View parent,
                                    int position, long id) {
                positionSelected = position;
                Log.d("MAIN", "User selected at position " + positionSelected);
            }
        });
    }

    private void setupAddButton() {
        // Set up the button to add a new users using a seperate activity
        buttonAdd = (Button) findViewById(R.id.buttonAddUser);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Start up the add user activity with an intent
                Intent detailActIntent = new Intent(view.getContext(), AddInfoActivity.class);
                //detailActIntent.putExtra("fitness", fitness);
                finish();
                startActivity(detailActIntent);

            }
        });
    }

    private void setupDetailButton() {
        // Set up the button to display details on one user using a seperate activity
        buttonDetails = (Button) findViewById(R.id.buttonDetails);
        buttonDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("MAIN", "onClick for Details");
                Intent detailActIntent = new Intent(view.getContext(), UserDetailsActivity.class);
                detailActIntent.putExtra("User", userList.get(positionSelected));
                finish();
                startActivity(detailActIntent);
            }
        });
    }

    private void setupDeleteButton() {
        // Set up the button to display details on one user using a seperate activity
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MAIN", "onClick for Delete");
                Log.d("MAIN", "Delete at position " + positionSelected);
                fitnessDataSource.deleteUser(userList.get(positionSelected));
                userAdapter.remove( userList.get(positionSelected) );
                userAdapter.notifyDataSetChanged();
            }
        });
    }
}

