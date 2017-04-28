package cis3334.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserDetailsActivity extends AppCompatActivity {

    Button buttonBack;
    EditText editTextName, editTextAge, editTextWeight, editTextHeight, editTextHeartRate, editTextBloodPressure, editTextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        Bundle extras = getIntent().getExtras();
        User user = (User) extras.getSerializable("User");


        // link each editText variable to the xml layout
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextHeight = (EditText) findViewById(R.id.editTextHeight);
        editTextHeartRate = (EditText) findViewById(R.id.editTextHeartRate);
        editTextBloodPressure = (EditText) findViewById(R.id.editTextBloodPressure);

        editTextName.setText(user.getName());
        editTextWeight.setText(user.getWeight());
        editTextDate.setText(user.getDate());
        editTextAge.setText(user.getAge());
        editTextHeight.setText(user.getHeight());
        editTextBloodPressure.setText(user.getBloodPressure());
        editTextHeartRate.setText(user.getHeartRate());

        // set up the button listener
        buttonBack = (Button) findViewById(R.id.buttonReturn);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);
            }
        });

    }
}
