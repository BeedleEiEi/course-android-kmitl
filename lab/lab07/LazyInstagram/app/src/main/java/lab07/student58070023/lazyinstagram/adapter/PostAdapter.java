package lab07.student58070023.lazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import lab07.student58070023.lazyinstagram.R;
import lab07.student58070023.lazyinstagram.model.UserPosts;

/**
 * Created by student on 10/6/2017 AD.
 */

class Holder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView textLike;
    public TextView textComment;


    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.imageView);
        textLike = itemView.findViewById(R.id.textLike);
        textComment = itemView.findViewById(R.id.textComment);
    }

    public void clearTextView() {
        textComment.setText("");
        textLike.setText("");
    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder> {
    private Context context;
    private List<UserPosts> data;


    public PostAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setData(List<UserPosts> data) {
        this.data = data;
    }

    public void clearData() {
        data.clear();

    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //ทำให้แอพใช้ memory น้อย เร็ว
        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); //สร้าง view เพื่อยัดลง holder
        View itemView = inflater.inflate(R.layout.post_item, null, false);
        Holder holder = new Holder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        TextView textLike = holder.textLike;
        TextView textComment = holder.textComment;
        String imageUrl = data.get(position).getUrl();
        Glide.with(context).load(imageUrl).into(image); //load data มาใส่ image

        textLike.setText("Like: " + Integer.toString(data.get(position).getLike()));
        textComment.setText("Comment: " + Integer.toString(data.get(position).getComment()));

    }

    @Override
    public int getItemCount() {
        return data.size(); //return ความยาว item
    }


}
