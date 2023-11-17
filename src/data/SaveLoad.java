package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

            // Player Stats
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

            // Player Inventory
            for(int i = 0; i < gp.player.inventory.size(); i++){
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmount.add(gp.player.inventory.get(i).amount);
            }

            // Player Equpment
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();

            // Objects on Map
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootName = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                for (int i = 0; i < gp.obj[1].length; i++){

                    if (gp.obj[mapNum][i] == null){
                        ds.mapObjectNames[mapNum][i] = "NA";
                    }
                    else{
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        if (gp.obj[mapNum][i].loot != null){
                            ds.mapObjectLootName[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                    }

                }
            }

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

            // Player Inventory
            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemNames.size(); i++){
                gp.player.inventory.add(gp.eGenerator.getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmount.get(i);
            }

            // Player Equipment
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttackImage();

            // Objects on Map

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                for (int i = 0; i < gp.obj[1].length; i++){

                    if (ds.mapObjectNames[mapNum][i].equals("NA")){
                        gp.obj[mapNum][i] = null;
                    }
                    else{
                        gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if (ds.mapObjectLootName[mapNum][i] != null){
                            gp.obj[mapNum][i].loot =  gp.eGenerator.getObject(ds.mapObjectLootName[mapNum][i]);
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if (gp.obj[mapNum][i].opened == true){
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }
                    }

                }
            }

        } catch (Exception e) {
            System.out.println("Load Exception!");
        }

    }
    
}
