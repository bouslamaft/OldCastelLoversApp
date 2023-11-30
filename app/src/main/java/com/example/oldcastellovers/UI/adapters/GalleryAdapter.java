package com.example.oldcastellovers.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.oldcastellovers.UI.GalleryItem;
import com.example.oldcastellovers.UI.MediaGalleryItem;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.UI.activities.MediaViewActivity;
import com.example.oldcastellovers.UI.VideoGalleryItem;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Context context;
    private List<MediaGalleryItem> galleryItems;
    private OnItemClickListener onItemClickListener;

    public GalleryAdapter(Context context, List<MediaGalleryItem> galleryItems) {
        this.context = context;
        this.galleryItems = galleryItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MediaGalleryItem item = galleryItems.get(position);
        String mediaPath = item.getMediaPath();

        if (item instanceof GalleryItem) {
            // Display the image using the ImageView
            holder.imageView.setVisibility(ImageView.VISIBLE);
            holder.videoView.setVisibility(VideoView.GONE);
            Glide.with(context)
                    .load(mediaPath)
                    .apply(new RequestOptions().centerCrop())
                    .into(holder.imageView);
        } else if (item instanceof VideoGalleryItem) {
            // Display the video thumbnail using the ImageView
            holder.imageView.setVisibility(ImageView.VISIBLE);
            holder.videoView.setVisibility(VideoView.GONE);

            // Get the thumbnail for the video
            Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(mediaPath, MediaStore.Images.Thumbnails.MINI_KIND);
            holder.imageView.setImageBitmap(thumbnail);

            // Set click listener for the ImageView to play the video
            holder.imageView.setOnClickListener(v -> {
                Intent intent = new Intent(context, MediaViewActivity.class);
                intent.putExtra("mediaPath", mediaPath);
                context.startActivity(intent);
            });
        }

        // Set click listener for the root view (the whole item)
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return galleryItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void updateMediaList(List<MediaGalleryItem> updatedMediaItems) {
        this.galleryItems = updatedMediaItems;
        notifyDataSetChanged();
    }

    public void setMediaItems(List<MediaGalleryItem> galleryItems) {
        this.galleryItems = galleryItems;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        VideoView videoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            videoView = itemView.findViewById(R.id.videoView);
        }
    }
}