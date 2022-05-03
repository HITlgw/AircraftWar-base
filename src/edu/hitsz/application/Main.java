package edu.hitsz.application;

import edu.hitsz.misic.BGMThread;

import javax.swing.*;
import java.awt.*;

import static java.lang.System.exit;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static final Object Lock = new Object();
//    public static final Object BGM_Lock = new Object();
    public static boolean BGM = true;
    public static int Difficulty=0;//1-简单 2-一般 3-困难




    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        var difficultySelection=new Difficulty();
        difficultySelection.addToFrame(frame);
        frame.setVisible(true);

        synchronized (Lock){
            try {
                Lock.wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Game game = null;
        if(Difficulty==1){
            game=new EasyGame();
        }else if(Difficulty==2){
            game=new OrdinaryGame();
        }else if(Difficulty==3){
            game=new DifficultGame();
        }else{
            System.out.println("Warning!No Selection!");
            exit(1);
        }

        Thread BGMThread=new BGMThread("src/video/bgm.wav",game);
        BGMThread.start();
        frame.setVisible(false);
        difficultySelection.removeFromFrame(frame);

        frame.setContentPane(game);
        frame.setVisible(true);
        game.action();

        //记录窗口
        synchronized (Lock){
            try {
                Lock.wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        frame.remove(game);
        frame.setVisible(false);
//        var nameInput = new InputName(game);
//        nameInput.addToFrame(frame);
//        frame.setVisible(true);
//        synchronized (Lock){
//            try {
//                Lock.wait();
//            }
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        nameInput.removeFromFrame(frame);
//        frame.setVisible(false);
        var scoreTable = new ScoreTable(game,frame);
        scoreTable.addToFrame(frame);
        frame.setVisible(true);
        scoreTable.addInputNamePanel(frame);


        //排行榜窗口

    }
}
