package com.example.earth.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earth.CmmentFragment;
import com.example.earth.R;
import com.example.earth.models.Follo;
import com.example.earth.models.Posts;
import com.example.earth.models.profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapters extends RecyclerView.Adapter<PostAdapters.postsViewHolder> {
    private List<Follo> posts;
    private Context context;
    profile a;
    public PostAdapters(List<Follo> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostAdapters.postsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_page, parent, false);
        PostAdapters.postsViewHolder viewHolder = new PostAdapters.postsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull postsViewHolder holder, int position) {
        Follo c = posts.get(position);
        holder.desc.setText(c.getDescription());
        Picasso.get()
                .load(c.getImageurl())
                .into(holder.o);


        holder.like.setImageResource(R.drawable.ic_heart);
        holder.like.setTag("Like");
        isLikes(c.getPostid(), c.getPublisher(), holder.like);
        nrLikes(holder.likes, c.getPostid());
        holder.comment.setOnClickListener(view -> {
            Intent intent = new Intent(context, CmmentFragment.class);
            intent.putExtra("postId", c.getPostid());
            intent.putExtra("authorId", c.getPublisher());
            context.startActivity(intent);
        });
        FirebaseDatabase.getInstance().getReference().child("Users").child(c.getPublisher()).child("Profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                a = dataSnapshot.getValue(profile.class);
                if (a != null) {
                    holder.name.setText(a.getName());
                    Log.d("AAAAAA", "onDataChange: " + a.getName());
                    Picasso.get()
                            .load(a.getProfileImage())
                            .into(holder.circleImageView);

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        holder.like.setOnClickListener(view -> {

            if (holder.like.getTag().equals("Like")) {
                FirebaseDatabase.getInstance().getReference().child("Likes").child(c.getPostid()).child(FirebaseAuth.getInstance().getUid()).setValue(true);
            } else {
                FirebaseDatabase.getInstance().getReference().child("Likes").child(c.getPostid()).child(FirebaseAuth.getInstance().getUid()).removeValue();
            }

        });


    }

    public void isLikes(String postid, String authid, ImageView imageView) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Likes").child(postid);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(FirebaseAuth.getInstance().getUid()).exists()) {
                    imageView.setImageResource(R.drawable.red_heart);
                    imageView.setTag("Liked");
                } else {
                    imageView.setImageResource(R.drawable.ic_heart);
                    imageView.setTag("Like");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void nrLikes(TextView a, String postid) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Likes").child(postid);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                a.setText(snapshot.getChildrenCount() + " Likes");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    //lets create the view holder class

    public static class postsViewHolder extends RecyclerView.ViewHolder {
        private CardView postOutline;
        private TextView desc;
        private TextView likes;
        private ImageView o;
        private ImageView comment;
        private TextView name;
        private ImageView like;
        private CircleImageView circleImageView;

        public postsViewHolder(@NonNull View itemView) {
            super(itemView);

            desc = itemView.findViewById(R.id.description);
            o = itemView.findViewById(R.id.post_image);
            comment = itemView.findViewById(R.id.comment);
            likes = itemView.findViewById(R.id.idTVLikes);
            like = itemView.findViewById(R.id.like);
            circleImageView = itemView.findViewById(R.id.idCVAuthor);
            name = itemView.findViewById(R.id.idTVAuthorName);
        }

    }
}
