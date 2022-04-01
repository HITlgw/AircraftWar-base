package edu.hitsz.aircraft;

import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.EnemyBullet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

//V3:创建单元检测类
class HeroAircraftTest {
    static HeroAircraft hero;
    @BeforeAll
    static void setUp(){
        hero=HeroAircraft.getinstance();
    }

    @DisplayName("decreaseHP test")
    @ParameterizedTest
    @CsvSource({"1,80,20","2,50,30","3,0,60"})
    //由于hero是单例模式，对象只有一个，刚开始100，第1次-20，HP变为80；第2次-30，HP变为50；第三次减60，HP变为0
    void decreaseHp(int time,int expected,int decrease) {
        hero.decreaseHp(decrease);
        assertEquals(expected,hero.getHp());
        System.out.println("第 "+time+" 次 pass!");

    }


    @ParameterizedTest
    @DisplayName("crash test")
    @CsvSource({"true,256,685","true,202,700","true,310,700","true,300,657","true,300,713",
            "false,200,700","false,312,700","false,300,655","false,300,715"})
        //判断hero和敌机子弹碰撞，预期201<=x<=311，656<=y<=714时碰撞
    void crash(boolean expected,int locationX,int locationY) {
        AbstractFlyingObject bullet = new EnemyBullet(locationX,locationY,0,0,0);
        assertEquals(expected,hero.crash(bullet));
        System.out.println("在("+locationX+","+locationY+")处测试pass!");
    }
}