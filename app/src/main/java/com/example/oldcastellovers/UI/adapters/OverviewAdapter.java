package com.example.oldcastellovers.UI.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.R;

import java.util.List;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.ViewHolder> {

    private List<String> openingHoursList;

    public OverviewAdapter(List<String> openingHoursList) {
        this.openingHoursList = openingHoursList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.opening_hours_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String openingHours = openingHoursList.get(position);
        // Assume the format is "Day: Opening hours"
        String[] parts = openingHours.split(": ");
        holder.dayTextView.setText(parts[0]);
        holder.hoursTextView.setText(parts[1]);
    }

    @Override
    public int getItemCount() {
        return openingHoursList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dayTextView;
        TextView hoursTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.dayTextView);
            hoursTextView = itemView.findViewById(R.id.hoursTextView);
        }
    }
}
