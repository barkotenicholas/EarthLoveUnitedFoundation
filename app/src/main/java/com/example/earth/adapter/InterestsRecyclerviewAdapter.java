package com.example.earth.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earth.R;
import com.example.earth.ui.ClubsFragment;
import com.example.earth.ui.InterestsFragment;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class InterestsRecyclerviewAdapter extends RecyclerView.Adapter<InterestsRecyclerviewAdapter.ViewHolder> {
    List<String> interestLabels;
    List<Integer> images;
    LayoutInflater inflater;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
   public ArrayList<String> labels;
    public InterestsRecyclerviewAdapter(Context context, List<Integer> images, List<String> interestLabels) {
        this.images = images;
        this.interestLabels = interestLabels;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.interest_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull InterestsRecyclerviewAdapter.ViewHolder holder, int position) {
        holder.interestImage.setImageResource(images.get(position));
        holder.interestLabel.setText(interestLabels.get(position));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                labels.add(holder.interestLabel.getText().toString());
//                mSharedPreferences = PreferenceManager.getDefaultSharedPreferences();
//                private void addToSharedPreferences(String) {
//                    mEditor.putString("interest", labels).apply();
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView interestLabel;
        ImageView interestImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            interestLabel = itemView.findViewById(R.id.imageLabel);
            interestImage = itemView.findViewById(R.id.interestLabel);
        }


    }
}
