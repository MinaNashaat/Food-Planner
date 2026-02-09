package com.mina.foodplanner.planner.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mina.foodplanner.R;
import com.mina.foodplanner.data.model.PlannerDay;
import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DayViewHolder>{
    private List<PlannerDay> days;
    private OnDayClick onDayClick;

    public void setOnDayClick(OnDayClick onDayClick) {
        this.onDayClick = onDayClick;
    }

    public void setDaysList(List<PlannerDay> days) {
        this.days = days;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DaysAdapter.DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day, parent, false);

        return new DaysAdapter.DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysAdapter.DayViewHolder holder, int position) {
        PlannerDay plannerDay = days.get(position);
        holder.bind(plannerDay);
    }

    @Override
    public int getItemCount() {
        return days != null ? days.size() : 0;
    }


    public class DayViewHolder extends RecyclerView.ViewHolder{
        TextView dayName, dayNumber;
        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayName = itemView.findViewById(R.id.dayName);
            dayNumber = itemView.findViewById(R.id.dayNumber);
        }

        void bind(PlannerDay plannerDay) {
            dayName.setText(plannerDay.dayName);
            dayNumber.setText(plannerDay.dayNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDayClick.onDayClick(plannerDay);
                }
            });
        }
    }
}
