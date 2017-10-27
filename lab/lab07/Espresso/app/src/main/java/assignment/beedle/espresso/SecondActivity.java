package assignment.beedle.espresso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private String user;
    private Toast toast;
    private ArrayList<String> listUser;
    private UserList userList = new UserList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listUser = new ArrayList<>();
        TextView checkList = (TextView) findViewById(R.id.checkList);
        Button back = (Button) findViewById(R.id.backBtn);

        Intent intent = getIntent();
        this.user = intent.getStringExtra("userName");
        userList.setUsername(user);

        if (user.length() >= 1) {
            listUser.add(userList.getUsername()); //Added user to list
            toast = Toast.makeText(this.getApplicationContext(), user, Toast.LENGTH_SHORT);
            toast.show();
        }

        ListView listView = (ListView) findViewById(R.id.listUser);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listUser);
        listView.setAdapter(adapter);

    }

    public void onBack(View view) {

        finish();
    }
}
