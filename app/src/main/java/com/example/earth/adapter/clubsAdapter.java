package com.example.earth.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earth.R;

import java.util.ArrayList;
import java.util.List;

public class clubsAdapter extends RecyclerView.Adapter<clubsAdapter.ViewHolder>{
    List<String> clubLabels;
    LayoutInflater inflater;
    public ArrayList<String> selectedClubs = new ArrayList<>();
    boolean isSelected = false;
    public clubsAdapter(Context context, List<String> clubLabels) {
        this.clubLabels =  clubLabels;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.club_item, parent, false);
        return new ViewHolder(view);

    }

    public ArrayList<String> getSelectedClubs() {
        return selectedClubs;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.clubLabel.setText(clubLabels.get(position));
        holder.clubCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelected = true;
                if (selectedClubs.contains(clubLabels.get(holder.getBindingAdapterPosition()))) {
                    holder.clubCard.setBackgroundColor(Color.TRANSPARENT);
                    selectedClubs.remove(clubLabels.get(holder.getAbsoluteAdapterPosition()));
                } else {
                    holder.clubCard.setBackgroundResource(R.drawable.green_rounded_corner);
                    selectedClubs.add(clubLabels.get(holder.getAbsoluteAdapterPosition()));
                }

                if (selectedClubs.size() == 0) {
                    isSelected = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return clubLabels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView clubLabel;
        CardView clubCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clubLabel = itemView.findViewById(R.id.clubLabel);
            clubCard=itemView.findViewById(R.id.clubCard);
        }


    }
}

