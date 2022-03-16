package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;

public class BombSupply extends AbstractSupply{

    public BombSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void getSupply(HeroAircraft hero) {
        System.out.println("BombSupply active!");
    }
}
