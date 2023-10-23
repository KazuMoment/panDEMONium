package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.GamePanel;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp){
        this.gp = gp;
    }

    public void save(){
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            ds.level = gp.player.level;
            ds.maxHP = gp.player.maxHP;
            ds.HP = gp.player.HP;
            ds.maxMP = gp.player.maxMP;
            ds.MP = gp.player.MP;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.EXP = gp.player.EXP; 
            ds.nextLevelEXP = gp.player.nextLevelEXP;
            ds.gold = gp.player.gold;

            // Write DataStorage object
            oos.writeObject(ds);

        } catch (Exception e) {
            System.out.println("Save Exception!");
        }
    }

    public void load(){

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // Read DataStorage object
            DataStorage ds = (DataStorage)ois.readObject();

            gp.player.level = ds.level;
            gp.player.maxHP = ds.maxHP;
            gp.player.HP = ds.HP;
            gp.player.maxMP = ds.MP;
            gp.player.MP = ds.MP;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.EXP = ds.EXP;
            gp.player.nextLevelEXP = ds.nextLevelEXP;
            gp.player.gold = ds.gold;
        } catch (Exception e) {
            System.out.println("Load Exception!");
        }

    }
    
}
