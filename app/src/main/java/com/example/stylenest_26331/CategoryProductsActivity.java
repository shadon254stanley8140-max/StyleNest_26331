package com.example.stylenest_26331;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CategoryProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_products);

        String categoryName = getIntent().getStringExtra("CATEGORY_NAME");
        TextView title = findViewById(R.id.categoryTitle);
        title.setText(categoryName);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.products_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<Product> products = getProductsForCategory(categoryName);
        ProductAdapter adapter = new ProductAdapter(products);
        recyclerView.setAdapter(adapter);
        
        recyclerView.scheduleLayoutAnimation();
    }

    private List<Product> getProductsForCategory(String category) {
        List<Product> list = new ArrayList<>();
        if ("Women".equals(category)) {
            list.add(new Product("w1", "Floral Maxi Dress", 4500, "https://images.unsplash.com/photo-1572804013309-59a88b7e92f1?q=80&w=1000&auto=format&fit=crop"));
            list.add(new Product("w2", "Satin Midi Skirt", 3200, "https://images.unsplash.com/photo-1583337130417-3346a1be7dee?q=80&w=1000&auto=format&fit=crop"));
            list.add(new Product("w3", "Casual Linen Shirt", 2800, "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?q=80&w=1000&auto=format&fit=crop"));
            list.add(new Product("w4", "Elegant Evening Gown", 12000, "https://images.unsplash.com/photo-1566174053879-31528523f8ae?q=80&w=1000&auto=format&fit=crop"));
        } else if ("Men".equals(category)) {
            list.add(new Product("m1", "Slim Fit Navy Suit", 15000, "https://images.unsplash.com/photo-1594932224456-802d92671b9b?q=80&w=1000&auto=format&fit=crop"));
            list.add(new Product("m2", "Cotton Polo Shirt", 2500, "https://images.unsplash.com/photo-1581655353564-df123a1eb820?q=80&w=1000&auto=format&fit=crop"));
            list.add(new Product("m3", "Classic Leather Belt", 1800, "https://images.unsplash.com/photo-1624222247344-550fb60583dc?q=80&w=1000&auto=format&fit=crop"));
            list.add(new Product("m4", "Chino Trousers", 4000, "https://images.unsplash.com/photo-1473966968600-fa804b86827b?q=80&w=1000&auto=format&fit=crop"));
        } else if ("Kids".equals(category)) {
            list.add(new Product("k1", "Cotton Dungarees", 2200, "https://images.unsplash.com/photo-1519706332590-c67251c19b70?q=80&w=1000&auto=format&fit=crop"));
            list.add(new Product("k2", "Striped T-shirt", 1200, "https://images.unsplash.com/photo-1522771935876-249711cdbb2a?q=80&w=1000&auto=format&fit=crop"));
            list.add(new Product("k3", "Toddler Sneakers", 3000, "https://images.unsplash.com/photo-1514989940723-e8e51635b782?q=80&w=1000&auto=format&fit=crop"));
        } else {
            // Default placeholder products
            list.add(new Product("p1", "Stylish Item", 2000, "https://images.unsplash.com/photo-1523275335684-37898b6baf30?q=80&w=1000&auto=format&fit=crop"));
        }
        return list;
    }
}