package bussinessLayer.domain.observer;

import java.util.HashSet;
import java.util.Set;

public class Observable
{
    private final Set<Observer> observers = new HashSet<>();

    public void notifyAllObservers()
    {
        for (Observer observer: this.observers)
        {
            observer.update();
        }
    }

    public void addObserver(Observer observer)
    {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer)
    {
        this.observers.remove(observer);
    }

}
