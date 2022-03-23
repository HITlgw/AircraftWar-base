package edu.hitsz.aircraft;
/**
 * V1:新建Boss敌机
 * 发射操作未实现
 *
 */

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.supply.AbstractSupply;

import java.util.List;

public class BossEnemy extends AbstractAircraft {
    private int shootNum = 2;
    private int power = 50;
    private int direction = 1;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public List<AbstractBullet> shoot() {
        return null;
    }
    //V2:将产生Supply移入BossEnemy
    @Override
    public AbstractSupply generateSupply() {
        return null;
    }
}
