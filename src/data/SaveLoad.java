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
            ds.currentMap = gp.currentMap; 
            ds.currentArea = gp.currentArea; 
            ds.currentLevel = gp.currentLevel; 
            ds.previousLevel = gp.previousLevel; 

            // Progress
            ds.orcDefeated = Progress.orcDefeated;
            ds.demonMonkDefeated = Progress.demonMonkDefeated; 
            ds.necromancerDefeated = Progress.necromancerDefeated;
            ds.demonLordDefeated = Progress.demonLordDefeated; 


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
            ds.mapObjectCollision = new boolean[gp.maxMap][gp.obj[1].length];

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
                        ds.mapObjectCollision[mapNum][i] = gp.obj[mapNum][i].collision;
                    }

                }
            }

            // NPCs on Map
            ds.mapNPCNames = new String[gp.maxMap][gp.npc[1].length];
            ds.mapNPCWorldX = new int[gp.maxMap][gp.npc[1].length];
            ds.mapNPCWorldY = new int[gp.maxMap][gp.npc[1].length];
            ds.mapNPCRewardName = new String[gp.maxMap][gp.npc[1].length];
            ds.mapNPCDoneQuest1 = new boolean[gp.maxMap][gp.npc[1].length];
            ds.mapNPCDoneQuest2 = new boolean[gp.maxMap][gp.npc[1].length];;
            ds.mapNPCReceivedReward = new boolean [gp.maxMap][gp.npc[1].length];
            ds.mapNPCPickedQuestObject = new boolean[gp.maxMap][gp.npc[1].length];
            ds.mapNPCStandby = new boolean[gp.maxMap][gp.npc[1].length];
            ds.mapNPCSleep = new boolean[gp.maxMap][gp.npc[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                for (int i = 0; i < gp.npc[1].length; i++){

                    if (gp.npc[mapNum][i] == null){
                        ds.mapNPCNames[mapNum][i] = "NA";
                    }
                    else{
                        ds.mapNPCNames[mapNum][i] = gp.npc[mapNum][i].name;
                        ds.mapNPCWorldX[mapNum][i] = gp.npc[mapNum][i].worldX;
                        ds.mapNPCWorldY[mapNum][i] = gp.npc[mapNum][i].worldY;
                        if (gp.npc[mapNum][i].reward != null){
                            ds.mapNPCRewardName[mapNum][i] = gp.npc[mapNum][i].reward.name;
                        }
                        ds.mapNPCDoneQuest1[mapNum][i] = gp.npc[mapNum][i].doneQuest1;
                        ds.mapNPCDoneQuest2[mapNum][i] = gp.npc[mapNum][i].doneQuest2;
                        ds.mapNPCReceivedReward[mapNum][i] = gp.npc[mapNum][i].receivedReward;
                        ds.mapNPCPickedQuestObject[mapNum][i] = gp.npc[mapNum][i].pickedQuestObject;
                        ds.mapNPCStandby[mapNum][i] = gp.npc[mapNum][i].standby;
                        ds.mapNPCSleep[mapNum][i] = gp.npc[mapNum][i].sleep;
                    }

                }
            }
        
            // Write DataStorage object
            oos.writeObject(ds);

            //Update and Insert to Database
            String weaponName;
            String shieldName;
            try {
                shieldName = gp.player.currentShield.name;
            } catch (Exception e) {
                shieldName = "None";
            }
            try {
                weaponName = gp.player.currentWeapon.name;
            } catch (Exception e) {
                weaponName = "None";
            }
            //Update and Insert to Database
            int lastSessionID = gp.toSQL.getLastSessionID();
            System.out.println("last sessionID = " + lastSessionID);
            if (gp.toSQL.SessionID == lastSessionID){
                System.out.println("updating");
                gp.toSQL.toDatabase("update playerrecords set level = " + ds.level + ", maxhp = " + ds.maxHP + ", hp = " + ds.HP + ", maxmana = " +
                                    ds.maxMP + ", mana = " + ds.MP + ", strength = " + ds.strength + ", dexterity = " + ds.dexterity + ", gold = " + 
                                    ds.gold + ", weapon = \"" + weaponName + "\", shield = \"" + shieldName + "\", playtime = \"" + 
                                    gp.playerTime.formatDuration(gp.playerTime.seconds) + "\" where sessionID = " + gp.toSQL.SessionID);
                gp.playerTime.saveTimer();
                System.out.println(gp.playerTime.formatDuration(gp.playerTime.seconds));
            }
            else {
                System.out.println("new entry");
                gp.toSQL.toDatabase("insert into playerrecords values(" + (lastSessionID + 1) + "," + ds.level + "," + ds.maxHP + 
                            "," + ds.HP + "," + ds.maxMP + "," + ds.MP + "," + ds.strength + "," + 
                            ds.dexterity + "," + gp.player.gold + ",\"" + weaponName + "\",\"" + shieldName + "\",\"" + 
                            gp.playerTime.formatDuration(gp.playerTime.seconds) + "\")");
                gp.playerTime.saveTimer();
                System.out.println(gp.playerTime.formatDuration(gp.playerTime.seconds));
            }
        } catch (Exception e) {
            System.out.println("Save Exception!");
        }
    }

    public void load(){

        try {
            gp.playerTime.startTimer();
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
            gp.currentMap = ds.currentMap;
            gp.currentArea = ds.currentArea;
            gp.currentLevel = ds.currentLevel;
            gp.previousLevel = ds.previousLevel;

            // Progress
            Progress.orcDefeated = ds.orcDefeated;
            Progress.demonMonkDefeated = ds.demonMonkDefeated;
            Progress.necromancerDefeated = ds.necromancerDefeated;
            Progress.demonLordDefeated = ds.demonLordDefeated;

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
                            gp.obj[mapNum][i].setLoot(gp.eGenerator.getObject(ds.mapObjectLootName[mapNum][i]));   
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        gp.obj[mapNum][i].collision = ds.mapObjectCollision[mapNum][i];
                        if (gp.obj[mapNum][i].opened == true){
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }
                    }
                }
            }

            // NPCs on Map
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                for (int i = 0; i < gp.npc[1].length; i++){

                    if (ds.mapNPCNames[mapNum][i].equals("NA")){
                        gp.npc[mapNum][i] = null;
                    }
                    else{
                        gp.npc[mapNum][i] = gp.eGenerator.getNPC(ds.mapNPCNames[mapNum][i]);
                        gp.npc[mapNum][i].worldX = ds.mapNPCWorldX[mapNum][i];
                        gp.npc[mapNum][i].worldY = ds.mapNPCWorldY[mapNum][i];
                        if (ds.mapNPCRewardName[mapNum][i] != null){
                            gp.npc[mapNum][i].setReward(gp.eGenerator.getObject(ds.mapNPCRewardName[mapNum][i]));   
                        }
                        gp.npc[mapNum][i].doneQuest1 = ds.mapNPCDoneQuest1[mapNum][i];
                        gp.npc[mapNum][i].doneQuest2 = ds.mapNPCDoneQuest2[mapNum][i];
                        gp.npc[mapNum][i].receivedReward = ds.mapNPCReceivedReward[mapNum][i];
                        gp.npc[mapNum][i].pickedQuestObject = ds.mapNPCPickedQuestObject[mapNum][i];
                        gp.npc[mapNum][i].standby = ds.mapNPCStandby[mapNum][i];
                        gp.npc[mapNum][i].sleep = ds.mapNPCSleep[mapNum][i];
                    }
                }
            }



        } catch (Exception e) {
            System.out.println("Load Exception!");
        }

    }
    
}
