package edu.hitsz.application;

import edu.hitsz.misic.BGMThread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

public class Difficulty {
    public JPanel MainPanel;
    private JButton Button1;
    private JButton Button3;
    private JButton Button2;
    private JRadioButton radioButton1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Difficulty");
        frame.setContentPane(new Difficulty().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Difficulty() {
        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.Difficulty=1;
                    ImageManager.BACKGROUND_IMAGE= ImageIO.read(new FileInputStream("src/images/bg.jpg"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                synchronized (Main.Lock){
                    Main.Lock.notify();
                }
            }
        });
        Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.Difficulty=2;
                    ImageManager.BACKGROUND_IMAGE= ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                synchronized (Main.Lock){
                    Main.Lock.notify();
                }
            }
        });
        Button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Main.Lock){try {
                    Main.Difficulty=3;
                    ImageManager.BACKGROUND_IMAGE= ImageIO.read(new FileInputStream("src/images/bg3.jpg"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                    Main.Lock.notify();
                }
            }
        });
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.BGM = !Main.BGM;
                Thread bgmthread = new BGMThread("src/vedio/bgm.wav");
                bgmthread.start();

                }

//        }
        });
    }
}
