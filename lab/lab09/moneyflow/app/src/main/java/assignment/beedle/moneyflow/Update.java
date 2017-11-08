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

public class Update extends Activity implements View.OnClickListener {

    private EditText desc, amount;
    private UserDB userDB;
    private UserInfo recordInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);
        Button saveBtn = findViewById(R.id.save_Btn);
        Button deleteBtn = findViewById(R.id.deleteBtn);

        saveBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        recordInfo = getIntent().getParcelableExtra("UserParcel");
        userDB = Room.databaseBuilder(this, UserDB.class, "RECORD").build();

        desc = findViewById(R.id.update_desc_et);
        amount = findViewById(R.id.update_amount_et);

        desc.setText(recordInfo.getDetail());
        amount.setText(String.valueOf((int) recordInfo.getAmount()));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_Btn:
                if (desc.getText().length() == 0 || amount.getText().toString().length() == 0) {
                    Toast.makeText(this, "Please fill information correctly!.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    updateRecord();
                    finishActivity();
                }
                break;
            case R.id.deleteBtn:
                deleteRecord();
                finishActivity();
                break;
        }

    }

    private void deleteRecord() {
        new AsyncTask<Void, Void, UserInfo>() {
            @Override
            protected UserInfo doInBackground(Void... voids) {
                userDB.getRecordInfoDAO().delete(recordInfo);
                return null;
            }
        }.execute();

    }

    private void finishActivity() {
        Intent output = new Intent();
        setResult(RESULT_OK, output);
        finish();
    }

    private void updateRecord() {
        new AsyncTask<Void, Void, UserInfo>() {
            @Override
            protected UserInfo doInBackground(Void... voids) {
                recordInfo.setDetail(desc.getText() + "");
                recordInfo.setAmount(Integer.parseInt(amount.getText() + ""));
                recordInfo.setType(getType());
                userDB.getRecordInfoDAO().update(recordInfo);
                return null;
            }

            @Override
            protected void onPostExecute(UserInfo recordInfo) {
                super.onPostExecute(recordInfo);
            }
        }.execute();

    }

    public String getType() {
        RadioGroup radioBtnGroup = findViewById(R.id.radio_Group);
        int selectedId = radioBtnGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.income_RBtn) return "income";
        else if (selectedId == R.id.outcome_RBtn) return "expense";
        else return null;
    }

    {
    }
}
