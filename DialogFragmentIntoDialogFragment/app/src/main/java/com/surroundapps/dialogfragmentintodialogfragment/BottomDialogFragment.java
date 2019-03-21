package com.surroundapps.dialogfragmentintodialogfragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomDialogFragment extends DialogFragment {

    View rootview;

    public BottomDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview =  inflater.inflate(R.layout.fragment_bottom_dialog, container, false);

        Button button = rootview.findViewById(R.id.btnDone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TopFragment dF = new TopFragment();
                dF.setCancelable(true);
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                dF.show(activity.getSupportFragmentManager(), TopFragment.class.getSimpleName());
            }
        });


        Button button1 = rootview.findViewById(R.id.btn_cancel);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return rootview;
    }

}
