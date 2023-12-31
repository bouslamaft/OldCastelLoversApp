package com.example.oldcastellovers.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide; // You may need to add the Glide library to your dependencies.
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.UI.activities.CastleDetailsActivity;
import com.example.oldcastellovers.UI.activities.EntryDetailsPageActivity;
import com.example.oldcastellovers.database.models.DiaryEntryModel;

import java.util.List;

public class DiaryEntryAdapter extends RecyclerView.Adapter<DiaryEntryAdapter.ViewHolder> {

    private Context context;
    private List<DiaryEntryModel> diaryEntries;

    public DiaryEntryAdapter(Context context, List<DiaryEntryModel> diaryEntries) {
        this.context = context;
        this.diaryEntries = diaryEntries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.display_entries, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiaryEntryModel entry = diaryEntries.get(position);

        // Set the values to the views in the ViewHolder
        holder.nameTextView.setText(entry.getCastleName());
        holder.addressTextView.setText(entry.getCastleLocation());
        holder.entryDate.setText(entry.getDate());

        // You'll need to implement loading images based on your data structure
        // Glide.with(context).load(entry.getMediaPath().get(0)).into(holder.photoImageView);

        // Load image using Glide
        String imagePath = entry.getMediaPath().get(0); // Assuming the first element is the image path
        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.homecastlepic) // Replace with your placeholder image
                .into(holder.photoImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, EntryDetailsPageActivity.class);
                intent.putExtra("entryid", entry.getEntryID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return diaryEntries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView addressTextView;
        TextView entryDate;
        ImageView photoImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            entryDate = itemView.findViewById(R.id.entryDate);
            photoImageView = itemView.findViewById(R.id.photoImageView);

        }
    }
}

