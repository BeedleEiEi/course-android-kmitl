package kmitl.lab03.chanoknan58070023.simplemydot.model;

public class Dot {

    private int centerX;
    private int centerY;
    private int radius;
    private OnDotChangedListener listener;

    public Dot(OnDotChangedListener listener, int centerX, int centerY, int radius) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.listener.onDotChanged(this); //this ตอนนี้คือ Dot

    }

    /*  Old constructor
        public Dot(int centerX, int centerY, int radius) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.radius = radius;
            this.listener.onDotChanged(this);
        }
    */
    public interface OnDotChangedListener {
        void onDotChanged(Dot dot);
    }

    public OnDotChangedListener getListener() {
        return listener;
    }

    public void setListener(OnDotChangedListener listener) {
        this.listener = listener;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
