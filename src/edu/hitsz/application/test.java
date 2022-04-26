package edu.hitsz.application;

import edu.hitsz.Data.Dao;
import edu.hitsz.Data.Player;
import edu.hitsz.Data.ScoreDao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        Dao dao = new ScoreDao();
        dao.printAll();
        ArrayList<Player> scoreData = dao.getAllData();
        String[][] data = new String[scoreData.size()][4];
        for(int i=0;i<scoreData.size();i++)
        {
            data[i][0]= ""+(i+1);
            Player p = scoreData.get(i);
            data[i][1]=p.getScore();
            data[i][2]=p.getName();
            data[i][3]= p.getLocalDateTime();
        }
        System.out.println(Arrays.deepToString(data));
    }
}
