package com.example.oldcastellovers.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oldcastellovers.BuildConfig;
import com.example.oldcastellovers.CastleDetailsActivity;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.model.CastleModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastleAdapter extends RecyclerView.Adapter<CastleAdapter.CastleViewHolder> {
    private List<CastleModel> castleList;

    public CastleAdapter(List<CastleModel> castleList) {
        this.castleList = castleList;
    }

    @Override
    public CastleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.castle_item, parent, false);
        return new CastleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CastleViewHolder holder, int position) {
        CastleModel castle = castleList.get(position);

        String reference = castle.getPhotos() == null ? "" : castle.getPhotos().get(0).getReference();


        Picasso.get()
                .load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400" +
                        "&photo_reference="+reference+
            "&key" +
            "="+ BuildConfig.MY_API_KEY)
                .placeholder(R.drawable.homecastlepic)
                .into(holder.photoImageView);


        holder.nameTextView.setText(castle.getName());
        holder.addressTextView.setText(castle.getFormattedAddress());
        holder.ratingBar.setRating((float)castle.getRating());
        holder.ratingNumberTextView.setText(String.valueOf(castle.getRating()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, CastleDetailsActivity.class);
                intent.putExtra("placeId", castle.getPlaceId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(castleList != null)return castleList.size();
        else return 0;
        //return Math.min(castleList.size(), 10); could be useful for limiting the number of the castles
    }
    public class CastleViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, addressTextView,ratingNumberTextView;
        ImageView photoImageView;
        AppCompatRatingBar ratingBar;

        public CastleViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            photoImageView = itemView.findViewById(R.id.photoImageView);
            ratingNumberTextView = itemView.findViewById(R.id.ratingNumberTextView);
        }
    }
}
