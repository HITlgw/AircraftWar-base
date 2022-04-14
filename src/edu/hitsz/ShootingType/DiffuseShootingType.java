package edu.hitsz.ShootingType;

import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class DiffuseShootingType implements AbstractShootingType{
    public DiffuseShootingType() {
    }

    @Override
    public List<AbstractBullet> doShoot(int AircraftType,int power,int shootNum,int LocationX,int LocationY) {
        List<AbstractBullet> list = new LinkedList<>();
        int[] bullets_locationX = new int[shootNum];
        int[] bullets_speedx = new int[shootNum];
        //利用数组记录每一个子弹的x速度和x坐标
        for(int i=0;i<shootNum;i++) {
            bullets_locationX[i] = LocationX-(shootNum-1)*5+i*10;
            bullets_speedx[i] = i*2-(shootNum-1)*1;
        }
        //根据不同的飞机类型建立不同的子弹
        if(AircraftType==1){
            for(int i=0;i<shootNum;i++) {
                list.add(new EnemyBullet(bullets_locationX[i],LocationY,bullets_speedx[i],5,power));
            }
        }
        else if(AircraftType==-1){
            for(int i=0;i<shootNum;i++) {
                list.add(new HeroBullet(bullets_locationX[i],LocationY,bullets_speedx[i],-10,power));
            }
        }
        return list;

    }
}
