package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.basic.Observer;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.misic.otherMusicThread;

import java.util.ArrayList;
import java.util.List;

public class BombSupply extends AbstractSupply{
    private ArrayList<Observer> observers;

    public BombSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
        this.observers=new ArrayList<>();
    }

    @Override
    public void getSupply(HeroAircraft hero) {
        System.out.println("BombSupply active!");
        notifyAllObservers();
        Thread bumbthread = new otherMusicThread("src/video/bomb_explosion.wav");
        bumbthread.start();
    }

    public void addObserver(Observer o){
        observers.add(o);
    }

    public void deleteObserver(Observer o){
        observers.remove(o);
    }

    public void notifyAllObservers() {
        for (Observer o:observers) {
            o.update();
        }
    }
}
