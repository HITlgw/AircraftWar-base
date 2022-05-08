package edu.hitsz.aircraftFactory;

import edu.hitsz.aircraft.AbstractAircraft;

public abstract class EnemyAircraftFactory {
    protected int defaultHp;
    protected int defaultSpeedX;
    protected int defaultSpeedY;

    public int getDefaultHp() {
        return defaultHp;
    }

    public int getDefaultSpeedX() {
        return defaultSpeedX;
    }

    public int getDefaultSpeedY() {
        return defaultSpeedY;
    }

    public int getDefaultPower() {
        return defaultPower;
    }

    protected int defaultPower;
    protected abstract AbstractAircraft createEnemyAircraft();
    public void increaseDifficulty(int increaseHp,int increaseSpeedX,int increaseSpeedY,int increasePower){
        this.defaultHp+=increaseHp;
        this.defaultSpeedX+=increaseSpeedX;
        this.defaultSpeedY+=increaseSpeedY;
        this.defaultPower+=increasePower;
    }
}
