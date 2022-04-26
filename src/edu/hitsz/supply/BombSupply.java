package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.misic.otherMusicThread;

public class BombSupply extends AbstractSupply{

    public BombSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void getSupply(HeroAircraft hero) {
        System.out.println("BombSupply active!");
        Thread bumbthread = new otherMusicThread("src/vedio/bomb_explosion.wav");
        bumbthread.start();
    }
}
