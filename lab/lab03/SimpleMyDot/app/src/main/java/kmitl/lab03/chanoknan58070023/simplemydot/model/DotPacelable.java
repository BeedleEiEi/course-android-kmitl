package kmitl.lab03.chanoknan58070023.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 9/8/2017 AD.
 */
public class DotPacelable implements Parcelable {
    private int centerX;
    private int centerY;
    private int radius;
    private int dotPosition;
    private int color;

    public DotPacelable(int dotPosition) {
        this.dotPosition = dotPosition;

    }

    public DotPacelable(int dotPosition, int color) {
        this.dotPosition = dotPosition;
        this.color = color;
    }

    public DotPacelable(int dotPosition, int color, int radius) {
        this.dotPosition = dotPosition;
        this.color = color;
        this.radius = radius;
    }


    public DotPacelable(int centerX, int centerY, int radius, int dotPosition) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.dotPosition = dotPosition;
    }


    protected DotPacelable(Parcel in) {
        centerX = in.readInt();
        centerY = in.readInt();
        radius = in.readInt();
        dotPosition = in.readInt();
        color = in.readInt();
    }

    public static final Creator<DotPacelable> CREATOR = new Creator<DotPacelable>() {
        @Override
        public DotPacelable createFromParcel(Parcel in) {
            return new DotPacelable(in);
        }

        @Override
        public DotPacelable[] newArray(int size) {
            return new DotPacelable[size];
        }
    };

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getDotPosition() {
        return dotPosition;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setDotPosition(int dotPosition) {
        this.dotPosition = dotPosition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(centerX);
        dest.writeInt(centerY);
        dest.writeInt(radius);
        dest.writeInt(dotPosition);
        dest.writeInt(color);
    }
}
