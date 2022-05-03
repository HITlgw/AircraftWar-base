package edu.hitsz.application;

import edu.hitsz.aircraftFactory.BossEnemyFactory;
import edu.hitsz.aircraftFactory.EliteEnemyFactory;
import edu.hitsz.aircraftFactory.MobEnemyFactory;

public class OrdinaryGame extends Game{
    @Override
    public void setFactroy() {
        super.setMobEnemyFactory(new MobEnemyFactory(15,50));
        super.setEliteEnemyFactory(new EliteEnemyFactory(12,8,40,45));
//        super.setMobEnemyFactory(new MobEnemyFactory(2,600));
//        super.setEliteEnemyFactory(new EliteEnemyFactory(15,1,50,600));
        super.setBossEnemyFactory(new BossEnemyFactory(12,400,70));
    }

    @Override
    protected boolean isGenerateBoss() {
        return true;
    }

    @Override
    protected boolean isIncreaseBossDifficuly() {
        return false;
    }

    @Override
    protected void increaseDifficulty() {
        super.mobEnemyFactory.increaseDifficulty(5,0,1,0);
        super.eliteEnemyFactory.increaseDifficulty(5,0,1,5);
    }

    @Override
    protected void setMaxEnemy() {
        super.enemyMaxNumber=10;
    }

    @Override
    protected void setEliteRate() {
        super.eliteRate=0.3;
    }

    @Override
    protected void setBossScore() {
        super.bossScore=300;
    }

    @Override
    protected void setEnemyEachTime() {
        super.enemyEachTime=2;
    }
}
