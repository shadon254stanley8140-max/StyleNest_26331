package com.example.stylenest_26331;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.categories_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        List<Category> categoryList = new ArrayList<>();
        // Professional Fashion Categories with Unsplash Images
        categoryList.add(new Category("Women", "https://images.unsplash.com/photo-1483985988355-763728e1935b?q=80&w=1000&auto=format&fit=crop"));
        categoryList.add(new Category("Men", "https://images.unsplash.com/photo-1490578474895-699cd4e2cf59?q=80&w=1000&auto=format&fit=crop"));
        categoryList.add(new Category("Kids", "https://images.unsplash.com/photo-1519706332590-c67251c19b70?q=80&w=1000&auto=format&fit=crop"));
        categoryList.add(new Category("Accessories", "https://images.unsplash.com/photo-1523206489230-c012c6e9a692?q=80&w=1000&auto=format&fit=crop"));
        categoryList.add(new Category("Shoes", "https://images.unsplash.com/photo-1542291026-7eec264c27ff?q=80&w=1000&auto=format&fit=crop"));
        categoryList.add(new Category("Jewelry", "https://images.unsplash.com/photo-1515562141207-7a88fb7ce338?q=80&w=1000&auto=format&fit=crop"));

        CategoryAdapter adapter = new CategoryAdapter(categoryList);
        recyclerView.setAdapter(adapter);
        
        // Trigger animation
        recyclerView.scheduleLayoutAnimation();

        return view;
    }
}