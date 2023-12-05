package com.example.oldcastellovers.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
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
import com.example.oldcastellovers.UI.GalleryItem;
import com.example.oldcastellovers.UI.VideoGalleryItem;
import com.example.oldcastellovers.UI.activities.MediaViewActivity;
import com.example.oldcastellovers.database.models.DiaryEntryModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiaryEntryMediaAdapter extends RecyclerView.Adapter<DiaryEntryMediaAdapter.DiaryViewHolder> {

    private Context context;
    private List<DiaryEntryModel> diaryEntries;

    public DiaryEntryMediaAdapter(Context context, List<DiaryEntryModel> diaryEntries) {
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
        DiaryEntryModel entry = diaryEntries.get(position);

        ArrayList<String> mediaPaths = new ArrayList<>(Arrays.asList(entry.getMediaPath().spl     //.split(",")));
        Log.d("imagepath", "onBindViewHolder: " + imagePath);
        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.homecastlepic) // Replace with your placeholder image
                .into(holder.imageView);


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
}


