package kmitl.lab03.chanoknan58070023.simplemydot.model;

import java.io.Serializable;

/**
 * Created by student on 9/8/2017 AD.
 */

public class DotSerealizable implements Serializable {
    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
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

    public void setColor(int color) {
        this.color = color;
    }

    private int centerX;
    private int centerY;
    private int radius;
    private int color;
}
