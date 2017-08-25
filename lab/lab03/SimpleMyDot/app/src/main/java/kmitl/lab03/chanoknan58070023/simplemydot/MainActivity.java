package kmitl.lab03.chanoknan58070023.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.chanoknan58070023.simplemydot.model.Dot;
import kmitl.lab03.chanoknan58070023.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener {

    private DotView dotView;
    private Dot dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dot = new Dot(this, 0, 0, 30);
    }

    public void onRandomDot(View view) {
        Random rand = new Random();
        int centerX = rand.nextInt(500);
        int centerY = rand.nextInt(500);
        //new Dot(this, centerX, centerY, 50); //this ในที่นี้เป็นตัวที่ implements มาคือ Dot.onDotChangedListener ซึ่งในนี้ประกาศรับ Listener
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);

    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(dot);
        dotView.invalidate();
//        TextView centerXTextView = (TextView) findViewById(R.id.centerXTextView); //ประกาศ textview ให้รู้จัก id textView
//        TextView centerYTextView = (TextView) findViewById(R.id.centerYTextView);
//        centerXTextView.setText(String.valueOf(dot.getCenterX())); //เซ็ทตัวอักษรของ TextView ให้มีค่าตามตัวแปร centerX
//        centerYTextView.setText(String.valueOf(dot.getCenterY()));
    }
}
