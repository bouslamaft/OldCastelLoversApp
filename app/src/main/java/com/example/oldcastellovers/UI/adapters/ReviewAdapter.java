package com.example.oldcastellovers.UI.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.R;
import com.example.oldcastellovers.model.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<Review> reviews;

    public ReviewAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.authorNameTextView.setText(review.getAuthorName());
        holder.reviewRatingNumberTextView.setText(String.valueOf(review.getRating()));
        holder.reviewTextView.setText(review.getText());
        holder.reviewDateTextView.setText(review.getDate());
        holder.ratingBar.setRating((float) review.getRating());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviews = reviewList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView authorNameTextView;
        TextView reviewRatingNumberTextView;
        TextView reviewTextView;
        TextView reviewDateTextView;
        AppCompatRatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            authorNameTextView = itemView.findViewById(R.id.authorNameTextView);
            reviewRatingNumberTextView = itemView.findViewById(R.id.reviewRatingNumberTextView);
            reviewTextView = itemView.findViewById(R.id.reviewTextView);
            reviewDateTextView = itemView.findViewById(R.id.reviewDateTextView);
            ratingBar = itemView.findViewById(R.id.reviewRatingBar);
        }
    }
}
