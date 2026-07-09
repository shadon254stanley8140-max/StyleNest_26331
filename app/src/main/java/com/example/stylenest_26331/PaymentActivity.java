package com.example.stylenest_26331;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;

public class PaymentActivity extends AppCompatActivity {

    private TextInputEditText cardNameInput, cardNumberInput, expiryInput, cvvInput, phoneInput, paypalEmailInput;
    private LinearLayout cardSection, mobileMoneySection, paypalSection, codSection;
    private TextView mobileMoneyHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardNameInput = findViewById(R.id.cardNameInput);
        cardNumberInput = findViewById(R.id.cardNumberInput);
        expiryInput = findViewById(R.id.expiryInput);
        cvvInput = findViewById(R.id.cvvInput);
        phoneInput = findViewById(R.id.phoneInput);
        paypalEmailInput = findViewById(R.id.paypalEmailInput);

        cardSection = findViewById(R.id.cardSection);
        mobileMoneySection = findViewById(R.id.mobileMoneySection);
        paypalSection = findViewById(R.id.paypalSection);
        codSection = findViewById(R.id.codSection);
        mobileMoneyHint = findViewById(R.id.mobileMoneyHint);

        RadioGroup paymentMethodGroup = findViewById(R.id.paymentMethodGroup);
        Button btnPayNow = findViewById(R.id.btnPayNow);

        paymentMethodGroup.setOnCheckedChangeListener((group, checkedId) -> {
            hideAllSections();
            if (checkedId == R.id.radioCard) {
                cardSection.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.radioMpesa) {
                mobileMoneySection.setVisibility(View.VISIBLE);
                mobileMoneyHint.setText(R.string.mpesa_hint);
            } else if (checkedId == R.id.radioAirtel) {
                mobileMoneySection.setVisibility(View.VISIBLE);
                mobileMoneyHint.setText(R.string.airtel_hint);
            } else if (checkedId == R.id.radioPaypal) {
                paypalSection.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.radioCod) {
                codSection.setVisibility(View.VISIBLE);
            }
        });

        btnPayNow.setOnClickListener(v -> {
            int selectedId = paymentMethodGroup.getCheckedRadioButtonId();

            if (selectedId == R.id.radioCard) {
                validateCardAndPay();
            } else if (selectedId == R.id.radioMpesa) {
                validateMobileMoneyAndPay("M-Pesa");
            } else if (selectedId == R.id.radioAirtel) {
                validateMobileMoneyAndPay("Airtel Money");
            } else if (selectedId == R.id.radioPaypal) {
                validatePaypalAndPay();
            } else {
                processPayment("Cash on Delivery");
            }
        });
    }

    private void hideAllSections() {
        cardSection.setVisibility(View.GONE);
        mobileMoneySection.setVisibility(View.GONE);
        paypalSection.setVisibility(View.GONE);
        codSection.setVisibility(View.GONE);
    }

    private void validateCardAndPay() {
        String name = Objects.requireNonNull(cardNameInput.getText()).toString().trim();
        String number = Objects.requireNonNull(cardNumberInput.getText()).toString().trim();
        String expiry = Objects.requireNonNull(expiryInput.getText()).toString().trim();
        String cvv = Objects.requireNonNull(cvvInput.getText()).toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number) || TextUtils.isEmpty(expiry) || TextUtils.isEmpty(cvv)) {
            Toast.makeText(this, "Please fill in all card details", Toast.LENGTH_SHORT).show();
        } else if (number.length() < 16) {
            Toast.makeText(this, "Invalid card number", Toast.LENGTH_SHORT).show();
        } else {
            processPayment("Credit Card");
        }
    }

    private void validateMobileMoneyAndPay(String method) {
        String phone = Objects.requireNonNull(phoneInput.getText()).toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
        } else {
            processPayment(method);
        }
    }

    private void validatePaypalAndPay() {
        String email = Objects.requireNonNull(paypalEmailInput.getText()).toString().trim();
        if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid PayPal email", Toast.LENGTH_SHORT).show();
        } else {
            processPayment("PayPal");
        }
    }

    private void processPayment(String method) {
        Toast.makeText(this, "Payment Successful via " + method + "! Thank you for shopping with StyleNest.", Toast.LENGTH_LONG).show();
        finish();
    }
}