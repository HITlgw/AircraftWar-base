package edu.hitsz.application;

import edu.hitsz.aircraftFactory.BossEnemyFactory;
import edu.hitsz.aircraftFactory.EliteEnemyFactory;
import edu.hitsz.aircraftFactory.MobEnemyFactory;

public class EasyGame extends Game{
    @Override
    protected void setFactroy() {
        super.setMobEnemyFactory(new MobEnemyFactory(10,30));
        super.setEliteEnemyFactory(new EliteEnemyFactory(10,5,30,30));
        super.setBossEnemyFactory(new BossEnemyFactory(10,300,50));
    }

    @Override
    protected boolean isGenerateBoss() {
        return false;
    }

    @Override
    protected boolean isIncreaseBossDifficuly() {
        return false;
    }

    @Override
    protected void increaseDifficulty() {
        return;
    }

    @Override
    protected void setMaxEnemy() {
        super.enemyMaxNumber=5;
    }

    @Override
    protected void setEliteRate() {
        super.eliteRate=0.2;
    }

    @Override
    protected void setBossScore() {
        return;
    }

    @Override
    protected void setEnemyEachTime() {
        super.enemyEachTime=1;
    }
}
