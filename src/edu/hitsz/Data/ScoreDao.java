package edu.hitsz.Data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
    public void writeOne(String Name, int Score)  {
        Player p = new Player(Score,Name);
        ArrayList<Player> L = new ArrayList<>();
        ArrayList<Player> addL=getAllData();
        if(addL!=null) { L.addAll(addL);}
        L.add(p);
        Collections.sort(L);
        writeAllData(L);
    }

    @Override
    public void printAll() {

        ArrayList<Player> L = getAllData();
        int i=1;
        System.out.println("排名\t\t名字\t\t得分");
        for (Player p :L) {
            System.out.println("第" + i + "名\t" + p);
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


    public static void main(String[] args) throws IOException {

//        Files.createFile(Paths.get("src/Players.dat"));
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Players.dat"));
//        oos.writeObject(new ArrayList<Player>());
//        oos.close();
//        ArrayList<Player> L = new ArrayList<>();
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Players.dat"));
//        oos.writeObject(L);
//        oos.close();

        Dao dao = new ScoreDao();
        dao.printAll();
        dao.deleteOne(18);
        System.out.println("*****");
        dao.printAll();
//        dao.writeScore("aaa",1);
//        dao.printAll();
//        System.out.println("***");
//        dao.writeScore("bbb",3);
//        dao.printAll();
//        System.out.println("***");
//        dao.writeScore("ccc",2);
//        dao.printAll();

    }
}
