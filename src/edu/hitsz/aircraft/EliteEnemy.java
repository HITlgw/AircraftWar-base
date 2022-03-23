package edu.hitsz.aircraft;
/**
 * V1:新增精英敌机
 *
 */

import Factory.BombSupplyFactory;
import Factory.BulletSupplyFactory;
import Factory.HPSupplyFactory;
import Factory.SupplyFactory;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.supply.AbstractSupply;
import edu.hitsz.supply.BombSupply;
import edu.hitsz.supply.BulletSupply;
import edu.hitsz.supply.HPSupply;

import java.util.LinkedList;
import java.util.List;

public class EliteEnemy extends AbstractAircraft{
//    private int shootNum = 1;
    private int power = 30;
    private int direction = 1;
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }
    @Override
    public List<AbstractBullet> shoot() {
        List<AbstractBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*5;
        AbstractBullet abstractBullet = new EnemyBullet(x,y,speedX,speedY,power);
        res.add(abstractBullet);
        return res;
    }

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
    public AbstractSupply generateSupply() {
        double typenum = Math.random();
        if(typenum<0.4){
            return new HPSupplyFactory().createSupply(locationX,locationY,0,10);
        }
        else if(typenum<0.8){
            return new BulletSupplyFactory().createSupply(locationX,locationY,0,10);
        }
        else{
            return new BombSupplyFactory().createSupply(locationX,locationY,0,10);
        }
    }
}
