package kmitl.lab03.chanoknan58070023.simplemydot.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kmitl.lab03.chanoknan58070023.simplemydot.model.Dot;
import kmitl.lab03.chanoknan58070023.simplemydot.model.Dots;

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

//        System.out.println(listDot + "  , at DotView");
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
