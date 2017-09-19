package kmitl.lab03.chanoknan58070023.simplemydot.model;

import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beedle on 8/9/2560.
 */

public class Dots {

    private OnDotsChangeListener listener;
    private List<Dot> listDot = new ArrayList<>();

    public interface OnDotsChangeListener {
        void onDotsChanged(Dots dots);
    }

    public void setListener(OnDotsChangeListener listener) {
        this.listener = listener;
    }

    public List<Dot> getAllDot() {
        return listDot;
    }

    public void addDot(Dot dot) {
        this.listDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void removeBy(int position) {
        listDot.remove(position);
        this.listener.onDotsChanged(this);
    }

    public void clearAll() {
        listDot.clear();
        this.listener.onDotsChanged(this);
    }

    public int findDot(int x, int y) {
        for (int i = 0; i < listDot.size(); i++) {
            int centerX = (int) listDot.get(i).getCenterX();
            int centerY = (int) listDot.get(i).getCenterY();
            double distance = Math.sqrt(Math.pow(centerX - x, 2)) +
                    Math.sqrt(Math.pow(centerY - y, 2));
            if (distance <= 30) {
                return i;
            }
        }
        return -1;
    }

    //old way
    public boolean isOverlap(float px, float py, float rad) {
        for (Dot dot : listDot) {
            double distance = Math.pow(Math.pow(dot.getCenterX() - px, 2) + Math.pow(dot.getCenterY() - py, 2), 0.5);
            if (distance <= rad) {
                //alert
                return true;
            }
        }
        return false;
    }
}
