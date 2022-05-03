package edu.hitsz.application;

import edu.hitsz.aircraftFactory.BossEnemyFactory;
import edu.hitsz.aircraftFactory.EliteEnemyFactory;
import edu.hitsz.aircraftFactory.MobEnemyFactory;

public class DifficultGame extends Game{
    @Override
    public void setFactroy() {
        super.setMobEnemyFactory(new MobEnemyFactory(20,60));
        super.setEliteEnemyFactory(new EliteEnemyFactory(15,10,50,60));
//        super.setMobEnemyFactory(new MobEnemyFactory(2,600));
//        super.setEliteEnemyFactory(new EliteEnemyFactory(15,1,50,600));
        super.setBossEnemyFactory(new BossEnemyFactory(15,500,90));
    }

    @Override
    protected boolean isGenerateBoss() {
        return true;
    }

    @Override
    protected boolean isIncreaseBossDifficuly() {
        return true;
    }

    @Override
    protected void increaseDifficulty() {
        super.mobEnemyFactory.increaseDifficulty(10,0,2,0);
        super.eliteEnemyFactory.increaseDifficulty(10,1,2,10);
    }

    @Override
    protected void setMaxEnemy() {
        super.enemyMaxNumber=15;
    }

    @Override
    protected void setEliteRate() {
        super.eliteRate=0.5;
    }

    @Override
    protected void setBossScore() {
        super.bossScore =200;
    }

    @Override
    protected void setEnemyEachTime() {
        super.enemyEachTime=3;
    }

}
