package edu.hitsz.Factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossEnemyFactory implements EnemyAircraftFactory {
    @Override
    public AbstractAircraft createEnemyAircraft() {
        AbstractAircraft boss = new BossEnemy(Main.WINDOW_WIDTH / 2,
                ImageManager.BOSS_ENEMY_IMAGE.getHeight(),
                0, 0, 100);
        return boss;
    }
}
