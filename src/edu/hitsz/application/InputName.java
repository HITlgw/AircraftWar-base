package edu.hitsz.application;

import edu.hitsz.Data.Dao;
import edu.hitsz.Data.ScoreDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class InputName {
    private JPanel NamePanel;
    private JPanel ButtonPanel;
    private JPanel MainPanel;
    private JTextField NametextField;
    private JLabel remindLabel;
    private JButton YesButton;
    private JButton NoButton;
    private JLabel YesorNoLabel;
    private Game game;

    public InputName(Game game) {
        this.game=game;
        YesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = NametextField.getText();
                Dao dao = new ScoreDao();
                try {
                    dao.writeOne(name, game.getScore(), LocalDateTime.now());
                    dao.printAll();
                } catch (Exception err) {
                    err.printStackTrace();
                }
                synchronized (Main.Lock) {
//                    System.out.println("YES");
                    Main.Lock.notify();
                }
            }
        });
        NoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Main.Lock) {
//                    System.out.println("YES");
                    Main.Lock.notify();
                }
            }
        });
    }
    public void addToFrame(JFrame frame){
        frame.setContentPane(this.MainPanel);
    }
    public void removeFromFrame(JFrame frame){
        frame.remove(this.MainPanel);
    }
}
