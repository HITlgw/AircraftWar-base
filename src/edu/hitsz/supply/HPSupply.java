package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;

public class HPSupply extends AbstractSupply{
    private int addHp;
    //默认addHP=50
    public HPSupply(int locationX, int locationY, int speedX, int speedY)
    {
        super(locationX, locationY, speedX, speedY);
        this.addHp =50;
    }
    //重新设置的addHP
    public HPSupply(int locationX, int locationY, int speedX, int speedY,int addHp)
    {
        super(locationX, locationY, speedX, speedY);
        this.addHp =addHp;
    }

    @Override
    public void getSupply(HeroAircraft hero){
        hero.addHp(addHp);
    }
}
