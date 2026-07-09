package com.example.stylenest_26331;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;

public class PostProductActivity extends AppCompatActivity {

    private ImageView productImageView;
    private LinearLayout placeholder;
    private TextInputEditText nameInput, priceInput, categoryInput;
    private Uri selectedImageUri;
    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_product);

        productImageView = findViewById(R.id.postProductImage);
        placeholder = findViewById(R.id.postPlaceholder);
        nameInput = findViewById(R.id.postProductName);
        priceInput = findViewById(R.id.postProductPrice);
        categoryInput = findViewById(R.id.postProductCategory);
        Button btnSubmit = findViewById(R.id.btnSubmitPost);
        ImageView btnBack = findViewById(R.id.btnPostBack);

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        selectedImageUri = result.getData().getData();
                        if (selectedImageUri != null) {
                            placeholder.setVisibility(View.GONE);
                            Glide.with(this).load(selectedImageUri).into(productImageView);
                        }
                    }
                }
        );

        findViewById(R.id.btnSelectProductImage).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            imagePickerLauncher.launch(intent);
        });

        btnSubmit.setOnClickListener(v -> {
            if (validateInputs()) {
                // Simulating product upload
                Toast.makeText(this, "Product posted successfully! New stock is live.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private boolean validateInputs() {
        if (selectedImageUri == null) {
            Toast.makeText(this, "Please select a product photo", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(Objects.requireNonNull(nameInput.getText()).toString().trim()) ||
            TextUtils.isEmpty(Objects.requireNonNull(priceInput.getText()).toString().trim()) ||
            TextUtils.isEmpty(Objects.requireNonNull(categoryInput.getText()).toString().trim())) {
            Toast.makeText(this, "Please fill in all product details", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}