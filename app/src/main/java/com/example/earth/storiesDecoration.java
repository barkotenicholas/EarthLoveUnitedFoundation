package com.example.earth;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class storiesDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public storiesDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
       outRect.right= space;
       outRect.left= space;
    }
}
