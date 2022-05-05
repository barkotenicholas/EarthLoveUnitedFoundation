package com.example.earth.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earth.R;
import com.example.earth.post;

import java.util.List;

public class postAdapter extends RecyclerView.Adapter<postAdapter.postsViewHolder> {
    private List<post> posts;
    private Context context;

    public postAdapter(List<post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public postAdapter.postsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.post_page, parent, false);
        postAdapter.postsViewHolder viewHolder = new postAdapter.postsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull postsViewHolder holder, int position) {
        if(posts.get(position).isScrolled()) {
            try {
                holder.postOutline.setCardBackgroundColor(context.getResources().getColor(R.color.earthGreen));
            }catch (NullPointerException ex){
                ex.printStackTrace();
            }

        }
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }



    //lets create the view holder class

    public static class postsViewHolder extends RecyclerView.ViewHolder{
        private CardView postOutline;
        public postsViewHolder(@NonNull View itemView){
            super(itemView);

            itemView.findViewById(R.id.postOutline);

        }

    }
}
