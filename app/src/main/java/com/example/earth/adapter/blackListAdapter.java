package com.example.earth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earth.R;

import java.util.List;

public class blackListAdapter extends RecyclerView.Adapter<blackListAdapter.ViewHolder> {
    List<String> stringList;
    LayoutInflater inflater;
    public blackListAdapter(Context context, List<String> stringList) {
        this.stringList =  stringList;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.dark_list_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemLabel.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemLabel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemLabel = itemView.findViewById(R.id.itemText);

        }


    }
}
