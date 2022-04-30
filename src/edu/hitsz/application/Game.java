package edu.hitsz.application;

import edu.hitsz.aircraftFactory.BossEnemyFactory;
import edu.hitsz.aircraftFactory.EliteEnemyFactory;
import edu.hitsz.aircraftFactory.MobEnemyFactory;
import edu.hitsz.aircraft.*;
import edu.hitsz.basic.Observer;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.misic.BossBGMThread;
import edu.hitsz.misic.otherMusicThread;
import edu.hitsz.supply.AbstractSupply;
import edu.hitsz.supply.BombSupply;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;
    //调试时改变的刷新频率
    //private int timeInterval = 600;
    private final HeroAircraft heroAircraft;
    private final List<AbstractAircraft> enemyAircrafts;
    private final List<AbstractBullet> heroBullets;
    private final List<AbstractBullet> enemyBullets;
    private final List<AbstractSupply> abstractSupplies;

    private int enemyMaxNumber = 10;
    private boolean gameOverFlag = false;
    private int score = 0;

    public boolean isGameOver() {
        return gameOverFlag;
    }

    public int getScore() {
        return score;
    }

    private int time = 0;
    private int bossScoreThreshold = 200;
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;
    //V4:增加变量记录是否存在boss敌机
    private boolean existBoss = false;
    private ArrayList<BombSupply> bombPublishers;

    public boolean isExistBoss() {
        return existBoss;
    }

    public Game() {
//        heroAircraft = new HeroAircraft(
//                Main.WINDOW_WIDTH / 2,
//                Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
//                0, 0, 100);
    //V2:单例模式修改
        heroAircraft=HeroAircraft.getinstance();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        // V1:新增supply列表
        abstractSupplies = new LinkedList<>() ;
        bombPublishers = new ArrayList<>();


        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生
                //V2:使用工厂重构，隐藏创建敌机细节
                if (enemyAircrafts.size() < enemyMaxNumber) {
                    if(Math.random()>0.2){
                        AbstractAircraft enemy = new MobEnemyFactory().createEnemyAircraft();
                        enemyAircrafts.add(enemy);
                        for(BombSupply b : bombPublishers){
                            b.addObserver((Observer) enemy);
                        }
                    }
                    else{
                        AbstractAircraft enemy = new EliteEnemyFactory().createEnemyAircraft();
                        enemyAircrafts.add(enemy);
                        for(BombSupply b : bombPublishers){
                            b.addObserver((Observer) enemy);
                        }
                    }
                }
                //V4:增加boss敌机
                if(this.score % bossScoreThreshold>=bossScoreThreshold-20 && existBoss==false)
                {
                    enemyAircrafts.add(new BossEnemyFactory().createEnemyAircraft());
                    existBoss=true;
                    Thread bossbgmthread = new Thread(()->{
                        while(existBoss){
                            Thread musicthread = new BossBGMThread("src/vedio/bgm_boss.wav",this);
                            musicthread.run();
                        }

                    });
                    bossbgmthread.start();
                }
                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            //  V1:补给道具移动
            suppliesMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
                Thread gameoverthread = new otherMusicThread("src/vedio/game_over.wav");
                gameoverthread.start();
                try {
                    gameoverthread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Main.Lock){
                    Main.Lock.notify();
                }
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // TODO 敌机射击
        // V1:敌机射击，只有精英敌机射击（是否改成全部敌机射击？Mob射击有重写）
        for(AbstractAircraft enemyAircraft : enemyAircrafts){
            List<AbstractBullet> newlist = enemyAircraft.shoot();
            if(newlist!=null)
            {
                enemyBullets.addAll(newlist);
                for (BombSupply b:bombPublishers){
                    for (AbstractBullet bullet : newlist)
                    {
                        b.addObserver((Observer) bullet);
                    }
                }
            }
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (AbstractBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (AbstractBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }
    //  V1:补给移动
    private void suppliesMoveAction() {
        for (AbstractSupply supply : abstractSupplies) {
            supply.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        // V1:敌机子弹和英雄碰撞检测
        for(AbstractBullet bullet : enemyBullets)
        {
            if(bullet.notValid()){
                continue;
            }
            if(heroAircraft.crash(bullet))
            {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
                for (BombSupply b:bombPublishers){
                    b.deleteObserver((Observer)bullet);
                }
            }
        }
        // 英雄子弹攻击敌机
        for (AbstractBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        //V2:使用敌机自身的generateSupply方法创建道具
                        List<AbstractSupply> newsupplyList=enemyAircraft.generateSupply();
                        if(newsupplyList!=null){
                            for(AbstractSupply s : newsupplyList){
                                abstractSupplies.add(s);
                                if(s instanceof BombSupply){
                                    for (AbstractBullet enemyBullet:enemyBullets){
                                        if(!enemyBullet.notValid()){
                                            ((BombSupply) s).addObserver((Observer)enemyBullet);
                                        }
                                    }
                                    for (AbstractAircraft aircraft:enemyAircrafts){
                                        if(!aircraft.notValid() && !(aircraft instanceof BossEnemy)){
                                            ((BombSupply) s).addObserver((Observer)aircraft);
                                        }
                                    }
                                    bombPublishers.add((BombSupply) s);
                                }
                            }
                        }

                        score += 10;
                        //V3:boss机消失时需要重置existBoss
                        if(enemyAircraft instanceof BossEnemy) {
                            existBoss=false;
                        }else{
                            for (BombSupply b:bombPublishers){
                                b.deleteObserver((Observer) enemyAircraft);
                            }
                        }
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }
        // TODO 我方获得道具，道具生效
        // V1:道具和英雄机碰撞检测
        for(AbstractSupply supply : abstractSupplies)
        {
            if(supply.notValid())
            {
                continue;
            }
            if(supply.crash(heroAircraft))
            {
                Thread getsupplythread = new otherMusicThread("src/vedio/get_supply.wav");
                getsupplythread.start();
                supply.getSupply(heroAircraft);
                supply.vanish();
            }
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * 4. 删除失效补给（V1）
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        // V1:删除失效的补给
        abstractSupplies.removeIf(AbstractFlyingObject::notValid);

    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        // V1:绘制补给
        paintImageWithPositionRevised(g, abstractSupplies);
        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }







}
