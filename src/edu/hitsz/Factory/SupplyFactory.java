package edu.hitsz.Factory;

import edu.hitsz.supply.AbstractSupply;

public interface SupplyFactory {
    public AbstractSupply createSupply(int LocationX,int LocationY,int speedX,int speedY);
}
