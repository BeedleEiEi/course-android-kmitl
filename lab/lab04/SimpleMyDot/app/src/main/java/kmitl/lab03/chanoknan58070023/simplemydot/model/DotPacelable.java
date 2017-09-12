package kmitl.lab03.chanoknan58070023.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 9/8/2017 AD.
 */

public class DotPacelable implements Parcelable{
    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return Radius;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setRadius(int radius) {
        Radius = radius;
    }

    private int centerX;
    private int centerY;
    private int Radius;

    public DotPacelable(int centerX, int centerY, int radius){
        this.centerX = centerX;
        this.centerY = centerY;
        this.Radius = radius;
    }


    protected DotPacelable(Parcel in) {
        centerX = in.readInt();
        centerY = in.readInt();
        Radius = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(centerX);
        dest.writeInt(centerY);
        dest.writeInt(Radius);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
