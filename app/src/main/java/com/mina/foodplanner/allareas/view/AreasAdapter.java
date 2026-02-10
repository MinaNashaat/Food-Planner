package com.mina.foodplanner.allareas.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mina.foodplanner.data.model.AreaString;

import java.util.List;

public class AreasAdapter extends RecyclerView.Adapter<AreasAdapter.AreaViewHolder> {

    private List<AreaString> areaList;
    private OnAreaClick onAreaClick;

    public void setAreaList(List<AreaString> areaList) {
        this.areaList = areaList;
        notifyDataSetChanged();
    }

    public void setOnAreaClick(OnAreaClick onAreaClick) {
        this.onAreaClick = onAreaClick;
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new AreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        holder.bind(areaList.get(position));
    }

    @Override
    public int getItemCount() {
        return areaList != null ? areaList.size() : 0;
    }

    class AreaViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }

        void bind(AreaString area) {
            textView.setText(area.getStrArea());
            textView.setTextColor(
                    itemView.getContext().getResources()
                            .getColor(android.R.color.white)
            );
            itemView.setOnClickListener(v -> {
                onAreaClick.onAreaClick(area.getStrArea());
            });
        }
    }
}