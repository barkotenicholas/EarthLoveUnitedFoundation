package com.example.earth.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.earth.R;
import com.example.earth.databinding.FragmentFollowingBinding;
import com.example.earth.databinding.SortBottomSheetBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FollowingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowingFragment extends Fragment {

    private FragmentFollowingBinding followingBinding;
    private SortBottomSheetBinding sheetBinding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FollowingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowingFragment newInstance(String param1, String param2) {
        FollowingFragment fragment = new FollowingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        followingBinding = FragmentFollowingBinding.inflate(inflater,container,false);
        return followingBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        followingBinding.sortButton.setOnClickListener(view1 -> showBottomSheet());
    }

    //Fxn for bottomSheetDialog
    @SuppressLint("NonConstantResourceId")
    public void showBottomSheet(){
        sheetBinding = SortBottomSheetBinding.inflate(getLayoutInflater());
        View view = sheetBinding.getRoot();

        final Dialog bottomDialog = new Dialog(requireActivity());
        bottomDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        bottomDialog.setContentView(view);


        sheetBinding.sortRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i){

                case R.id.defaultRadio:
                    Toast.makeText(requireActivity(), "Default",Toast.LENGTH_LONG).show();

                    /*
                     * Sort by default
                     * */
                    break;

                case R.id.radioLatest:
                    Toast.makeText(requireActivity(), "Latest Followed",Toast.LENGTH_LONG).show();

                    /*
                     * Sort from last to first
                     */
                    break;

                case R.id.radioEarliest:
                    Toast.makeText(requireActivity(), "Earliest Followed",Toast.LENGTH_LONG).show();
                    /*
                     * Sort from first to last
                     * */
                    break;

                default:
                    break;
            }
        });

        bottomDialog.show();
        bottomDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        bottomDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bottomDialog.getWindow().getAttributes().windowAnimations = R.style.sheetAnimation;
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}