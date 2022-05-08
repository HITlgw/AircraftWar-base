package edu.hitsz.application;

import edu.hitsz.aircraftFactory.BossEnemyFactory;
import edu.hitsz.aircraftFactory.EliteEnemyFactory;
import edu.hitsz.aircraftFactory.MobEnemyFactory;

public class DifficultGame extends Game{
    @Override
    public void setFactroy() {
        super.mobEnemyFactory=new MobEnemyFactory(15,60);
        super.eliteEnemyFactory=new EliteEnemyFactory(15,12,50,60);
        super.bossEnemyFactory=new BossEnemyFactory(15,500,90);
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
        super.eliteRate+=0.05;
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
