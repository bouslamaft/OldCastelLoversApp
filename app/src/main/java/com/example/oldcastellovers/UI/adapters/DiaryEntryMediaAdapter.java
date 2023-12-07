package com.example.oldcastellovers.UI.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.database.models.DiaryEntryModel;

import java.util.ArrayList;
import java.util.List;

public class DiaryEntryMediaAdapter extends RecyclerView.Adapter<DiaryEntryMediaAdapter.DiaryViewHolder> {

    private Context context;
    private ArrayList<String> diaryEntries;

    public DiaryEntryMediaAdapter(Context context, ArrayList<String> diaryEntries) {
        this.context = context;
        this.diaryEntries = diaryEntries;
    }

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_item, parent, false);
        //return new DiaryEntryAdapter.ViewHolder(view);
        return new DiaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {
        String entry = diaryEntries.get(position);

        // Load the media content into the ImageView using Glide
        loadImageIntoImageView(entry, holder.imageView);
    }

    @Override
    public int getItemCount() {
        return diaryEntries.size();
    }

    public static class DiaryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        VideoView videoView;

        public DiaryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            videoView = itemView.findViewById(R.id.videoView);
        }
    }

    // Helper method to load the image into the ImageView using Glide
    private void loadImageIntoImageView(String mediaPath, ImageView imageView) {

        Glide.with(context)
                .load(mediaPath)
                .placeholder(R.drawable.homecastlepic) // Replace with your placeholder image
                .into(imageView);
    }
}


