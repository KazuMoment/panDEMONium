package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{

    // Player Stats
    int level;
    int maxHP;
    int HP;
    int maxMP;
    int MP;
    int strength;
    int dexterity;
    int EXP;
    int nextLevelEXP;
    int gold;


    // Current Area
    int currentMap;
    int previousLevel;
    int currentLevel;
    int currentArea;

    // Progress
    boolean orcDefeated;
    boolean demonMonkDefeated;
    boolean necromancerDefeated;
    boolean demonLordDefeated;

    // Player Inventory
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmount = new ArrayList<>();
    int currentWeaponSlot;
    int currentShieldSlot;

    // Objects on Map
    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootName[][];
    boolean mapObjectOpened[][];
    boolean mapObjectCollision[][];

    // NPC on Map
    String mapNPCNames[][];
    int mapNPCWorldX[][];
    int mapNPCWorldY[][];
    String mapNPCRewardName[][];
    boolean mapNPCDoneQuest1[][];
    boolean mapNPCDoneQuest2[][];
    boolean mapNPCReceivedReward[][];
    boolean mapNPCPickedQuestObject[][];
    boolean mapNPCStandby[][];
    boolean mapNPCSleep[][];



    
}
