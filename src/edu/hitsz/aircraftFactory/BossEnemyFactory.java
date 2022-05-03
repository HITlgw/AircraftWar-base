package edu.hitsz.aircraftFactory;

import edu.hitsz.ShootingType.DiffuseShootingType;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossEnemyFactory extends EnemyAircraftFactory {

    public BossEnemyFactory(int defaultSpeedX,int defaultHp,int defaultPower) {
        super.defaultSpeedX = defaultSpeedX;
        super.defaultHp=defaultHp;
        super.defaultSpeedY=0;
        super.defaultPower=defaultPower;
    }

//原设定power=50，speedx=10
    @Override
    public AbstractAircraft createEnemyAircraft() {
        AbstractAircraft boss = new BossEnemy(Main.WINDOW_WIDTH / 2,
                ImageManager.BOSS_ENEMY_IMAGE.getHeight()/2,
                defaultSpeedX, defaultSpeedY, defaultHp,defaultPower);
        boss.setShootingType(new DiffuseShootingType());
        return boss;
    }
}
