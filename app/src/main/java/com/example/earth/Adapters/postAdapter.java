package com.example.earth.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earth.R;
import com.example.earth.models.Posts;
import com.squareup.picasso.Picasso;

import java.util.List;

public class postAdapter extends RecyclerView.Adapter<postAdapter.postsViewHolder> {
    private List<Posts> posts;
    private Context context;

    public postAdapter(List<Posts> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public postAdapter.postsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_page, parent, false);
        postAdapter.postsViewHolder viewHolder = new postAdapter.postsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull postsViewHolder holder, int position) {
        Posts c = posts.get(position);
        holder.desc.setText(c.getDescription());
        String currentUrl = c.getUrl();
        Picasso.get()
                .load(c.getUrl())
                .into(holder.o);


    }


    @Override
    public int getItemCount() {
        return posts.size();
    }


    //lets create the view holder class

    public static class postsViewHolder extends RecyclerView.ViewHolder {
        private CardView postOutline;
        private TextView desc;
        private ImageView o;

        public postsViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.findViewById(R.id.postOutline);
            desc = itemView.findViewById(R.id.description);
            o = itemView.findViewById(R.id.iv_overlay);
        }

    }
}
