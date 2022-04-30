package edu.hitsz.application;

import edu.hitsz.Data.Dao;
import edu.hitsz.Data.ScoreDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
/****************
 *     已弃用    *
 ***************/
public class InputName {
    private JPanel NamePanel;
    private JPanel ButtonPanel;
    private JPanel MainPanel;
    private JTextField NametextField;
    private JLabel remindLabel;
    private JButton YesButton;
    private JButton NoButton;
    private JLabel YesorNoLabel;
    private JLabel scoreLabel;
    private Game game;

    public InputName(Game game) {
        this.game=game;
        scoreLabel.setText("本次得分为"+game.getScore());
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
    public void addToPanel(JPanel panel){
        panel.add(this.MainPanel);
    }
    public void removeFromPanel(JPanel panel){
        panel.remove(this.MainPanel);
    }
}
