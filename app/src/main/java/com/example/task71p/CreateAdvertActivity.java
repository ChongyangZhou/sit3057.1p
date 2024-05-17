package com.example.task71p;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAdvertActivity extends AppCompatActivity {
    private EditText nameEditText, phoneEditText, descriptionEditText, dateEditText, locationEditText;
    private RadioGroup radioGroup;
    private RadioButton rbLost, rbFound;
    private Button submitButton;
    private DataBase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        // Initialize views
        nameEditText = findViewById(R.id.name);
        phoneEditText = findViewById(R.id.phone);
        descriptionEditText = findViewById(R.id.description);
        dateEditText = findViewById(R.id.date);
        locationEditText = findViewById(R.id.location);
        radioGroup = findViewById(R.id.radioGroup);
        rbLost = findViewById(R.id.rb_lost);
        rbFound = findViewById(R.id.rb_found);
        submitButton = findViewById(R.id.btn_submit);

        // Initialize database
        db = new DataBase(this);

        // Set submit button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from views
                String type = rbLost.isChecked() ? "Lost" : "Found";
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String date = dateEditText.getText().toString();
                String location = locationEditText.getText().toString();

                // Insert data into database
                long result = db.insertData(type, name, phone, description, date, location);

                if (result != -1) {
                    Toast.makeText(CreateAdvertActivity.this, "Advert created successfully!", Toast.LENGTH_SHORT).show();
                    // Clear fields after successful insert
                    nameEditText.setText("");
                    phoneEditText.setText("");
                    descriptionEditText.setText("");
                    dateEditText.setText("");
                    locationEditText.setText("");
                    startActivity(new Intent().setClass(CreateAdvertActivity.this,MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(CreateAdvertActivity.this, "Failed to create advert.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
