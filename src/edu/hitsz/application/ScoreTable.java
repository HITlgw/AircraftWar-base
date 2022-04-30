package edu.hitsz.application;

import edu.hitsz.Data.Dao;
import edu.hitsz.Data.Player;
import edu.hitsz.Data.ScoreDao;

import javax.imageio.plugins.tiff.TIFFDirectory;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ScoreTable {
    private JButton deleteButton;
    private JTable scoreTable;
    private JScrollPane tableScrollPane;
    private JLabel tableTitleLabel;
    private JPanel MainPanel;
    private JPanel topPanel;
    private JPanel ButtonPanel;
    private JLabel difficultyLabel;
    private Game game;

    public ScoreTable(Game game,JFrame frame) {
        this.game=game;
        if(Main.Difficulty==1){
            difficultyLabel.setText("难度：简单");
        }
        else if(Main.Difficulty==2){
            difficultyLabel.setText("难度：中等");
        }
        else{
            difficultyLabel.setText("难度：困难");
        }

        update();
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = scoreTable.getSelectedRow();
                int result = JOptionPane.showConfirmDialog(
                        frame,
                        "确认要删除吗？",
                        "提示",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );
                if(result==JOptionPane.YES_OPTION){
                    Dao dao = new ScoreDao();
                    dao.deleteOne(row+1);
                    update();
                }
            }
        });
    }

    public void addToFrame(JFrame frame){
        frame.setContentPane(this.MainPanel);
    }
    public void addInputNamePanel(JFrame frame){
        String inputName = JOptionPane.showInputDialog(
                frame,
                "当前得分为："+game.getScore()+"\n输入你的名字:",
                "Player"
        );
        if(inputName!=null){
            ScoreDao dao = new ScoreDao();
            dao.writeOne(inputName, game.getScore(), LocalDateTime.now());
            update();
        }
    }

    private void update(){
        String[] columnName = {"排名","分数","姓名","时间"};
        ScoreDao dao = new ScoreDao();
        ArrayList<Player> scoreData ;
        scoreData = new ScoreDao().getAllData();
        String[][] data = new String[scoreData.size()][4];
        for(int i=0;i<scoreData.size();i++)
        {
            data[i][0]=""+(i+1);
            Player p = scoreData.get(i);
            data[i][1]=p.getScore();
            data[i][2]=p.getName();
            data[i][3]= p.getLocalDateTime();
        }
        DefaultTableModel model = new DefaultTableModel(data, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        scoreTable.setModel(model);
        tableScrollPane.setViewportView(scoreTable);
    }


}
