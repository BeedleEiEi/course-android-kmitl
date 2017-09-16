package kmitl.lab03.chanoknan58070023.simplemydot.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import kmitl.lab03.chanoknan58070023.simplemydot.EditDot;
import kmitl.lab03.chanoknan58070023.simplemydot.MainActivity;
import kmitl.lab03.chanoknan58070023.simplemydot.R;
import kmitl.lab03.chanoknan58070023.simplemydot.model.DotPacelable;
import kmitl.lab03.chanoknan58070023.simplemydot.model.Dots;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends android.support.v4.app.Fragment {


    public EditFragment() {
        // Required empty public constructor
    }

    private Dots listDot;


    public void setDots(Dots listDot) {
        this.listDot = listDot;
    }

    public EditFragment newInstance(int message, Dots listDot) {
        Bundle args = new Bundle();
        this.listDot = listDot;
        EditFragment fragment = new EditFragment();
        args.putInt("message", message);
        fragment.setArguments(args);
        return fragment;

    }

    String message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_edit, container, false);
        if (!message.isEmpty()) { //มีการเรียกใช้ Instance

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
