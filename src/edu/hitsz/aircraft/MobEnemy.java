package edu.hitsz.aircraft;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.basic.Observer;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.supply.AbstractSupply;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractAircraft implements Observer {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationX >= Main.WINDOW_HEIGHT || locationX <= 0) {
            vanish();
        }
    }

    @Override
    public List<AbstractBullet> shoot() {
//        return new LinkedList<>();
        return null;
    }
    //V2:将产生Supply移入MObEnemy
    @Override
    public List<AbstractSupply> generateSupply() {
        return null;
    }

    @Override
    public void update() {
        this.vanish();
    }
}
