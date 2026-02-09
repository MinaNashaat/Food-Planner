package com.mina.foodplanner.planner.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mina.foodplanner.R;
import com.mina.foodplanner.data.model.UserPlannedMeal;

import java.util.List;

public class WeeklyPlannerAdapter extends RecyclerView.Adapter<WeeklyPlannerAdapter.PlannerViewHolder> {

    private List<UserPlannedMeal> plannedMeals;
    private onUserPlannedMealClick onUserPlannedMealClick;

    public void setOnUserPlannedMealClick(onUserPlannedMealClick onUserPlannedMealClick) {
        this.onUserPlannedMealClick = onUserPlannedMealClick;
    }

    public void setUserPlannedMealsList(List<UserPlannedMeal> plannedMeals) {
        this.plannedMeals = plannedMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeeklyPlannerAdapter.PlannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);

        return new WeeklyPlannerAdapter.PlannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyPlannerAdapter.PlannerViewHolder holder, int position) {
        UserPlannedMeal userPlannedMeal = plannedMeals.get(position);
        holder.bind(userPlannedMeal);
    }

    @Override
    public int getItemCount() {
        return plannedMeals != null ? plannedMeals.size() : 0;
    }

    public class PlannerViewHolder extends RecyclerView.ViewHolder{
        ImageView image, remove;
        TextView name, category;
        public PlannerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            remove = itemView.findViewById(R.id.remove);
            name = itemView.findViewById(R.id.name);
            category = itemView.findViewById(R.id.category);
        }

        void bind(UserPlannedMeal userPlannedMeal) {
            name.setText(userPlannedMeal.getStrMeal());
            category.setText(userPlannedMeal.getStrCategory());
            Glide.with(itemView)
                    .load(userPlannedMeal.getStrMealThumb())
                    .into(image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUserPlannedMealClick.onMealImageClick(userPlannedMeal.getMeal());
                }
            });
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUserPlannedMealClick.onRemoveIconClick(userPlannedMeal);
                }
            });
        }
    }
}
