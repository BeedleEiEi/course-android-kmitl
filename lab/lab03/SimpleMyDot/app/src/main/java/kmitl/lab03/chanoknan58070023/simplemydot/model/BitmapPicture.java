package kmitl.lab03.chanoknan58070023.simplemydot.model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;

import kmitl.lab03.chanoknan58070023.simplemydot.R;

/**
 * Created by Beedle on 10/9/2560.
 */

public class BitmapPicture {
    private int width;
    private int height;
    private Bitmap bitmap;
    final View view;

    public BitmapPicture(View root){
        this.view = root;

    }

    public BitmapPicture(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Bitmap snap() {
        bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        return bitmap;
    }
}
