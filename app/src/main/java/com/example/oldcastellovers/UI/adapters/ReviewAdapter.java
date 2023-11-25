package com.example.oldcastellovers.UI.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.BuildConfig;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.model.Review;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<Review> reviewList;

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
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
        Review review = reviewList.get(position);

        Picasso.get()
                .load(review.getProfilePhotoUrl())
                .placeholder(R.drawable.default_avatar)
                .into(holder.authorAvatarImageView);

        holder.bind(review);
    }

    @Override
    public int getItemCount() {
        return reviewList != null ? reviewList.size() : 0;
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

        public void bind(Review review) {
            // Bind data to your views here
            authorNameTextView.setText(review.getAuthorName());
            reviewRatingBar.setRating(review.getRating());
            reviewDateTextView.setText(review.getDate());
            reviewTextView.setText(review.getText());
            reviewRatingNumberTextView.setText(String.valueOf(review.getRating()));
        }
    }
}
