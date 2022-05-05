package com.example.earth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earth.R;

import java.util.List;

public class photoPostAdapter extends RecyclerView.Adapter<photoPostAdapter.ViewHolder>{
    List<Integer> images;
    LayoutInflater inflater;

    public photoPostAdapter(Context context, List<Integer> images) {
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.image_post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.postedImage.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView postedImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postedImage = itemView.findViewById(R.id.imagePosted);
        }


    }
}
