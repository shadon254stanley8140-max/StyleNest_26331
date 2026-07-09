package com.example.stylenest_26331;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {

    private ImageView uploadedImageView;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private SharedPreferences userPrefs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userPrefs = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        if (imageUri != null) {
                            // Persist image URI
                            userPrefs.edit().putString("profile_image_uri", imageUri.toString()).apply();
                            loadProfileImage(imageUri.toString());
                            Toast.makeText(getContext(), "Profile picture updated!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        uploadedImageView = view.findViewById(R.id.uploadedImageView);
        TextView profileName = view.findViewById(R.id.profileName);
        TextView profileEmail = view.findViewById(R.id.profileEmail);
        Button btnUploadImage = view.findViewById(R.id.btnUploadImage);
        Button btnPostProduct = view.findViewById(R.id.btnPostProduct);
        Button btnSignOut = view.findViewById(R.id.btnSignOut);

        // Load User Info from registration
        String name = userPrefs.getString("name", "User Name");
        String email = userPrefs.getString("email", "user@email.com");
        String imageUriStr = userPrefs.getString("profile_image_uri", null);

        profileName.setText(name);
        profileEmail.setText(email);
        
        if (imageUriStr != null) {
            loadProfileImage(imageUriStr);
        }

        btnUploadImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            galleryLauncher.launch(intent);
        });

        btnPostProduct.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PostProductActivity.class);
            startActivity(intent);
        });

        btnSignOut.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        return view;
    }

    private void loadProfileImage(String uriStr) {
        if (uploadedImageView != null) {
            Glide.with(this)
                    .load(uriStr)
                    .placeholder(R.drawable.ic_profile)
                    .circleCrop()
                    .into(uploadedImageView);
        }
    }
}