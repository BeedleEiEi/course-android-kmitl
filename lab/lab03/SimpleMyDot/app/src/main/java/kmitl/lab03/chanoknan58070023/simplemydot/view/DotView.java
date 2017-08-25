package kmitl.lab03.chanoknan58070023.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import kmitl.lab03.chanoknan58070023.simplemydot.model.Dot;


public class DotView extends View {
    public void setDot(Dot dot) {
        this.dot = dot;
    }

    public Dot getDot() {

        return dot;
    }

    private Dot dot;
    private Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        if (this.dot != null) {
            canvas.drawCircle(this.dot.getCenterX(), this.dot.getCenterY(), 30, paint);
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
