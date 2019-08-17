package chap8;

import java.util.ArrayList;
import java.util.List;

public class Moon {

    private final List<LandingObserver> observers = new ArrayList<>();

    public void land(String name) {
        for (LandingObserver observer: observers) {
            observer.observeLanding(name);
        }
    }

    public void startSpying(LandingObserver observer) {
        observers.add(observer);
    }

    public static void main(String[] args) {
        Moon moon = new Moon();
        moon.startSpying(name -> {
            if (name.contains("Apollo"))
                System.out.println("We made it!");
        });
        moon.startSpying(name -> {
            if (name.contains("Apollo"))
                System.out.println("They're distracted, lets invade earth!");
        });
        moon.land("Apollo 11");
        moon.land("An asteroid");
    }
}
