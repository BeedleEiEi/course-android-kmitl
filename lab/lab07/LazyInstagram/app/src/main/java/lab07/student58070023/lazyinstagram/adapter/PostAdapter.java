package lab07.student58070023.lazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import lab07.student58070023.lazyinstagram.MainActivity;
import lab07.student58070023.lazyinstagram.R;
import lab07.student58070023.lazyinstagram.api.LazyInstagramApi;
import lab07.student58070023.lazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by student on 10/6/2017 AD.
 */

class Holder extends RecyclerView.ViewHolder{
    public ImageView image;
    public TextView textLike;
    public Holder(View itemView){
        super(itemView);
        image = itemView.findViewById(R.id.imageView);
        textLike = itemView.findViewById(R.id.textLike);

    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder>{

        String[] data = {
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n1.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n2.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n3.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n4.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n5.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n6.jpg"
    };

    private Context context;
    private UserProfile userProfile;



    public PostAdapter(Context context) {
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //ทำให้แอพใช้ memory น้อย เร็ว
        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); //สร้าง view เพื่อยัดลง holder
        View itemView = inflater.inflate(R.layout.post_item, null, false);

        Holder holder = new Holder(itemView);
        getUserProfile("nature");
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        TextView textLike = holder.textLike;

        Glide.with(context).load(data[position]).into(image); //load data มาใส่ image
        //textLike.setText(userProfile.getPosts());




    }

    @Override
    public int getItemCount() {
        return data.length; //return ความยาว item
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
                    userProfile = response.body();
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }


}
