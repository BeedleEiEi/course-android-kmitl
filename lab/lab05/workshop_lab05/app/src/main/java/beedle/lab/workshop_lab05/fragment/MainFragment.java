package beedle.lab.workshop_lab05.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import beedle.lab.workshop_lab05.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends android.support.v4.app.Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String message) {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        args.putString("message", message);
        fragment.setArguments(args);
        return fragment;

    }

    String message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView fragmentTextView = (TextView) rootView.findViewById(R.id.fragmentTextView);
        fragmentTextView.setText(message);
        if (!message.isEmpty()) {
            fragmentTextView.setText(message);
        }
        return rootView;
    }


    //เมื่อมีการเรียก Fragment ให้ฟื้นมา Message ก็จะกลับมา
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = getArguments().getString("message");
    }
}
