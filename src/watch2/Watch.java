package watch2;

import static java.lang.Math.PI;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Watch {
    private double hours = Math.PI/2;
    private double minutes = Math.PI/2;
    private double seconds = Math.PI/2;
    private final Timer timer;
    private final List<Observer> observers = new ArrayList<>();
    private double SecondStep = 2 * PI / 60;
    private double MinuteStep = SecondStep / 60;
    private double HourStep = MinuteStep / 12;

    public Watch() {
        timer = new Timer();
        timer.schedule(timerTask(), 0, 1000);
    }

    public double getHours() {
        return hours;
    }

    public double getMinutes() {
        return minutes;
    }

    public double getSeconds() {
        return seconds;
    }
    
    

    private TimerTask timerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                step();
                updateObservers();
            }

        };
    }
    
    private void step() {
        seconds = normalize(seconds - SecondStep);
        minutes = normalize(minutes - MinuteStep);
        hours = normalize(hours - HourStep);
    }
    
    public void add(Observer observer) {
        observers.add(observer);
    }
    
    private void updateObservers() {
        for (Observer observer : observers) 
            observer.update();
    }

    private double normalize(double v) {
        return v % (2 * PI);
    }

    public interface Observer {

        public void update();

    }

    
}
