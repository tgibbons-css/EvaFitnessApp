package cis3334.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import static cis3334.fitnessapp.R.id.buttonSave;


public class AddInfoActivity extends AppCompatActivity {

    EditText name, age, height, weight, heartRate, bloodPressure, date;
    Button save;
    FitnessFirebaseData fitnessDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        //link each editText variable to the xml layout
        name = (EditText) findViewById(R.id.editTextName);
        age = (EditText) findViewById(R.id.editTextAge);
        height = (EditText) findViewById(R.id.editTextHeight);
        weight = (EditText) findViewById(R.id.editTextWeight);
        heartRate = (EditText) findViewById(R.id.editTextHeartRate);
        bloodPressure = (EditText) findViewById(R.id.editTextBloodPressure);
        date = (EditText) findViewById(R.id.editTextDate);
        save = (Button) findViewById(buttonSave);

        fitnessDataSource = new FitnessFirebaseData();
        fitnessDatasource.open();

    }

    // set up the button listener
    //save = (Button) findViewById(buttonSave);
    buttonSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            // Add the user to the database
            String Name = name.getText().toString();
            String Age = age.getText().toString();
            String Height = height.getText().toString();
            String Weight = weight.getText().toString();
            String BloodPressure = bloodPressure.getText().toString();
            String HeartRate = heartRate.getText().toString();
            String Date = date.getText().toString();

            //UserDataSource.createUser(name, age, height, weight, date);
            fitnessDataSource.createUser(Name, Age, Height, Weight, BloodPressure, HeartRate, Date);
            Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
            finish();
            startActivity(mainActIntent);
        }
    }

}

