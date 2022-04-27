package edu.hitsz.application;

import edu.hitsz.Data.Dao;
import edu.hitsz.Data.Player;
import edu.hitsz.Data.ScoreDao;

import javax.imageio.plugins.tiff.TIFFDirectory;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public ScoreTable() {
        if(Main.Difficulty==1){
            difficultyLabel.setText("难度：简单");
        }
        else if(Main.Difficulty==2){
            difficultyLabel.setText("难度：中等");
        }
        else{
            difficultyLabel.setText("难度：困难");
        }

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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = scoreTable.getSelectedRow();
//                System.out.println(row);
                Dao dao = new ScoreDao();
                dao.deleteOne(row+1);
                if(row != -1){
                    model.removeRow(row);
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
