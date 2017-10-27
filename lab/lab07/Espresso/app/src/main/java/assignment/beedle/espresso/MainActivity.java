package assignment.beedle.espresso;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Editable userName;
    private Editable password;
    private TextView sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;
        Button btnAdd = (Button) findViewById(R.id.buttonAdd);
        EditText name = (EditText) findViewById(R.id.userName);
        EditText userPassword = (EditText) findViewById(R.id.userPassword);
        this.userName = name.getText();
        this.password = userPassword.getText();
        TextView sign = (TextView) findViewById(R.id.sign);
        this.sign = sign;
    }

    public void onAdded(View view) {

        if (userName.length() <= 0 || password.length() <= 0) {
            sign.setText("Please Enter user info");
            Toast toast = Toast.makeText(context, "Please Enter user info", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void toList(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("userName", userName.toString());
        startActivity(intent);
    }
}
