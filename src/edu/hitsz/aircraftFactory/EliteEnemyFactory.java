package edu.hitsz.aircraftFactory;

import edu.hitsz.ShootingType.AbstractShootingType;
import edu.hitsz.ShootingType.StraightShootingType;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteEnemyFactory extends EnemyAircraftFactory {


    public EliteEnemyFactory(int defaultSpeedX,int defaultSpeedY,int defaultPower,int defaultHp){
        super.defaultHp=defaultHp;
        super.defaultSpeedX=defaultSpeedX;
        super.defaultSpeedY=defaultSpeedY;
        super.defaultPower=defaultPower;
    }
//原设定speedx=10，speedy=5，hp=30
    @Override
    public AbstractAircraft createEnemyAircraft() {
//        AbstractAircraft elite = new EliteEnemy(
//                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
//                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
//                (int)(defaultSpeedX*(Math.random()-0.5)),
//                defaultSpeedY,
//                defaultHp
//        );
        AbstractAircraft elite = new EliteEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                (int)(defaultSpeedX*(Math.random()-0.5)),
                defaultSpeedY,
                defaultHp
        );
        AbstractShootingType shootingType= new StraightShootingType();
        elite.setShootingType(shootingType);
        return elite;
    }
}
