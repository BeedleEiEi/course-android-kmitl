package assignment.beedle.moneyflow;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Beedle on 8/11/2560.
 */

public class Add extends Activity implements View.OnClickListener {

    private EditText desc, amount;
    private UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

        userDB = Room.databaseBuilder(this, UserDB.class, "RECORD").build();

        desc = findViewById(R.id.desc_et);
        amount = findViewById(R.id.amount_et);

    }

    @Override
    public void onClick(View view) {
        if (desc.getText().length() == 0 || amount.getText().toString().length() == 0) {
            Toast.makeText(this, "Please enter in this field.",
                    Toast.LENGTH_LONG).show();
        } else {
            insertRecord();
            finishActivity();
        }
    }

    private void finishActivity() {

        Intent output = new Intent();
        setResult(RESULT_OK, output);
        finish();
    }

    private void insertRecord() {
        new AsyncTask<Void, Void, UserInfo>() {
            @Override
            protected UserInfo doInBackground(Void... voids) {
                UserInfo recordInfo = new UserInfo();
                recordInfo.setDetail(desc.getText() + "");
                recordInfo.setAmount(Integer.parseInt(amount.getText() + ""));
                recordInfo.setType(getType());
                userDB.getRecordInfoDAO().insert(recordInfo);
                return null;
            }

            @Override
            protected void onPostExecute(UserInfo recordInfo) {
                super.onPostExecute(recordInfo);
            }
        }.execute();

    }

    public String getType() {
        RadioGroup radioBtnGroup = findViewById(R.id.radioGroup);
        int selectedId = radioBtnGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.incomeRBtn) return "income";
        else if (selectedId == R.id.outcomeRBtn) return "expense";
        else return null;

    }
}

