package com.example.stylenest_26331;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ProductAdapter featuredAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        EditText searchInput = view.findViewById(R.id.search_input);
        RecyclerView newArrivalsRecycler = view.findViewById(R.id.new_arrivals_recycler);
        newArrivalsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        RecyclerView recyclerView = view.findViewById(R.id.featured_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // High-quality clothing images for New Arrivals
        List<Product> newArrivalsList = new ArrayList<>();
        newArrivalsList.add(new Product("n1", "Silk Satin Blouse", 3500, "https://images.unsplash.com/photo-1583337130417-3346a1be7dee?q=80&w=1000&auto=format&fit=crop"));
        newArrivalsList.add(new Product("n2", "Men's Linen Blazer", 8500, "https://images.unsplash.com/photo-1507679799987-c73779587ccf?q=80&w=1000&auto=format&fit=crop"));
        newArrivalsList.add(new Product("n3", "Kids' Denim Overall", 2800, "https://images.unsplash.com/photo-1519706332590-c67251c19b70?q=80&w=1000&auto=format&fit=crop"));
        newArrivalsList.add(new Product("n4", "Floral Summer Dress", 4200, "https://images.unsplash.com/photo-1572804013309-59a88b7e92f1?q=80&w=1000&auto=format&fit=crop"));
        
        ProductAdapter arrivalsAdapter = new ProductAdapter(newArrivalsList);
        newArrivalsRecycler.setAdapter(arrivalsAdapter);

        // Featured Collections
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("1", "Men's Urban Hoodie", 3800, "https://images.unsplash.com/photo-1556821840-3a63f95609a7?q=80&w=1000&auto=format&fit=crop"));
        productList.add(new Product("2", "Women's Leather Boots", 9500, "https://images.unsplash.com/photo-1638247025967-b4e38f787b76?q=80&w=1000&auto=format&fit=crop"));
        productList.add(new Product("3", "Kids' Party Dress", 3500, "https://images.unsplash.com/photo-1518831959646-742c3a14ebf7?q=80&w=1000&auto=format&fit=crop"));
        productList.add(new Product("4", "Men's Cargo Pants", 4200, "https://images.unsplash.com/photo-1624371414361-e6e8ea06255d?q=80&w=1000&auto=format&fit=crop"));
        productList.add(new Product("5", "Women's Trench Coat", 12000, "https://images.unsplash.com/photo-1591047139829-d91aecb6caea?q=80&w=1000&auto=format&fit=crop"));
        productList.add(new Product("6", "Kids' Cotton Tee", 1500, "https://images.unsplash.com/photo-1522771935876-249711cdbb2a?q=80&w=1000&auto=format&fit=crop"));

        featuredAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(featuredAdapter);

        // Search Implementation
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                featuredAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        newArrivalsRecycler.scheduleLayoutAnimation();
        recyclerView.scheduleLayoutAnimation();

        return view;
    }
}