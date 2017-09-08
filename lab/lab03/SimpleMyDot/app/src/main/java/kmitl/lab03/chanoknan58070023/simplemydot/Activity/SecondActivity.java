package kmitl.lab03.chanoknan58070023.simplemydot.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import kmitl.lab03.chanoknan58070023.simplemydot.R;
import kmitl.lab03.chanoknan58070023.simplemydot.model.DotSerealizable;

public class SecondActivity extends AppCompatActivity {

    private DotSerealizable dotSerealizable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Button backButton = (Button) findViewById(R.id.BackActivity);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //จบคำสั่ง StartActivity เพื่อกลับไปหน้าเดิม
            }
        });

        String xx = getIntent().getStringExtra("xxx");

        TextView tvv = (TextView) findViewById(R.id.tvDot);
        TextView tvTex = (TextView) findViewById(R.id.tvTexk);

        dotSerealizable = (DotSerealizable) getIntent().getSerializableExtra("dotSerializable");

        tvv.setText("centerX : " + dotSerealizable.getCenterX() + "centerY : " + dotSerealizable.getCenterY() + "Radius : " + dotSerealizable.getRadius());
        tvv.setTextColor(dotSerealizable.getColor());

        tvTex.setText(xx);

        //tvDot.setText();
    }
}
