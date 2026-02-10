package com.mina.foodplanner.allingredients.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mina.foodplanner.R;
import com.mina.foodplanner.data.model.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredientList;
    private OnIngredientClick onIngredientClick;

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
        notifyDataSetChanged();
    }

    public void setOnIngredientClick(OnIngredientClick onIngredientClick) {
        this.onIngredientClick = onIngredientClick;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.bind(ingredientList.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredientList != null ? ingredientList.size() : 0;
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        ImageView ingredientImage;
        TextView ingredientName;

        IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage = itemView.findViewById(R.id.ingredientImage);
            ingredientName = itemView.findViewById(R.id.ingredientName);
        }

        void bind(Ingredient ingredient) {
            ingredientName.setText(ingredient.getStrIngredient());

            Glide.with(itemView)
                    .load(ingredient.getStrThumb())
                    .into(ingredientImage);

            itemView.setOnClickListener(v -> {
                if (onIngredientClick != null) {
                    onIngredientClick.onIngredientSelected(ingredient);
                }
            });
        }
    }
}

