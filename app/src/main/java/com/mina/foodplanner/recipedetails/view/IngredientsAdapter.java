package com.mina.foodplanner.recipedetails.view;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mina.foodplanner.R;
import com.mina.foodplanner.data.IngredientsRepo;
import com.mina.foodplanner.data.model.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    private List<Pair<String, String>> ingredientsList;

    public IngredientsAdapter() {
    }

    public void setIngredientsList(List<Pair<String, String>> ingredientsList) {
        this.ingredientsList = ingredientsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient_small, parent, false);

        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Pair<String, String> ingredient = ingredientsList.get(position);
        holder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredientsList != null ? ingredientsList.size() : 0;
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        ImageView ingredientImage;
        TextView ingredientName;
        TextView ingredientMeasure;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage = itemView.findViewById(R.id.ingredientImage);
            ingredientName = itemView.findViewById(R.id.ingredientName);
            ingredientMeasure = itemView.findViewById(R.id.ingredientMeasure);
        }

        void bind(Pair<String, String> ingredient) {
            ingredientName.setText(ingredient.first);
            ingredientMeasure.setText(ingredient.second);

            Ingredient oneIngredient = IngredientsRepo.getInstance().getIngredientByName(ingredient.first);

            if (oneIngredient != null) {
                Glide.with(itemView)
                        .load(oneIngredient.getStrThumb())
                        .into(ingredientImage);
            }

        }
    }
}
