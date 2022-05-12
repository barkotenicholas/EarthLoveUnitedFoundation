package com.example.earth.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earth.R;
import com.example.earth.databinding.FragmentConnectBinding;


public class ConnectFragment extends Fragment {

    private FragmentConnectBinding connectBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        connectBinding = FragmentConnectBinding.inflate(inflater,container,false);
        return connectBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connectBinding.connectFollowButton.setOnClickListener(view1 -> showDialog());
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        AlertDialog dialog = builder.create();
        View customDialogView = requireActivity().getLayoutInflater().inflate(R.layout.custom_follow_dialog,null);
        EditText mEditText = (EditText) customDialogView.findViewById(R.id.followMessageEditText);
        Button mButton = (Button) customDialogView.findViewById(R.id.dialogFollowButton);
        TextView mTextView = (TextView)  customDialogView.findViewById(R.id.cancelFollowText);

        String message = mEditText.getText().toString().trim();

        mButton.setOnClickListener(view1 ->{
            //Handle Follow
            /* With or without message*/
            Toast.makeText(requireActivity(),message, Toast.LENGTH_LONG).show();
        });

        mTextView.setOnClickListener(view1 ->{
           //Handle Cancel
            dialog.dismiss();
        });

        builder.setView(customDialogView);
        dialog.show();
    }

}