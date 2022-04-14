package edu.hitsz.ShootingType;
import edu.hitsz.bullet.AbstractBullet;

import java.util.List;

public interface AbstractShootingType {
    List<AbstractBullet> doShoot(int AircraftType,int power,int shootnum,int LocationX,int LocationY);

}
