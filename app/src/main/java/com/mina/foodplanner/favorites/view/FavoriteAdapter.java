package com.mina.foodplanner.favorites.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mina.foodplanner.R;
import com.mina.foodplanner.data.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{
    private List<Meal> meals;
    private OnFavoriteClickListener listener;


    public FavoriteAdapter(OnFavoriteClickListener listener){
        this.meals = new ArrayList<>();
        this.listener= listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_favorite_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return meals != null ? meals.size() : 0;
    }

    public void updateMealsList(List<Meal> mealsList){
        this.meals = mealsList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mealImage, favIcon;
        TextView mealName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealImage);
            favIcon = itemView.findViewById(R.id.favIcon);
            mealName = itemView.findViewById(R.id.mealName);
        }

        void bind(Meal meal) {
            mealName.setText(meal.getStrMeal());
            Glide
                    .with(itemView.getContext())
                    .load(meal.getStrMealThumb())
                    .into(mealImage);
            favIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(meal);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.showMealDetails(meal);
                }
            });
        }
    }
}

