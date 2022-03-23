package Factory;

import edu.hitsz.aircraft.AbstractAircraft;

public class BossAircraftFactory implements EnemyAircraftFactory {
    @Override
    public AbstractAircraft createEnemyAircraft() {
        return null;
    }
}
