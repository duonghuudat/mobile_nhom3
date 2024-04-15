package com.example.cookingguideapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingguideapp.Adapter.CategoryAdapter;
import com.example.cookingguideapp.Adapter.RecipeFavoriteHomeListAdapter;
import com.example.cookingguideapp.Adapter.RecipeTodayAdapter;
import com.example.cookingguideapp.Domain.Model.Recipe;
import com.example.cookingguideapp.Domain.Model.Type;
import com.example.cookingguideapp.Domain.Network.FirebaseRecipe;
import com.example.cookingguideapp.Domain.Network.NetworkHelper;
import com.example.cookingguideapp.R;
import com.example.cookingguideapp.Room.AppDatabase;
import com.example.cookingguideapp.Room.DAO.RecipeDao;
import com.example.cookingguideapp.Room.Entity.RecipeEntity;
import com.example.cookingguideapp.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewRecipeTodayList;
    private RecyclerView getRecyclerViewRecipeFavoriteList;
    private ConstraintLayout searchButton;
    FragmentHomeBinding binding;
    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        recyclerViewCategory();
        if(new NetworkHelper().isNetworkConnected(getActivity())){
            recyclerViewRecipeToday();
        }else{
            ConstraintLayout today_layout = binding.recipeTodayLayout;
            today_layout.setVisibility(View.GONE);
        }

        recyclerViewRecipeFavorite();

        searchButton = binding.searchBtn;
        searchButton.setOnClickListener(v -> openSearchFragment());
        return view;
    }

    public void openSearchFragment() {
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.search);
    }

    private void recyclerViewRecipeToday() {
        //danh sach cac cong thuc
        new FirebaseRecipe().getRandomRecipes(12, recipeList -> {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewRecipeTodayList = binding.recyclerViewToday;
            recyclerViewRecipeTodayList.setLayoutManager(linearLayoutManager);

            RecipeTodayAdapter recipeTodayAdapter = new RecipeTodayAdapter(recipeList);
            recipeTodayAdapter.setOnItemClickListener(recipeId -> {
                Intent intent = new Intent(getActivity(), DetailRecipeActivity.class);
                intent.putExtra("recipeId", recipeId);
                startActivity(intent);
            });
            adapter = recipeTodayAdapter;
            recyclerViewRecipeTodayList.setAdapter(adapter);
        });

//  Test
//        ArrayList<Recipe> recipeList = new ArrayList<>();
//        recipeList.add(new Recipe("1", "Recipe 1", "Description 1", "image_url_1", "2 servings", 30, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
//        recipeList.add(new Recipe("2", "Recipe 2", "Description 2", "image_url_2", "4 servings", 45, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
//        recipeList.add(new Recipe("3", "Recipe 3", "Description 3", "image_url_3", "6 servings", 60, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
//
//        // Tạo layout cho RecyclerView
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//
//        // Thiết lập RecyclerView và Adapter
//        recyclerViewRecipeTodayList = binding.recyclerViewToday;
//        recyclerViewRecipeTodayList.setLayoutManager(linearLayoutManager);
//
//        RecipeTodayAdapter recipeTodayAdapter = new RecipeTodayAdapter(recipeList);
//            recipeTodayAdapter.setOnItemClickListener(recipeId -> {
//                Intent intent = new Intent(getActivity(), DetailRecipeActivity.class);
//                intent.putExtra("recipeId", recipeId);
//                startActivity(intent);
//            });
//
//        adapter = recipeTodayAdapter;
//        recyclerViewRecipeTodayList.setAdapter(adapter);

    }

    private void recyclerViewRecipeFavorite() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        getRecyclerViewRecipeFavoriteList = binding.recyclerViewRecipeFavorite;
        getRecyclerViewRecipeFavoriteList.setLayoutManager(linearLayoutManager);

        AppDatabase db = AppDatabase.getInstance(this.getActivity());
        RecipeDao recipeDao = db.recipeDao();
        List<Recipe> recipeList = new RecipeEntity().toRecipeList(recipeDao.getAll());

        RecipeFavoriteHomeListAdapter recipeFavoriteHomeListAdapter = new RecipeFavoriteHomeListAdapter(recipeList);
        recipeFavoriteHomeListAdapter.setOnItemClickListener(recipeId -> {
            Intent intent = new Intent(getActivity(), DetailRecipeActivity.class);
            intent.putExtra("recipeId", recipeId);
            startActivity(intent);
        });

        adapter = recipeFavoriteHomeListAdapter;
        getRecyclerViewRecipeFavoriteList.setAdapter(adapter);


    }

    private void recyclerViewCategory() {
        //danh sach cac muc
        ArrayList<Type> typeList = new ArrayList<>();
        typeList.add(new Type(0, "Món chính", "burger"));
        typeList.add(new Type(1, "Ăn vặt", "pizza"));
        typeList.add(new Type(2, "Sức khỏe", "carrot"));
        typeList.add(new Type(3, "Cơm nhà", "sushi"));
        typeList.add(new Type(4, "Món nước", "monnuoc"));
        typeList.add(new Type(5, "Món chay", "chay"));
        typeList.add(new Type(6, "Món canh", "canh"));
        typeList.add(new Type(7, "Thức uống", "nuoc"));
        typeList.add(new Type(8, "Mẹo vặt", "meovat"));
        typeList.add(new Type(9, "Món phụ", "phu"));
        int totalItems = typeList.size();
        int spanCount = (totalItems % 2 == 0) ? totalItems / 2 : (totalItems / 2) + 1;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);

        recyclerViewCategoryList = binding.recyclerViewCategory;
        recyclerViewCategoryList.setLayoutManager(gridLayoutManager);

        adapter = new CategoryAdapter(typeList);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}
