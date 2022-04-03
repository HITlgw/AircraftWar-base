package edu.hitsz.aircraft;

import edu.hitsz.Factory.EliteEnemyFactory;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.supply.AbstractSupply;
import edu.hitsz.supply.HPSupply;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assumptions.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {
    static HeroAircraft hero;
    @BeforeAll
    static void setUp() {
        hero=HeroAircraft.getinstance();
    }

    @AfterEach
    void tearDown() {
        hero.addHp(100);                                                    //hero恢复最大血量
    }

    @ParameterizedTest
    @DisplayName("elite shoot test")
    @ValueSource(ints = {1,3,5})
    void shoot(int time) {
        EliteEnemy elite = (EliteEnemy) new EliteEnemyFactory().createEnemyAircraft();
        List<AbstractBullet> bulletList = new ArrayList<>();
        for(int i=0;i<time;i++)                                             //射击time次
        {
            bulletList.addAll(elite.shoot());
        }
        assertEquals(time,bulletList.size());                               //每次只产生1个子弹
        EnemyBullet bullet = (EnemyBullet) bulletList.get(0);
        assertEquals(bullet.getLocationX(),elite.getLocationX());           //检查子弹X坐标
        assertEquals(bullet.getLocationY(),elite.getLocationY()+2);   //检查子弹Y坐标
        assertEquals(15,bullet.getSpeedY());         //检查子弹速度
        assertEquals(30,bullet.getPower());                         //检查子弹攻击力
        System.out.println("shoot "+time+" time test pass!");
    }

    @ParameterizedTest
    @DisplayName("elite generate supply test")
    @ValueSource(ints={1,2,3})
    void generateSupply(int time) {
        EliteEnemy elite = (EliteEnemy) new EliteEnemyFactory().createEnemyAircraft();
        List<AbstractSupply> supplyList = elite.generateSupply();
        assertNotNull(supplyList);
        AbstractSupply supply = supplyList.get(0);
        if(!(supply instanceof HPSupply))
        {
            System.out.println("第"+time+"次不是HP道具,pass!");
        }
        assumingThat(supply instanceof HPSupply,()->{                       //若不是HPSupply则不执行

            hero.decreaseHp(70);
            supply.getSupply(hero);
            assertEquals(80,hero.getHp());
            System.out.println("第"+time+"次对HP道具进行检测,pass!");
        });                                                                 //测试已实现的HPSupply是否可以实现加血50
    }

}