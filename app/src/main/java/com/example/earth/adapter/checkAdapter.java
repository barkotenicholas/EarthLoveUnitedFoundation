package com.example.earth.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earth.R;

import java.util.List;

public class checkAdapter extends RecyclerView.Adapter<checkAdapter.ViewHolder>{
    List<String> listItems;
    LayoutInflater inflater;
    public checkAdapter(Context context, List<String> listItems) {
        this.listItems =  listItems;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public checkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.check_item, parent, false);
        return new checkAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull checkAdapter.ViewHolder holder, int position) {
        holder.checkText.setText(listItems.get(position));
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      TextView checkText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkText= itemView.findViewById(R.id.checkText);

        }


    }
}

