package edu.hitsz.aircraftFactory;

import edu.hitsz.ShootingType.AbstractShootingType;
import edu.hitsz.ShootingType.StraightShootingType;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteEnemyFactory implements EnemyAircraftFactory {
    @Override
    public AbstractAircraft createEnemyAircraft() {
        AbstractAircraft elite = new EliteEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                (int)(10*(Math.random()-0.5)),
                5,
                30
        );
        AbstractShootingType shootingType= new StraightShootingType();
        elite.setShootingType(shootingType);
        return elite;
    }
}
