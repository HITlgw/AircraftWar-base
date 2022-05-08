package edu.hitsz.application;

import edu.hitsz.aircraftFactory.BossEnemyFactory;
import edu.hitsz.aircraftFactory.EliteEnemyFactory;
import edu.hitsz.aircraftFactory.MobEnemyFactory;

public class EasyGame extends Game{
    @Override
    public void setFactroy() {
        super.mobEnemyFactory=new MobEnemyFactory(10,30);
        super.eliteEnemyFactory=new EliteEnemyFactory(10,8,30,30);
        super.bossEnemyFactory=new BossEnemyFactory(10,300,50);
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
