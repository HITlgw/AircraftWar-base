package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;

public class HPSupply extends AbstractSupply{
    private int addHP;
    //默认addHP=100
    public HPSupply(int locationX, int locationY, int speedX, int speedY)
    {
        super(locationX, locationY, speedX, speedY);
        this.addHP=50;
    }
    //重新设置的addHP
    public HPSupply(int locationX, int locationY, int speedX, int speedY,int addHP)
    {
        super(locationX, locationY, speedX, speedY);
        this.addHP=addHP;
    }
    @Override
    public void getSupply(HeroAircraft hero){
        hero.addHP(addHP);
    }
}
