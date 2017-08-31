package kmitl.lab03.chanoknan58070023.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import kmitl.lab03.chanoknan58070023.simplemydot.model.Dot;


public class DotView extends View {

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    public void setDot(ArrayList<Dot> listDot) {
        this.listDot = listDot;
    }

    public Dot getDot() {

        return dot;
    }

    private Dot dot;
    private Paint paint;
    private ArrayList<Dot> listDot; //Create Array for dot
    private OnTouchListener onTouch;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        System.out.println(listDot + "  , at DotView");
        if (listDot != null) {
            for (Dot dot : listDot) {
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

}
