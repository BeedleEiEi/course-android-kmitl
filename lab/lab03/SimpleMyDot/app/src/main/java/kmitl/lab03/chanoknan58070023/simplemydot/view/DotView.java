package kmitl.lab03.chanoknan58070023.simplemydot.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kmitl.lab03.chanoknan58070023.simplemydot.model.Dot;
import kmitl.lab03.chanoknan58070023.simplemydot.model.Dots;

import static android.R.attr.bitmap;
import static kmitl.lab03.chanoknan58070023.simplemydot.R.id.dotView;


public class DotView extends View {

    public Dot getDot() {

        return dot;
    }

    private Dot dot;
    private Paint paint;
    private Dots listDot; //Create Array for dot
    private OnTouchListener onTouch;
    private Context context = super.getContext();
    private OnDotViewClickListener onDotViewClickListener;
    private int positionDot = -1;

    public interface OnDotViewClickListener {
        void onDotViewClicked(int x, int y);
    }

    public Bitmap createBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        view.draw(c);
        return bitmap;
    }

    public void saveBitmap(Bitmap bitmap) {
        // save bitmap to cache directory
        try {
            File cachePath = new File(context.getCacheDir(), "images");
            cachePath.mkdirs(); // don't forget to make the directory
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOnDotViewClickListener(
            OnDotViewClickListener onDotViewClickListener) {
        this.onDotViewClickListener = onDotViewClickListener;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.onDotViewClickListener
                        .onDotViewClicked(
                                (int) event.getX(),
                                (int) event.getY());
                return true;
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.listDot != null) {
            for (Dot dot : listDot.getAllDot()) {
                paint.setColor(dot.getColor());
                canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
            }
        }
    }


    public DotView(Context context) {
        super(context);
        paint = new Paint();

    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public void setDots(Dots dots) {
        this.listDot = dots;
    }

}
