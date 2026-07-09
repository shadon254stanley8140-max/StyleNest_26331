package com.example.stylenest_26331;

public class Product {
    private final String id;
    private final String name;
    private final int price;
    private final int imageDrawableId;
    private final String imageUrl;

    public Product(String id, String name, int price, int imageDrawableId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageDrawableId = imageDrawableId;
        this.imageUrl = null;
    }

    public Product(String id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageDrawableId = 0;
        this.imageUrl = imageUrl;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getImageDrawableId() { return imageDrawableId; }
    public String getImageUrl() { return imageUrl; }
}