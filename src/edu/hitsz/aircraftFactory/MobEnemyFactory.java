package edu.hitsz.aircraftFactory;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobEnemyFactory extends EnemyAircraftFactory {

    public MobEnemyFactory(int defaultSpeedY,int defaultHp) {
        super.defaultSpeedY=defaultSpeedY;
        super.defaultHp=defaultHp;
        super.defaultSpeedX=0;
        super.defaultPower=0;
    }
//原设定：speedY=10，speedX=0，hp=30
    @Override
    public AbstractAircraft createEnemyAircraft() {
        return new MobEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                defaultSpeedX,
                defaultSpeedY,
                defaultHp
        );
    }
}
