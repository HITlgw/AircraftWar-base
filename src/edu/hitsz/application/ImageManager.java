package edu.hitsz.application;


import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.supply.BombSupply;
import edu.hitsz.supply.BulletSupply;
import edu.hitsz.supply.HPSupply;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();
    // V1: 增加精英敌机，Boss敌机，加血补给，子弹补给，炸弹补给图片变量
    public static BufferedImage BACKGROUND_IMAGE;
    public static BufferedImage HERO_IMAGE;
    public static BufferedImage HERO_BULLET_IMAGE;
    public static BufferedImage ENEMY_BULLET_IMAGE;
    public static BufferedImage MOB_ENEMY_IMAGE;
    public static BufferedImage ELITE_ENEMY_IMAGE;
    public static BufferedImage BOSS_ENEMY_IMAGE;
    public static BufferedImage HP_SUPPLY_IMAGE;
    public static BufferedImage BULLET_SUPPLY_IMAGE;
    public static BufferedImage BOMB_SUPPLY_IMAGE;

    static {
        try {

            BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg.jpg"));


            // V1: 增加精英敌机，Boss敌机，加血补给，子弹补给，炸弹补给// V1: 导入精英敌机，Boss敌机，加血补给，子弹补给，炸弹补给图片
            HERO_IMAGE = ImageIO.read(new FileInputStream("src/images/hero_r.png"));
            MOB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/mob_r.png"));
            ELITE_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elite_r.png"));
            BOSS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/boss_r.png"));
            HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_hero_r.png"));
            ENEMY_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_enemy_r.png"));
            HP_SUPPLY_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_blood_r.png"));
            BULLET_SUPPLY_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bullet_r.png"));
            BOMB_SUPPLY_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bomb_r.png"));
            // V1: 在MAP中增加精英敌机，Boss敌机，加血补给，子弹补给，炸弹补给的类和图片关联
            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ELITE_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(), BOSS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HPSupply.class.getName(), HP_SUPPLY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BulletSupply.class.getName(), BULLET_SUPPLY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BombSupply.class.getName(), BOMB_SUPPLY_IMAGE);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static BufferedImage get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static BufferedImage get(Object obj){
        if (obj == null){
            return null;
        }
        return get(obj.getClass().getName());
    }

}
