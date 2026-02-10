package com.mina.foodplanner.areameals.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mina.foodplanner.R;
import com.mina.foodplanner.categorymeals.view.CategoryMealsAdapter;
import com.mina.foodplanner.categorymeals.view.OnCategoryMealClick;
import com.mina.foodplanner.data.model.FilteredMeal;

import java.util.List;

public class AreaMealsAdapter extends RecyclerView.Adapter<AreaMealsAdapter.MealViewHolder> {

    private List<FilteredMeal> filteredMealList;
    private OnAreaMealClick onAreaMealClick;

    public AreaMealsAdapter() {
    }

    public void setOnFilteredMealClick(OnAreaMealClick onAreaMealClick) {
        this.onAreaMealClick = onAreaMealClick;
    }

    public void setMealList(List<FilteredMeal> filteredMealList) {
        this.filteredMealList = filteredMealList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filtered_meal, parent, false);
        return new AreaMealsAdapter.MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        FilteredMeal meal = filteredMealList.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return filteredMealList != null ? filteredMealList.size() : 0;
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
                onAreaMealClick.onAreaMealClick(meal);

            });
        }
    }
}
