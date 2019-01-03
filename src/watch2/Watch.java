package watch2;

import static java.lang.Math.PI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Watch {
    private Calendar calendar = new GregorianCalendar();
    private double hours;
    private double minutes;
    private double seconds;
    private final Timer timer;
    private final List<Observer> observers = new ArrayList<>();
    private double SecondStep = 2 * PI / 60;
    private double MinuteStep = SecondStep / 60;
    private double HourStep = MinuteStep / 12;

    public Watch() {
        timer = new Timer();
        timer.schedule(timerTask(), 0, 1000);
        hours = normalize(Math.PI/2 - ((calendar.get(Calendar.HOUR_OF_DAY)%12)*60*60 + calendar.get(Calendar.MINUTE)*60 + calendar.get(Calendar.SECOND))*HourStep);
        minutes = normalize(Math.PI/2 - (calendar.get(Calendar.MINUTE)*60 + calendar.get(Calendar.SECOND))*MinuteStep);
        seconds = normalize(Math.PI/2 - calendar.get(Calendar.SECOND)*SecondStep);
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
