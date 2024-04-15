package com.example.cookingguideapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cookingguideapp.Domain.Model.Recipe;
import com.example.cookingguideapp.OnFavoriteIconClickListener;
import com.example.cookingguideapp.OnItemClickListener;
import com.example.cookingguideapp.R;
import com.example.cookingguideapp.Room.AppDatabase;

import java.util.List;

public class RecipeFavoriteHomeListAdapter extends RecyclerView.Adapter<RecipeFavoriteHomeListAdapter.ViewHolder> {
    List<Recipe> recipeList;
    AppDatabase db;
    private OnItemClickListener itemClickListener;
    private OnFavoriteIconClickListener favoriteIconClickListener;


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnFavoriteIconClickListener(OnFavoriteIconClickListener listener) {
        this.favoriteIconClickListener = listener;
    }

    public RecipeFavoriteHomeListAdapter(List<Recipe> recipeList ) {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_today, parent, false);
        return new ViewHolder(inflater);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(holder.getAdapterPosition());

        holder.recipeTitle.setText(recipe.getTitle());
        String imageURL = recipe.getImage();
        holder.main_layout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_bg));
        if (!imageURL.isBlank()) {
            Glide.with(holder.recipeImage.getContext())
                    .load(imageURL)
                    .into(holder.recipeImage);
        }
        holder.itemView.setOnClickListener(view -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(recipe.getId());
            }
        });
    }


    @Override
    public int getItemCount() {
        return  recipeList == null ? 0 : recipeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeTitle;
        ImageView recipeImage;
        ConstraintLayout main_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.recipe_today_title);
            recipeImage = itemView.findViewById(R.id.recipe_today_image);
            main_layout = itemView.findViewById(R.id.recipe_today_layout);
        }
    }
}

