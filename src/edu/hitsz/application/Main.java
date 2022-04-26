package edu.hitsz.application;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static final Object Lock = new Object();
    public static boolean BGM=false;
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
        var difficultySelectPanel=new Difficulty().MainPanel;

        frame.setContentPane(difficultySelectPanel);
        frame.setVisible(true);
        synchronized (Lock){
            try {
                Lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        frame.setVisible(false);
        frame.remove(difficultySelectPanel);
        Game game = new Game();
        frame.setContentPane(game);
        frame.setVisible(true);
        game.action();

        //记录窗口
        synchronized (Lock){
            try {
                Lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        frame.remove(game);
        frame.setVisible(false);
        var nameInputPanel = new InputName().MainPanel;
        frame.setContentPane(nameInputPanel);
        frame.setVisible(true);

        //排行榜窗口
        synchronized (Lock){
            try {
                Lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        frame.remove(nameInputPanel);
        frame.setVisible(false);
        var scorePanel = new ScoreTable().MainPanel;
        frame.setContentPane(scorePanel);
        frame.setVisible(true);


    }
}
