package edu.hitsz.supply;

import edu.hitsz.aircraftFactory.HPSupplyFactory;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class HPSupplyTest {
    static HeroAircraft hero;
    @BeforeAll
    static void setUp() {
        hero = HeroAircraft.getinstance();
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @DisplayName("get supply test")
    @CsvSource({"70,80","50,80"})
    void getSupply(int expected,int decrease)
    {
        hero.decreaseHp(decrease);
        AbstractSupply supply = new HPSupplyFactory().createSupply(hero.getLocationX(), hero.getLocationY(), 0, hero.getSpeedY()+10);
        supply.getSupply(hero);
        assertEquals(expected,hero.getHp());
        System.out.println("test pass!");
    }

    @ParameterizedTest
    @DisplayName("forward test")
    @CsvSource({"200,1","400,3","2100,20"})
    void forward(int expectedY,int time)
    {
        AbstractSupply supply = new HPSupplyFactory().createSupply(100, 100, 0, 100);
        for(int i=0;i<time;i++)
        {
            supply.forward();
        }
        assertEquals(expectedY,supply.getLocationY());
        System.out.println("移动"+time+"次pass!");
        if (expectedY> Main.WINDOW_HEIGHT){
            assertTrue(supply.notValid());
            System.out.println("移动"+time+"次后出边界，道具消失pass!");
        }
    }
}