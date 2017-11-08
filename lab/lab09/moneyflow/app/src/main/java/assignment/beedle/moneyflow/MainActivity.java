package assignment.beedle.moneyflow;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, UserInfoAdapter.RecordInfoAdapterListener {


    private UserDB userDB;
    private Intent intent;
    TextView total;
    UserInfoAdapter recordInfoAdapter;
    public final int RESULT_ACTIVITY = 999;
    public final int RESULT_UPDATE = 888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDB = Room.databaseBuilder(this, UserDB.class, "RECORD").build();
        intent = new Intent(this, Add.class);
        Button addBtn = findViewById(R.id.addRecordBtn);
        addBtn.setOnClickListener(this);
        total = findViewById(R.id.totalIncomeTv);
        recordInfoAdapter = new UserInfoAdapter();
        recordInfoAdapter.setContext(this);
        recordInfoAdapter.setListener(this);
        RecyclerView recyclerView = findViewById(R.id.rc_record_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recordInfoAdapter);

        loadList();
    }

    @SuppressLint("StaticFieldLeak")
    private void loadList() {

        new AsyncTask<Void, Void, List<UserInfo>>() {
            @Override
            protected List<UserInfo> doInBackground(Void... voids) {
                List<UserInfo> result = userDB.getRecordInfoDAO().showAll();
                return result;
            }

            @Override
            protected void onPostExecute(List<UserInfo> recordInfos) {
                recordInfoAdapter.setUserInfoList(recordInfos);
                recordInfoAdapter.notifyDataSetChanged();

                float totalBalance = 0;
                float totalIncome = 0;
                for (UserInfo r : recordInfos) {

                    if (r.getType().equals("income")) {
                        totalIncome += r.getAmount();
                        totalBalance += r.getAmount();
                    } else totalBalance -= r.getAmount();
                }
                float ratio;
                try {
                    ratio = totalBalance / totalIncome;
                } catch (ArithmeticException e) {
                    ratio = 0;
                }
                if (ratio > 0.5) {
                    //total.setTextColor(Color.GREEN);
                    total.setBackgroundColor(Color.GREEN);
                    Toast.makeText(MainActivity.this, "Just Added", Toast.LENGTH_SHORT).show();
                } else if (ratio >= 0.25) {
                    total.setBackgroundColor(Color.YELLOW);
                    Toast.makeText(MainActivity.this, "Pretty low money", Toast.LENGTH_SHORT).show();
                } else {
                    total.setBackgroundColor(Color.RED);
                    Toast.makeText(MainActivity.this, "You're in debt I guess", Toast.LENGTH_SHORT).show();
                }
                total.setText(totalBalance + "");
            }
        }.execute();
    }

    @Override
    public void onClick(View view) {
        startActivityForResult(intent, RESULT_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_ACTIVITY && resultCode == RESULT_OK) {
            loadList();
        }
    }

    @Override
    public void onClickRecordInfoItem(UserInfo recordInfo) {
        Intent intent = new Intent(this, Update.class);
        intent.putExtra("UserParcel", recordInfo);
        startActivityForResult(intent, RESULT_ACTIVITY);

    }
}
