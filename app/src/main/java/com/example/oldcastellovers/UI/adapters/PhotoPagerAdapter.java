package com.example.oldcastellovers.UI.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.oldcastellovers.BuildConfig;
import com.example.oldcastellovers.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoPagerAdapter extends PagerAdapter {
    private List<String> photoReferenceList;

    public PhotoPagerAdapter(List<String> photoReferenceList) {
        this.photoReferenceList = photoReferenceList;
    }

    @Override
    public int getCount() {
        return photoReferenceList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.photo_item, container, false);
        ImageView photoPagerImageView = itemView.findViewById(R.id.photoPagerImageView);
        
        Picasso.get()
                .load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400" +
                        "&photo_reference="+photoReferenceList.get(position)+
                        "&key" +
                        "="+ BuildConfig.MY_API_KEY)
                .placeholder(R.drawable.homecastlepic) // You can use a placeholder image// You can use an error image
                .into(photoPagerImageView); // Set the loaded image to the ImageView

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

