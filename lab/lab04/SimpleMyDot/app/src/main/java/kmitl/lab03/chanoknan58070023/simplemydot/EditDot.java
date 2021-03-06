package kmitl.lab03.chanoknan58070023.simplemydot;

import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;


import com.rarepebble.colorpicker.ColorPickerView;

import kmitl.lab03.chanoknan58070023.simplemydot.model.DotPacelable;

public class EditDot extends AppCompatActivity {
    private DotPacelable dotPacelable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dot);
        setTitle("Edit Dot");
        alertDialog();
    }

    public void alertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("What do you want?"); //Text when edit window show up
        alertDialogBuilder
                .setMessage("")
                .setCancelable(false)
                .setPositiveButton("Edit Color", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        colorPicker();
                    }
                })
                .setNegativeButton("Edit Size", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        seekBar();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void colorPicker() {
        final DotPacelable dotParcelable = getIntent().getParcelableExtra("dotParcelable");
        final ColorPicker cp = new ColorPicker(EditDot.this, Color.red(dotParcelable.getColor()),
                Color.green(dotParcelable.getColor()), Color.blue(dotParcelable.getColor()));
        cp.show();
        cp.setCancelable(false);
        cp.setCallback(new ColorPickerCallback() {
            @Override
            public void onColorChosen(@ColorInt int c) {
                int color = Color.rgb(Color.red(c), Color.green(c), Color.blue(c));
                final DotPacelable reDotParcelable = new DotPacelable(dotParcelable.getDotPosition(), color);
                Intent returnIntent = new Intent(EditDot.this, MainActivity.class);
                returnIntent.putExtra("reDotParcelable", reDotParcelable);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                cp.cancel();
                Toast.makeText(getApplicationContext(), "Changed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void seekBar() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final DotPacelable dotParcelable = getIntent().getParcelableExtra("dotParcelable");
        alert.setTitle("Change Dot Size");
        alert.setCancelable(false);
        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        final TextView text = new TextView(this);
        text.setPadding(500, 50, 0, 50);
        text.setText("Radius: " + dotParcelable.getRadius());
        final SeekBar seek = new SeekBar(this);
        seek.setBackgroundColor(Color.BLACK);
        seek.setMax(150); //Max radius
        seek.setProgress(dotParcelable.getRadius());
        seek.setKeyProgressIncrement(1);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text.setText("Radius: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        linear.addView(text);
        linear.addView(seek);
        alert.setView(linear);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                final DotPacelable reDotParcelable = new DotPacelable(dotParcelable.getDotPosition());
                reDotParcelable.setRadius(seek.getProgress());
                Intent returnIntent = new Intent(EditDot.this, MainActivity.class);
                returnIntent.putExtra("reDotParcelable", reDotParcelable);
                setResult(2, returnIntent);
                Toast.makeText(getApplicationContext(), "Radius Changed!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        alert.show();
    }


}
