package edu.hitsz.Factory;

import edu.hitsz.supply.AbstractSupply;
import edu.hitsz.supply.BulletSupply;

public class BulletSupplyFactory implements SupplyFactory{
    @Override
    public AbstractSupply createSupply(int LocationX, int LocationY, int speedX, int speedY) {
        return new BulletSupply(LocationX, LocationY,speedX,speedY);
    }
}
