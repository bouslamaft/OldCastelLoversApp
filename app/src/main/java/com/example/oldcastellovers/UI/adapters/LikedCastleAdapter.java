package com.example.oldcastellovers.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.oldcastellovers.BuildConfig;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.UI.activities.CastleDetailsActivity;
import com.example.oldcastellovers.database.models.LikedCastleModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LikedCastleAdapter extends RecyclerView.Adapter<LikedCastleAdapter.CastleViewHolder> {

    private Context context;
    private static List<LikedCastleModel> likedCastleModels;
    public interface OnDeleteClickListener {
        void onDeleteClick(LikedCastleModel likedCastleModel);
    }
    private static OnDeleteClickListener onDeleteClickListener;
    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public LikedCastleAdapter(Context context, List<LikedCastleModel> likedCastleModels) {
        this.context = context;
        LikedCastleAdapter.likedCastleModels = likedCastleModels;
    }

    @NonNull
    @Override
    public CastleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.liked_item, parent, false);
        return new CastleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastleViewHolder holder, int position) {
        LikedCastleModel likedCastleModel = likedCastleModels.get(position);
        String reference = likedCastleModel.getPhotoReference() == null ? "" : likedCastleModel.getPhotoReference();

        holder.nameTextView.setText(likedCastleModel.getCastleName());
        holder.addressTextView.setText(likedCastleModel.getAddress());
        holder.ratingBar.setRating((float) likedCastleModel.getRating());
        holder.ratingNumberTextView.setText(String.valueOf(likedCastleModel.getRating()));

        Picasso.get()
                .load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400" +
                        "&photo_reference="+reference+
                        "&key" +
                        "="+ BuildConfig.MY_API_KEY)
                .placeholder(R.drawable.homecastlepic) // You can use a placeholder image// You can use an error image
                .into(holder.photoImageView);
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteClickListener.onDeleteClick(likedCastleModel);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, CastleDetailsActivity.class);
                intent.putExtra("placeId", likedCastleModel.getPlaceId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return likedCastleModels.size();
    }

    public static class CastleViewHolder extends RecyclerView.ViewHolder {
        ImageView photoImageView;
        TextView nameTextView;
        TextView addressTextView;
        RatingBar ratingBar;
        TextView ratingNumberTextView;
        ImageButton deleteIcon;

        public CastleViewHolder(@NonNull View itemView) {
            super(itemView);

            photoImageView = itemView.findViewById(R.id.photoImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ratingNumberTextView = itemView.findViewById(R.id.ratingNumberTextView);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
        }
    }
}
