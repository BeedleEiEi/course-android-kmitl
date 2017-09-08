package kmitl.lab03.chanoknan58070023.simplemydot.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kmitl.lab03.chanoknan58070023.simplemydot.R;
import kmitl.lab03.chanoknan58070023.simplemydot.model.DotPacelable;
import kmitl.lab03.chanoknan58070023.simplemydot.model.DotSerealizable;

public class SecondActivity extends AppCompatActivity {

    private DotSerealizable dotSerealizable;
    private DotPacelable dotPacelable;

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
        TextView tvParcel = (TextView) findViewById(R.id.tvParcel);



        dotSerealizable = (DotSerealizable) getIntent().getSerializableExtra("dotSerializable");
        dotPacelable = getIntent().getParcelableExtra("dotPacelable");

        tvv.setText("centerXx : " + dotPacelable.getCenterX() + "centerY : " + dotSerealizable.getCenterY() + "Radius : " + dotSerealizable.getRadius());
        tvv.setTextColor(dotSerealizable.getColor());

        tvParcel.setText("centerX : "  + "centerY : " + dotPacelable.getCenterY() + " Radius : " + dotPacelable.getRadius());
        tvTex.setText(xx);
    }
}
