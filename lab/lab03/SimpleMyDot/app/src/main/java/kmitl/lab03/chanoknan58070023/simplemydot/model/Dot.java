package kmitl.lab03.chanoknan58070023.simplemydot.model;

import android.graphics.Color;
import android.view.View;

import java.util.Random;

public class Dot {

    private float centerX;
    private float centerY;
    private int radius;
    private OnDotChangedListener listener;
    private View.OnTouchListener listenerTouch;
    private int color;
    Random rand = new Random();

    public Dot(OnDotChangedListener listener, int centerX, int centerY, int radius) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.listener.onDotChanged(this); //this ตอนนี้คือ Dot

    }

    public Dot(OnDotChangedListener listener, int centerX, int centerY, int radius, int color) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = -1;
        this.listener.onDotChanged(this);
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

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void randomColor() {

        this.color = Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

    }

    public void setColor() {
        this.randomColor();
    }

    public int getColor() {
        return this.color;
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
