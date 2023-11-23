package com.example.oldcastellovers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.oldcastellovers.model.CastleModel;

import java.util.List;

public class CastleAdapter extends RecyclerView.Adapter<CastleAdapter.CastleViewHolder> {

    private Context context;
    private static List<CastleModel> castleList;
    public interface OnDeleteClickListener {
        void onDeleteClick(CastleModel castleModel);
    }
    private static OnDeleteClickListener onDeleteClickListener;
    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }
//    public interface OnDeleteClickListener {
//        void onDeleteClick(CastleModel castleModel);
//    }

    public CastleAdapter(Context context, List<CastleModel> castleList) {
        this.context = context;
        this.castleList = castleList;
    }

    @NonNull
    @Override
    public CastleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.liked_tem, parent, false);
        return new CastleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastleViewHolder holder, int position) {
        CastleModel castleModel = castleList.get(position);

        holder.nameTextView.setText(castleModel.getName());
        holder.addressTextView.setText(castleModel.getFormattedAddress());
        holder.ratingBar.setRating((float) castleModel.getRating());
        holder.ratingNumberTextView.setText(String.valueOf(castleModel.getRating()));
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteClickListener.onDeleteClick(castleModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return castleList.size();
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
