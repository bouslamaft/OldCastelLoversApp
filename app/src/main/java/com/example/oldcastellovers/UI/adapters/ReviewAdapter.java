package com.example.oldcastellovers.UI.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.R;
import com.example.oldcastellovers.network.dto.ReviewDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<ReviewDTO> reviewDTOList;

    public void setReviewList(List<ReviewDTO> reviewDTOList) {
        this.reviewDTOList = reviewDTOList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewDTO reviewDTO = reviewDTOList.get(position);

        Picasso.get()
                .load(reviewDTO.getProfilePhotoUrl())
                .placeholder(R.drawable.default_avatar)
                .into(holder.authorAvatarImageView);

        holder.bind(reviewDTO);
    }

    @Override
    public int getItemCount() {
        return reviewDTOList != null ? reviewDTOList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView authorNameTextView;
        TextView reviewRatingNumberTextView;
        TextView reviewTextView;
        TextView reviewDateTextView;
        AppCompatRatingBar reviewRatingBar;
        ImageView authorAvatarImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            authorNameTextView = itemView.findViewById(R.id.authorNameTextView);
            reviewRatingNumberTextView = itemView.findViewById(R.id.reviewRatingNumberTextView);
            reviewTextView = itemView.findViewById(R.id.reviewTextView);
            reviewDateTextView = itemView.findViewById(R.id.reviewDateTextView);
            reviewRatingBar = itemView.findViewById(R.id.reviewRatingBar);
            authorAvatarImageView = itemView.findViewById(R.id.authorAvatarImageView);
        }

        public void bind(ReviewDTO reviewDTO) {
            // Bind data to your views here
            authorNameTextView.setText(reviewDTO.getAuthorName());
            reviewRatingBar.setRating(reviewDTO.getRating());
            reviewDateTextView.setText(reviewDTO.getDate());
            reviewTextView.setText(reviewDTO.getText());
            reviewRatingNumberTextView.setText(String.valueOf(reviewDTO.getRating()));
        }
    }
}
