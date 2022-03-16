package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;

/**
 * *
 * class BulletSupply{
 *     - int AddBullet
 *     + BulletSupply(int locationX, int locationY,
 *      int speedX, int speedY):void
 *     + getSupply(HeroAircraft Hero)
 * }
 */
public class BulletSupply extends AbstractSupply{
    private int AddBullet;
    //默认Addbullet=1
    public BulletSupply(int locationX, int locationY, int speedX, int speedY){
        super(locationX,locationY,speedX,speedY);
        this.AddBullet=1;
    }
    //设置Addbullet
    public BulletSupply(int locationX, int locationY, int speedX, int speedY,int AddBullet){
        super(locationX,locationY,speedX,speedY);
        this.AddBullet=AddBullet;
    }
    public void getSupply(HeroAircraft hero){
        hero.addBullet(AddBullet);
        System.out.println("FireSupply active!");
    }
}
