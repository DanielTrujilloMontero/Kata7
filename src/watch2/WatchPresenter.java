package watch2;

import java.awt.Point;
import static java.lang.Math.*;

public class WatchPresenter implements Watch.Observer {
    private final Watch watch;
    private final WatchDisplay watchDisplay;

    public WatchPresenter(Watch watch, WatchDisplay watchDisplay) {
        this.watch = watch;
        this.watch.add(this);
        this.watchDisplay = watchDisplay;
        this.watchDisplay.paint(pointsOf(watch));
    }

    @Override
    public void update() {
        refreshDisplay();
    }

    private void refreshDisplay() {
        watchDisplay.paint(pointsOf(watch));
    }

    private Point[] pointsOf(Watch watch) {
        Point[] points = new Point[3];
        points[0] = pointOf(watch.getSeconds(), 150);
        points[1] = pointOf(watch.getMinutes(), 100);
        points[2] = pointOf(watch.getHours(), 50);
        return points;
    }

    private Point pointOf(double angle, int length) {
        return new Point(toInt(length*cos(angle)), toInt(length*sin(angle)));
    }
    
    private int toInt(double v) {
        return (int) v;
    }
    
    
    
}
