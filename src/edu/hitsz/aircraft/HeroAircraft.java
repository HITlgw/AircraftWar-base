package edu.hitsz.aircraft;

import edu.hitsz.ShootingType.StraightShootingType;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.supply.AbstractSupply;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 * V2:修改为单例模式，构造函数私有
 */
public class HeroAircraft extends AbstractAircraft {

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = -1;
    //类的静态私有对象
    private static HeroAircraft hero;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
//    public List<AbstractBullet> shoot() {
//        List<AbstractBullet> res = new LinkedList<>();
//        int x = this.getLocationX();
//        int y = this.getLocationY() + direction*2;
//        int speedX = 0;
//        int speedY = this.getSpeedY() + direction*5;
//        AbstractBullet abstractBullet;
//        for(int i=0; i<shootNum; i++){
//            // 子弹发射位置相对飞机位置向前偏移
//            // 多个子弹横向分散
//            abstractBullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
//            res.add(abstractBullet);
//        }
//        return res;
//    }
    public List<AbstractBullet> shoot() {
        return this.shootingType.doShoot(-1,power,shootNum,this.getLocationX(),this.getLocationY());
    }

    @Override
    public List<AbstractSupply> generateSupply() {
        return null;
    }

    // V1:增加加血和加子弹操作（实现补给）
    public void addHp(int addHp){
        this.hp+=addHp;
        if(this.hp>maxHp){
            this.hp=maxHp;
        }
    }
    public void addBullet(int addBullet){
        this.shootNum+=addBullet;
    }

    //饿汉式
//    static
//    {
//        hero=new HeroAircraft(
//                Main.WINDOW_WIDTH / 2,
//                Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
//                0, 0, 100);
//    }
//    public static HeroAircraft getinstance()
//    {
//        return hero
//    }

    //懒汉式
//    public static synchronized HeroAircraft getinstance()
//    {
//        if(hero==null)
//        {
//            hero=new HeroAircraft(
//                    Main.WINDOW_WIDTH / 2,
//                    Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
//                    0, 0, 100);
//        }
//        return hero;
//
//    }


    //V2:双重检查锁定
    public static HeroAircraft getinstance()
    {
        if(hero==null)
        {
            synchronized (HeroAircraft.class)
            {
                if(hero==null)
                {
                    hero=new HeroAircraft(
                            Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                            0, 0, 100);
                    //V4 初始时默认直射
                    hero.setShootingType(new StraightShootingType());
                }
            }
        }

        return hero;
    }
    public void increaseShootNum()
    {
        this.shootNum++;
    }


}
