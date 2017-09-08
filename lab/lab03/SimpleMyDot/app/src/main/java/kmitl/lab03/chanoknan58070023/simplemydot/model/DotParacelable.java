package kmitl.lab03.chanoknan58070023.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 9/8/2017 AD.
 */

public class DotParacelable implements Parcelable {

    protected DotParacelable(Parcel in) {
        centerX = in.readInt();
        centerY = in.readInt();
        radius = in.readInt();
        color = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(centerX);
        dest.writeInt(centerY);
        dest.writeInt(radius);
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DotParacelable> CREATOR = new Creator<DotParacelable>() {
        @Override
        public DotParacelable createFromParcel(Parcel in) {
            return new DotParacelable(in);
        }

        @Override
        public DotParacelable[] newArray(int size) {
            return new DotParacelable[size];
        }
    };

    private int centerX;
    private int centerY;
    private int radius;
    private int color;
}
