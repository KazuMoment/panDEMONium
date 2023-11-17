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


    
}
