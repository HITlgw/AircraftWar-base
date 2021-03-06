package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

public abstract class AbstractSupply extends AbstractFlyingObject {

    public AbstractSupply(int locationX, int locationY, int speedX, int speedY)
    {
        super(locationX, locationY, speedX, speedY);
    }

    public abstract void getSupply(HeroAircraft hero);

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            vanish();
        }
    }
}
