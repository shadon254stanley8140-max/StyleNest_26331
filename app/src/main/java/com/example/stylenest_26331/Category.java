package com.example.stylenest_26331;

public class Category {
    private final String name;
    private final int imageResId;
    private final String imageUrl;

    public Category(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
        this.imageUrl = null;
    }

    public Category(String name, String imageUrl) {
        this.name = name;
        this.imageResId = 0;
        this.imageUrl = imageUrl;
    }

    public String getName() { return name; }
    public int getImageResId() { return imageResId; }
    public String getImageUrl() { return imageUrl; }
}