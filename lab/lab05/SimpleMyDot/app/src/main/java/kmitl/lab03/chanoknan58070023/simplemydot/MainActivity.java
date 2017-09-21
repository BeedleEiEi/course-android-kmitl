package kmitl.lab03.chanoknan58070023.simplemydot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.EventListener;
import java.util.Random;

import kmitl.lab03.chanoknan58070023.simplemydot.Activity.SecondActivity;
import kmitl.lab03.chanoknan58070023.simplemydot.fragment.EditFragment;
import kmitl.lab03.chanoknan58070023.simplemydot.model.Dot;
import kmitl.lab03.chanoknan58070023.simplemydot.model.DotPacelable;
import kmitl.lab03.chanoknan58070023.simplemydot.model.DotSerealizable;
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
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Binding Fragment Buttons


        //Binding
        Button btnShare = (Button) findViewById(R.id.share); //Share Button
        Button btnClearDot = (Button) findViewById(R.id.clearDot);
        Button btnOpenActivity = (Button) findViewById(R.id.OpenActivity); //Binding Button
        ImageButton clickMe = (ImageButton) findViewById(R.id.clickMe);
        listDot = new Dots();
        listDot.setListener(this);
        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewClickListener(this);

        //Second Activity
        final DotPacelable dotPacelable = new DotPacelable(150, 150, 50);
        final DotSerealizable dotSerealizable = new DotSerealizable();
        dotSerealizable.setCenterX(150);
        dotSerealizable.setCenterY(150);
        dotSerealizable.setColor(Color.RED);
        dotSerealizable.setRadius(30);
        //


        clickMe.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mp = MediaPlayer.create(context, R.raw.allahu);

            @Override
            public void onClick(View v) {
                mp.start();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() { //When clicked share button
            @Override
            public void onClick(View v) {
                bitmap = dotView.createBitmapFromView(dotView);
                dotView.saveBitmap(bitmap);
                File imagePath = new File(getCacheDir(), "images");
                File newFile = new File(imagePath, "image.png");
                Uri contentUri = FileProvider.getUriForFile(context, "kmitl.lab03.chanoknan58070023.simplemydot.fileprovider", newFile); //get path from package internal
                if (contentUri != null) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(contentUri, getContentResolver().getType(contentUri));
                    intent.putExtra(Intent.EXTRA_STREAM, contentUri);
                    startActivity(Intent.createChooser(intent, "Share to app"));
                }
            }

        });

        btnClearDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDot.clearAll();
                dotView.invalidate();
            }
        });

        btnOpenActivity.setOnClickListener(new View.OnClickListener()

        {
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //When response
        DotPacelable dotParcelable = data.getParcelableExtra("reDotParcelable");
        //Toast.makeText(getApplicationContext(), "RadiusUpdate" + dotParcelable.getRadius() + "Request Code == " + requestCode + "   result code == " + resultCode, Toast.LENGTH_LONG).show();
        if (resultCode == 2) {
            this.listDot.getAllDot().get(dotParcelable.getDotPosition()).setRadius(dotParcelable.getRadius());
        } else {
            this.listDot.getAllDot().get(dotParcelable.getDotPosition()).setColor(dotParcelable.getColor());
        }
    }

    public void onRandomDot(View view) {
        Random rand = new Random();
        int centerX = rand.nextInt(830) + 30;
        int centerY = rand.nextInt(1200) + 30;
        Dot dot = new Dot(centerX, centerY, 30);
        dot.setColor();
        listDot.addDot(dot);
    }


    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(listDot);
        dotView.invalidate();
    }


    @Override
    public void onDotViewClicked(int x, int y) {
        double time = dotView.getTime();
        this.positionDot = listDot.findDot(x, y);
        if (positionDot != -1) {
            if (time < 550) {
                //alertDialog(positionDot);
                final DotPacelable dotPacelable = new DotPacelable(positionDot, listDot.getAllDot().get(positionDot).getColor(), listDot.getAllDot().get(positionDot).getRadius(), listDot);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.testFragment, new EditFragment().newInstance(dotPacelable)).commit();
            }

        } else {
            Random rand = new Random();
            int centerX = x;
            int centerY = y;
            Dot dot = new Dot(centerX, centerY, rand.nextInt(70) + 80);
            dot.setColor();
            listDot.addDot(dot);
        }
    }

}
