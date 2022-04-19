package edu.hitsz.Data;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ScoreDao implements Dao {

    @Override
    public ArrayList<Player> getAllData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Players.dat"));
            ArrayList<Player> L;
            L = (ArrayList<Player>) ois.readObject();
            ois.close();
            return L;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void  writeAllData(ArrayList<Player> L)
    {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Players.dat"));
            oos.writeObject(L);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeOne(String Name, int Score, LocalDateTime localDateTime)  {
        Player p = new Player(Score,Name,localDateTime);
        ArrayList<Player> L = new ArrayList<>();
        ArrayList<Player> addL=getAllData();
        if(addL!=null) { L.addAll(addL);}
        L.add(p);
        Collections.sort(L);
        writeAllData(L);
    }

    @Override
    public void printAll() {
        System.out.println("********************************");
        System.out.println("            得分排行榜            ");
        System.out.println("********************************");

        ArrayList<Player> L = getAllData();
        int i=1;
        //System.out.println("排名\t\t名字\t\t得分\t\t时间");
        for (Player p :L) {
            System.out.print("第" + i + "名\t");
            p.printSelf();
            System.out.print("\n");
            i++;
        }
    }

    @Override
    public void deleteAll() {
        ArrayList<Player> L = new ArrayList<>();
        writeAllData(L);
    }

    @Override
    //删除第rank名的元素
    public void deleteOne(int rank) {
        ArrayList<Player> L = getAllData();
        if(rank>L.size())
        {
            System.out.println("无效排名");
            return;
        }
        L.remove(rank-1);
        writeAllData(L);
    }

//    public static void main(String[] args) throws IOException {
//        Dao dao = new ScoreDao();
//        dao.deleteAll();
//    }
}
