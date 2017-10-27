package assignment.beedle.espresso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoListActivity extends AppCompatActivity {

    public static final String EXTTRA_LIST = "EXTTRA_LIST";
    public static int status = 0;

    @BindView(R.id.list)
    public RecyclerView list;

    @BindView(R.id.textNotFound)
    public TextView textNotFound;

    private MyAdapter adapter;
    private CommonSharePreference preference;
    public UserInfoList suggestSearchList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_list);
        ButterKnife.bind(this);
        preference = new CommonSharePreference(this);

        adapter = new MyAdapter();
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        init();

    }

    public void init() {
        suggestSearchList = (UserInfoList) preference.read(UserInfoListActivity.EXTTRA_LIST, UserInfoList.class);
        if (suggestSearchList != null) {
            displaySuggestsList(suggestSearchList.getUserInfoList());
        } else {
            displaySuggestsList(new ArrayList<UserInfo>());
        }
    }

    public void displaySuggestsList(List<UserInfo> suggestsList) {
        if (suggestsList.size() <= 0) {
            textNotFound.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        } else {
            textNotFound.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
            adapter.setData(suggestsList);
            adapter.notifyDataSetChanged();
        }

    }

    public void clearList(View view) {
        status = 1;
        preference.save(UserInfoListActivity.EXTTRA_LIST, null);
        displaySuggestsList(new ArrayList<UserInfo>());
    }

}
