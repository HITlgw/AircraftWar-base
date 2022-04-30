package edu.hitsz.aircraft;
/**
 * V1:新增精英敌机
 *
 */

import edu.hitsz.aircraftFactory.BombSupplyFactory;
import edu.hitsz.aircraftFactory.BulletSupplyFactory;
import edu.hitsz.aircraftFactory.HPSupplyFactory;
import edu.hitsz.application.Main;
import edu.hitsz.basic.Observer;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.supply.AbstractSupply;

import java.util.LinkedList;
import java.util.List;

public class EliteEnemy extends AbstractAircraft implements Observer {
    private int power = 30;
//    private int direction = 1;
    private int shootNum = 1;
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
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
//        AbstractBullet abstractBullet = new EnemyBullet(x,y,speedX,speedY,power);
//        res.add(abstractBullet);
//        return res;
//    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    //V2:将产生Supply移入EliteEnemy
    @Override
    public List<AbstractSupply> generateSupply() {
        double typenum = Math.random();
        List<AbstractSupply> newSupplyList = new LinkedList<>();
        if(typenum<0.4){
            newSupplyList.add(new HPSupplyFactory().createSupply(locationX,locationY,0,10));
        }
        else if(typenum<0.8){
            newSupplyList.add(new BulletSupplyFactory().createSupply(locationX,locationY,0,10));
        }
        else{
            newSupplyList.add(new BombSupplyFactory().createSupply(locationX,locationY,0,10));
        }
        return newSupplyList;
    }

    @Override
    public void update() {
        this.vanish();
    }
}
