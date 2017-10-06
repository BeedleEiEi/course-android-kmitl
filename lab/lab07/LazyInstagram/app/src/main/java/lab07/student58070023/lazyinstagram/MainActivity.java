package lab07.student58070023.lazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import lab07.student58070023.lazyinstagram.adapter.PostAdapter;
import lab07.student58070023.lazyinstagram.api.LazyInstagramApi;
import lab07.student58070023.lazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile("nature"); //เรียก method
        PostAdapter postAdapter = new PostAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.list); //bind list view
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(postAdapter); // set adapter
    }



    private void getUserProfile(String userName){
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
                if(response.isSuccessful()){
                    UserProfile userProfile = response.body();
                    display(userProfile);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }

    private void display(UserProfile userProfile){

        TextView textUser = (TextView) findViewById(R.id.textUser);
        textUser.setText("@"+userProfile.getUser());

        TextView textPost = (TextView) findViewById(R.id.textPost);
        textPost.setText("Post\n"+userProfile.getPost());

        TextView textFollower = (TextView) findViewById(R.id.textFollower);
        textFollower.setText("Follower\n"+userProfile.getFollower());

        TextView textFollwing = (TextView) findViewById(R.id.textFollowing);
        textFollwing.setText("Following\n"+userProfile.getFollowing());

        TextView textBio = (TextView) findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());

        ImageView imageProfile = (ImageView) findViewById(R.id.imageProfile);
        Glide.with(this).load(userProfile.getUrlProfile()).into(imageProfile);

    }


}
