package lab07.student58070023.lazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import lab07.student58070023.lazyinstagram.adapter.PostAdapter;
import lab07.student58070023.lazyinstagram.api.LazyInstagramApi;
import lab07.student58070023.lazyinstagram.model.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private PostAdapter postAdapter;
    private UserProfile userProfile;
    private ArrayAdapter<String> adapter;
    private String accountName = "android";
    private Spinner dropDown;
    private RecyclerView recyclerView;
    private String layoutType = "grid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile("android"); //เรียก method
        dropDown = findViewById(R.id.dropDown);
        setAccount(); //set Account to Spinner
        addListenerOnSpinnerItemSelectionr();


    }

    private void setAccount() {
        List<String> list = new ArrayList<String>();
        list.add("android");
        list.add("cartoon");
        list.add("nature");

        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item); //ให้ adapter เก็บ dropdown
        dropDown.setAdapter(adapter); //ให้ spinner เก็บ dropdown
    }

    public void addListenerOnSpinnerItemSelectionr() {
        dropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                accountName = parent.getItemAtPosition(position).toString(); //assign account name to selected account
                Toast.makeText(parent.getContext(),
                        "You selected : " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
                updateAccount();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateAccount() {
        if (postAdapter != null) {
            postAdapter.clearData();
            getUserProfile(accountName);
        }

    }


    private void getUserProfile(String userName) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LazyInstagramApi.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LazyInstagramApi lazyInstagramApi =
                retrofit.create(LazyInstagramApi.class); //Factory Design Pattern สร้าง obj จาก innterface

        Call<UserProfile> call = lazyInstagramApi.getProfile(userName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    UserProfile userProfile = response.body();
                    display(userProfile);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }

    private void display(UserProfile userProfile) {

        postAdapter = new PostAdapter(this);
        postAdapter.setLayoutType(layoutType);
        this.postAdapter.setData(userProfile.getPosts());
        //updateAccount();

        TextView textUser = findViewById(R.id.textUser);
        textUser.setText("@" + userProfile.getUser());

        TextView textPost = findViewById(R.id.textPost);
        textPost.setText("Post\n" + userProfile.getPost());

        TextView textFollower = findViewById(R.id.textFollower);
        textFollower.setText("Follower\n" + userProfile.getFollower());

        TextView textFollwing = findViewById(R.id.textFollowing);
        textFollwing.setText("Following\n" + userProfile.getFollowing());

        TextView textBio = findViewById(R.id.textBio);
        textBio.setText("Test: " + userProfile.getBio());

        ImageView imageProfile = findViewById(R.id.imageProfile);
        Glide.with(this).load(userProfile.getUrlProfile()).into(imageProfile);

        recyclerView = findViewById(R.id.list);
        if (layoutType.equals("grid")) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        recyclerView.setAdapter(postAdapter);
    }

    public void listMode(View view) {
        this.layoutType = "list";
        getUserProfile(accountName);
    }

    public void gridMode(View view) {
        this.layoutType = "grid";
        getUserProfile(accountName);
    }
}
