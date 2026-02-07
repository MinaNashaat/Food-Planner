package com.mina.foodplanner.search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mina.foodplanner.R;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.home.view.onMealClick;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MealViewHolder>{
    private List<Meal> mealList;
    private onMealSelected onMealSelected;

    public SearchAdapter() {
    }

    public void setOnMealClick(onMealSelected onMealSelected) {
        this.onMealSelected = onMealSelected;
    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = mealList.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return mealList != null ? mealList.size() : 0;
    }

    public void clearList(){
        this.mealList.clear();
        notifyDataSetChanged();
    }

    class MealViewHolder extends RecyclerView.ViewHolder {

        ImageView recipeImage, favIcon;
        TextView category, recipeName, recipeInfo;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            favIcon = itemView.findViewById(R.id.fav);
            category = itemView.findViewById(R.id.tag);

            recipeName = itemView.findViewById(R.id.mealTitle);
        }

        void bind(Meal meal) {

            recipeName.setText(meal.getStrMeal());
            category.setText(meal.getStrCategory());

            Glide.with(itemView)
                    .load(meal.getStrMealThumb())
                    .into(recipeImage);

            itemView.setOnClickListener(v -> {
                onMealSelected.showMealDetails(meal);
            });

            favIcon.setOnClickListener(v -> {
                onMealSelected.addToFav(meal);
            });
        }
    }
}
