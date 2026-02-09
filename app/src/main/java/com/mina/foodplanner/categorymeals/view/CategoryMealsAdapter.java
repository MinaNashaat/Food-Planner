package com.mina.foodplanner.categorymeals.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mina.foodplanner.R;
import com.mina.foodplanner.data.model.FilteredMeal;

import java.util.List;

public class CategoryMealsAdapter extends RecyclerView.Adapter<CategoryMealsAdapter.MealViewHolder> {

    private List<FilteredMeal> dilteredMealList;
    private OnCategoryMealClick onCategoryMealClick;

    public CategoryMealsAdapter() {
    }

    public void setOnFilteredMealClick(OnCategoryMealClick onCategoryMealClick) {
        this.onCategoryMealClick = onCategoryMealClick;
    }

    public void setMealList(List<FilteredMeal> dilteredMealList) {
        this.dilteredMealList = dilteredMealList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filtered_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        FilteredMeal meal = dilteredMealList.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return dilteredMealList != null ? dilteredMealList.size() : 0;
    }

    class MealViewHolder extends RecyclerView.ViewHolder {

        ImageView mealImage;
        TextView mealName;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealImage);
            mealName = itemView.findViewById(R.id.mealName);
        }

        void bind(FilteredMeal meal) {
            mealName.setText(meal.getStrMeal());

            Glide.with(itemView)
                    .load(meal.getStrMealThumb())
                    .into(mealImage);

            itemView.setOnClickListener(v -> {
                    onCategoryMealClick.onCategoryMealClick(meal);

            });
        }
    }
}