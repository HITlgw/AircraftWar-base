package edu.hitsz.aircraftFactory;

import edu.hitsz.ShootingType.DiffuseShootingType;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossEnemyFactory implements EnemyAircraftFactory {
    @Override
    public AbstractAircraft createEnemyAircraft() {
        AbstractAircraft boss = new BossEnemy(Main.WINDOW_WIDTH / 2,
                ImageManager.BOSS_ENEMY_IMAGE.getHeight()/2,
                5, 0, 300);
        boss.setShootingType(new DiffuseShootingType());
        return boss;
    }
}
