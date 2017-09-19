package kmitl.lab03.chanoknan58070023.simplemydot.model;

import android.graphics.Color;
import android.view.View;

import java.util.Random;

public class Dot {

    private float centerX;
    private float centerY;
    private int radius;
    private int color;
    Random rand = new Random();

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

    }

    public Dot(int centerX, int centerY, int radius, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = -1;
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
    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
