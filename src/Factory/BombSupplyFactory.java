package Factory;

import edu.hitsz.supply.AbstractSupply;
import edu.hitsz.supply.BombSupply;

public class BombSupplyFactory implements SupplyFactory{
    @Override
    public AbstractSupply createSupply(int LocationX, int LocationY, int speedX, int speedY) {
        return new BombSupply(LocationX,LocationY,0,10);
    }
}
