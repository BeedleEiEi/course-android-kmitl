package kmitl.lab03.chanoknan58070023.simplemydot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import kmitl.lab03.chanoknan58070023.simplemydot.Activity.SecondActivity;
import kmitl.lab03.chanoknan58070023.simplemydot.model.BitmapPicture;
import kmitl.lab03.chanoknan58070023.simplemydot.model.Dot;
import kmitl.lab03.chanoknan58070023.simplemydot.model.DotPacelable;
import kmitl.lab03.chanoknan58070023.simplemydot.model.Dots;
import kmitl.lab03.chanoknan58070023.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener, DotView.OnDotViewClickListener {

    final Context context = this;
    private DotView dotView;
    private Dot dot;
    private Dots listDot;
    private int color;
    private int rad = 0;
    private int positionDot;

    private int widthScreen = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int heightScreen = Resources.getSystem().getDisplayMetrics().heightPixels;
    private BitmapPicture bitmapPicture = new BitmapPicture(widthScreen, heightScreen);
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnShare = (Button) findViewById(R.id.share);
        Button btnClearDot = (Button) findViewById(R.id.clearDot);
        Button btnOpenActivity = (Button) findViewById(R.id.OpenActivity); //Binding Button
        /*final DotSerealizable dotSerealizable = new DotSerealizable();
        dotSerealizable.setCenterX(150);
        dotSerealizable.setCenterY(150);
        dotSerealizable.setColor(Color.RED);
        dotSerealizable.setRadius(30);*/



        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = bitmapPicture.snap();
                //Intent intent = new Intent(MainActivity.this, f);
            }
        });

        btnClearDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDot.clearAll();
                dotView.invalidate();
            }
        });

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewClickListener(this);

        listDot = new Dots();
        listDot.setListener(this);

        final DotPacelable dotPacelable = new DotPacelable(150, 150, 50);

        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("xxx", "もいいですか");
                //intent.putExtra("dotSerializable", dotSerealizable);
                intent.putExtra("dotPacelable", dotPacelable);
                startActivity(intent);
                //เริ่มเปลี่ยนหน้า และทำงานอยู่จนกว่าจะ Finish เพื่อกลับมาหน้าเดิม
            }
        });


    }

    public void onRandomDot(View view) {
        Random rand = new Random();
        int centerX = rand.nextInt(830) + 30;
        int centerY = rand.nextInt(1200) + 30;
        Dot dot = new Dot(centerX, centerY, 30);
        dot.setColor();
        listDot.addDot(dot);
//        System.out.println(listDot + "  , at MainAct"); //add in
    }


    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(listDot);
        dotView.invalidate();
    }

    @Override
    public void onDotViewClicked(int x, int y) {

        this.positionDot = listDot.findDot(x, y);
        if (positionDot != -1) {
            alertDialog(positionDot);
        } else {
            Random rand = new Random();
            int centerX = x;
            int centerY = y;
            Dot dot = new Dot(centerX, centerY, rand.nextInt(70) + 80);
            dot.setColor();
            listDot.addDot(dot);
        }
    }

    public void alertDialog(final int position) {
        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(context);
        alertDialog.setTitle("Select Action");
        alertDialog.setMessage("Choose").setCancelable(true).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //delete item
                listDot.removeBy(position);
            }
        });
        alertDialog.show();
    }
}
