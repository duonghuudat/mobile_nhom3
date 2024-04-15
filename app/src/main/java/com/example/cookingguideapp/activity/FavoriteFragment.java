package com.example.cookingguideapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingguideapp.Adapter.RecipeListAdapter;
import com.example.cookingguideapp.Domain.Model.Recipe;
import com.example.cookingguideapp.Room.AppDatabase;
import com.example.cookingguideapp.Room.DAO.RecipeDao;
import com.example.cookingguideapp.Room.Entity.RecipeEntity;
import com.example.cookingguideapp.databinding.FragmentFavoriteBinding;

import java.util.List;

public class FavoriteFragment extends Fragment {


    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewRecipeFavorite;
    FragmentFavoriteBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setRecyclerViewRecipeFavorite();


        return view;

    }

    private void setRecyclerViewRecipeFavorite() {
        AppDatabase db = AppDatabase.getInstance(this.getActivity());
        RecipeDao recipeDao = db.recipeDao();
        List<Recipe> recipeList = new RecipeEntity().toRecipeList(recipeDao.getAll());
        //danh sach cac cong thuc
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewRecipeFavorite = binding.recylerFavorite;
        recyclerViewRecipeFavorite.setLayoutManager(linearLayoutManager);
        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(recipeList, db);
        recipeListAdapter.setOnItemClickListener(recipeId -> {
            Intent intent = new Intent(getActivity(), DetailRecipeActivity.class);
            intent.putExtra("recipeId", recipeId);
            startActivity(intent);
        });
        recipeListAdapter.setOnFavoriteIconClickListener((position, recipeId) -> {
        });

        adapter = recipeListAdapter;
        recyclerViewRecipeFavorite.setAdapter(adapter);


    }
}