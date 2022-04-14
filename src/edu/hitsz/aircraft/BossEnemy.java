package edu.hitsz.aircraft;
/**
 * V1:新建Boss敌机
 * 发射操作未实现
 *
 */

import edu.hitsz.aircraftFactory.BombSupplyFactory;
import edu.hitsz.aircraftFactory.BulletSupplyFactory;
import edu.hitsz.aircraftFactory.HPSupplyFactory;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.supply.AbstractSupply;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends AbstractAircraft {
    private int shootNum = 3;
    private int power = 50;
    private int direction = 1;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public List<AbstractBullet> shoot() {
        return this.shootingType.doShoot(1,power,shootNum,this.getLocationX(),this.getLocationY());
    }
//    public List<AbstractBullet> shoot() {
//        List<AbstractBullet> res = new LinkedList<>();
//        int x = this.getLocationX();
//        int y = this.getLocationY() + direction*2;
//        int speedX = 0;
//        int speedY = this.getSpeedY() + direction*5;
//        AbstractBullet abstractBullet1 = new EnemyBullet(x,y,speedX,speedY,power);
//        res.add(abstractBullet1);
//        AbstractBullet abstractBullet2 = new EnemyBullet(x+2,y,speedX+2,speedY,power);
//        res.add(abstractBullet2);
//        AbstractBullet abstractBullet3 = new EnemyBullet(x+2,y,speedX-2,speedY,power);
//        res.add(abstractBullet3);
//        return res;
//    }
    //V2:将产生Supply移入BossEnemy
    @Override
    public List<AbstractSupply> generateSupply() {
        List<AbstractSupply> newSupplyList = new LinkedList<>();
        newSupplyList.add(new HPSupplyFactory().createSupply(super.locationX+8,super.locationY,0,10));
        newSupplyList.add(new BulletSupplyFactory().createSupply(super.locationX-8,super.locationY,0,10));
        if(Math.random()>0.5)
        {
            newSupplyList.add(new BombSupplyFactory().createSupply(super.locationX,super.locationY+5,0,10));
        }
        return newSupplyList;
    }
}
