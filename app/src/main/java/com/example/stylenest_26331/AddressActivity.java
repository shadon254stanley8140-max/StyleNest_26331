package com.example.stylenest_26331;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;

public class AddressActivity extends AppCompatActivity {

    private TextInputEditText addressInput, buildingInput, locationInput, countyInput, countryInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        addressInput = findViewById(R.id.addressInput);
        buildingInput = findViewById(R.id.buildingInput);
        locationInput = findViewById(R.id.locationInput);
        countyInput = findViewById(R.id.countyInput);
        countryInput = findViewById(R.id.countryInput);
        Button btnContinue = findViewById(R.id.btnContinueToPayment);

        btnContinue.setOnClickListener(v -> {
            if (validateInputs()) {
                Intent intent = new Intent(AddressActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateInputs() {
        String address = Objects.requireNonNull(addressInput.getText()).toString().trim();
        // Building is optional
        String location = Objects.requireNonNull(locationInput.getText()).toString().trim();
        String county = Objects.requireNonNull(countyInput.getText()).toString().trim();
        String country = Objects.requireNonNull(countryInput.getText()).toString().trim();

        if (TextUtils.isEmpty(address) || TextUtils.isEmpty(location) || 
            TextUtils.isEmpty(county) || TextUtils.isEmpty(country)) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}