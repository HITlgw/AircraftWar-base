package edu.hitsz.supply;

import edu.hitsz.ShootingType.DiffuseShootingType;
import edu.hitsz.ShootingType.StraightShootingType;
import edu.hitsz.aircraft.HeroAircraft;

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
    private int addBullet;
    //默认Addbullet=1
    public BulletSupply(int locationX, int locationY, int speedX, int speedY){
        super(locationX,locationY,speedX,speedY);
        this.addBullet =1;
    }
    //设置Addbullet
    public BulletSupply(int locationX, int locationY, int speedX, int speedY,int addBullet){
        super(locationX,locationY,speedX,speedY);
        this.addBullet =addBullet;
    }
    @Override
    public void getSupply(HeroAircraft hero){
//        hero.addBullet(addBullet);
        Thread thread = new Thread(()-> {
            synchronized (HeroAircraft.shootnum_Lock) {     //加时序锁防止连续获得多个bullet道具产生问题
                hero.increaseShootNum();
                hero.setShootingType(new DiffuseShootingType());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hero.decreaseShootNum();
                hero.setShootingType(new StraightShootingType());
            }
        });
        thread.start();
    }
}
