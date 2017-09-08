package kmitl.lab03.chanoknan58070023.simplemydot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.chanoknan58070023.simplemydot.Activity.SecondActivity;
import kmitl.lab03.chanoknan58070023.simplemydot.model.Dot;
import kmitl.lab03.chanoknan58070023.simplemydot.model.DotPacelable;
import kmitl.lab03.chanoknan58070023.simplemydot.model.DotSerealizable;
import kmitl.lab03.chanoknan58070023.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener {

    final Context context = this;
    private DotView dotView;
    private Dot dot;
    private ArrayList<Dot> listDot;
    private ArrayList<Dot> listDotTouch;
    private int color;
    private float eXis, eYis;
    private float pxis, pyis;
    private int rad = 0;
    private long startTime;
    private long stopTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpenActivity = (Button) findViewById(R.id.OpenActivity); //Binding Button
        final DotSerealizable dotSerealizable = new DotSerealizable();
        dotSerealizable.setCenterX(150);
        dotSerealizable.setCenterY(150);
        dotSerealizable.setColor(Color.RED);
        dotSerealizable.setRadius(30);

        final DotPacelable dotPacelable = new DotPacelable(150,150,50);






        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("xxx", "もいいですか");
                intent.putExtra("dotSerializable", dotSerealizable);
                intent.putExtra("dotPacelable", dotPacelable);
                startActivity(intent);
                //เริ่มเปลี่ยนหน้า และทำงานอยู่จนกว่าจะ Finish เพื่อกลับมาหน้าเดิม
            }
        });


        listDot = new ArrayList<>();
        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Random rand = new Random();
                rad = rand.nextInt(70) + 80;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    eXis = event.getX();
                    eYis = event.getY();
                    startTime = event.getEventTime();

                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    stopTime = event.getEventTime();
                    if (stopTime - startTime < 300) {
                        addDot(rad);
                    } else {
                        if (isOverlap(event.getX(), event.getY(), rad)) {
                            return true;
                        }
                    }
                }

                System.out.println("Touched");

                return true;
            }
        });
    }


    public void alertDialog(final Dot dot) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Select Action");
        alertDialog.setMessage("Choose").setCancelable(true).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //delete item
                listDot.remove(dot);
                dotView.invalidate();
            }
        });
        alertDialog.show();
    }


    public void onRandomDot(View view) {
        Random rand = new Random();
        dot = new Dot(this, 0, 0, 30);
        dot.setColor();
        int centerX = rand.nextInt(830) + 30;
        int centerY = rand.nextInt(1200) + 30;
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
        listDot.add(dot);
//        System.out.println(listDot + "  , at MainAct"); //add in
    }


    public boolean isOverlap(float px, float py, float rad) {
        for (Dot dot : listDot) {
            double distance = Math.pow(Math.pow(dot.getCenterX() - px, 2) + Math.pow(dot.getCenterY() - py, 2), 0.5);
            if (distance <= rad) {
                //alert
                alertDialog(dot);
                ;
                return true;
            }
        }
        return false;
    }

    public void addDot(int radius) {
        dot = new Dot(this, 0, 0, radius);
        dot.setColor();
        float centerX = eXis;
        float centerY = eYis;
        dot.setCenterX((int) centerX);
        dot.setCenterY((int) centerY);
        listDot.add(dot);
    }

    public void onClearDot(View view) {

        this.listDot.clear();
        dotView.invalidate();
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(listDot);
        dotView.invalidate();
    }
}
