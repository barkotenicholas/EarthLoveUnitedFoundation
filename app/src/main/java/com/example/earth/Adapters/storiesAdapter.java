package com.example.earth.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earth.R;
import com.example.earth.story;

import java.util.List;

public class storiesAdapter extends RecyclerView.Adapter<storiesAdapter.storiesViewHolder>{

    private List<story> stories;
    private Context context;

    public storiesAdapter(List<story> stories, Context context) {
        this.stories = stories;
        this.context = context;
    }

    @NonNull
    @Override
    public storiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.stories_page, parent, false);
        storiesViewHolder viewHolder = new storiesViewHolder(view);
        return viewHolder;
    }



    @Override
    public int getItemCount() {
       return stories.size();
    }
    //lets create the view holder class

    public static class storiesViewHolder extends RecyclerView.ViewHolder{
        private CardView storyOutline;
        public storiesViewHolder(@NonNull View itemView){
            super(itemView);

            itemView.findViewById(R.id.storyOutline);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull storiesViewHolder holder, int position) {

        if(stories.get(position).isSeen()) {
            try {
                holder.storyOutline.setCardBackgroundColor(context.getResources().getColor(R.color.earthGreen));
            }catch (NullPointerException ex){
                ex.printStackTrace();
            }

        }

    }

}
