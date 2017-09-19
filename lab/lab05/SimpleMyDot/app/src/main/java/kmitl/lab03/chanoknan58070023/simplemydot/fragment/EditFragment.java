package kmitl.lab03.chanoknan58070023.simplemydot.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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

    private DotPacelable dotPacelable;

    private Dots listDot;

    public static EditFragment newInstance(Parcelable message) { //รับ dot parceleable มา
        Bundle args = new Bundle();
        EditFragment fragment = new EditFragment();
        args.putParcelable("message", message);
        fragment.setArguments(args);
        return fragment;
    }

    String message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_edit, container, false);
        TextView fragmentTextView = (TextView) rootView.findViewById(R.id.fragmentTextView);
        return rootView;
    }

    public void alertDialog(final int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                getContext());

        alertDialog.setTitle("Select Action");
        alertDialog.setMessage("Choose").setCancelable(true).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //delete item
                listDot.removeBy(position);
                Toast.makeText(getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
            }
        })
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent edit = new Intent(getActivity(), EditDot.class);
                        edit.putExtra("dotParcelable", dotPacelable);

                        startActivityForResult(edit, 1);
                    }
                });
        AlertDialog alertDialog2 = alertDialog.create();
        alertDialog.show();
    }

    //เมื่อมีการเรียก Fragment ให้ฟื้นมา Message ก็จะกลับมา
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.dotPacelable = getArguments().getParcelable("message");
        this.listDot = dotPacelable.getListDot();
        alertDialog(dotPacelable.getDotPosition());

    }

}
