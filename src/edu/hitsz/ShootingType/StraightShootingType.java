package edu.hitsz.ShootingType;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class StraightShootingType implements AbstractShootingType{
    public StraightShootingType() {
    }

    @Override
    //AircraftType = -1 表示英雄机，AircraftType = 1 表示敌机
    public List<AbstractBullet> doShoot(int AircraftType,int power,int shootNum,int LocationX,int LocationY) {
        List<AbstractBullet> list = new LinkedList<>();
        int[] bullets_locationY = new int[shootNum];
        //利用数组记录每一个子弹的x速度和x坐标
        for(int i=0;i<shootNum;i++)
        {
            bullets_locationY[i]=LocationY-(shootNum-1)*10+i*20;
        }

        if(AircraftType==-1){
            for(int i=0;i<shootNum;i++)
            {
                list.add(new HeroBullet(LocationX,bullets_locationY[i],0,5*AircraftType,power));
            }

        }
        else if(AircraftType==1) {
            for(int i=0;i<shootNum;i++)
            {
                list.add(new EnemyBullet(LocationX, bullets_locationY[i], 0 , 10 + 5 * AircraftType,power));
            }
        }
        return list;
    }
}
