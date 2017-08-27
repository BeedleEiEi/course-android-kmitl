package kmitl.lab03.chanoknan58070023.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.chanoknan58070023.simplemydot.model.Dot;
import kmitl.lab03.chanoknan58070023.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener {

    private DotView dotView;
    private Dot dot;
    private ArrayList<Dot> listDot;
    private ArrayList<Dot> listDotTouch;
    private int color;
    private float eXis, eYis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listDot = new ArrayList<>();
        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("Touched");
                eXis = event.getX();
                eYis = event.getY();
                addDot();
//                System.out.println(listDot + "  , at MainAct Touched"); //add in
                return true;
            }
        });
    }
    public void onRandomDot(View view) {

        dot = new Dot(this, 0, 0, 30);
        Random rand = new Random();
        dot.setColor();
        int centerX = rand.nextInt(830) + 30;
        int centerY = rand.nextInt(1200) + 30;
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
        listDot.add(dot);
//        System.out.println(listDot + "  , at MainAct"); //add in
    }

    public void addDot(){
        dot = new Dot(this, 0, 0, 80);
        Random rand = new Random();
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
