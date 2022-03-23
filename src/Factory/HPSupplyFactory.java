package Factory;

import edu.hitsz.supply.AbstractSupply;
import edu.hitsz.supply.HPSupply;

public class HPSupplyFactory implements SupplyFactory{
    @Override
    public AbstractSupply createSupply(int LocationX, int LocationY, int speedX,int speedY) {
        return new HPSupply(LocationX, LocationY,speedX,speedY);
    }
}
