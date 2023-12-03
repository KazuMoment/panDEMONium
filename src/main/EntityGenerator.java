package main;

import entity.Entity;
import entity.NPC_BJ;
import entity.NPC_Bogart;
import entity.NPC_Cally;
import entity.NPC_Farid;
import entity.NPC_Fischer;
import entity.NPC_Hal;
import entity.NPC_Holly;
import entity.NPC_JB;
import entity.NPC_Jobert;
import entity.NPC_Kane;
import entity.NPC_Lazlow;
import entity.NPC_Martha;
import entity.NPC_Mounsi;
import entity.NPC_Piyaye;
import entity.NPC_Reul;
import entity.NPC_Wally;
import object.Object_Axe_Normal;
import object.Object_Boat;
import object.Object_Bonfire;
import object.Object_Boots;
import object.Object_Chest;
import object.Object_DemonLord_Helmet;
import object.Object_Door;
import object.Object_Fireball;
import object.Object_Gold;
import object.Object_Health_Potion_Small;
import object.Object_Heart;
import object.Object_Iron_Gate;
import object.Object_Key;
import object.Object_Lantern;
import object.Object_Lavaball;
import object.Object_Lever;
import object.Object_ManaCrystal;
import object.Object_Mana_Potion_Small;
import object.Object_Paddle;
import object.Object_Purple_Haze;
import object.Object_Sacred_Rose;
import object.Object_Seal;
import object.Object_Shield_Tinvaak;
import object.Object_Shield_Victoria;
import object.Object_Shield_Wood;
import object.Object_Slimeball;
import object.Object_Sword_Tinvaak;
import object.Object_Sword_Vorlorn;
import object.Object_Tent;
import object.Object_WaterBall;
import object.Object_WitheredTree;

public class EntityGenerator {

    GamePanel gp;

    public EntityGenerator(GamePanel gp){
        this.gp = gp;
    }

    public Entity getObject(String itemName){

        Entity obj = null;

        switch(itemName){
            case Object_Axe_Normal.objectName: obj = new Object_Axe_Normal(gp); break;
            case Object_Boat.objectName: obj = new Object_Boat(gp); break;
            case Object_Bonfire.objectName: obj = new Object_Bonfire(gp); break;
            case Object_Boots.objectName: obj = new Object_Boots(gp); break;
            case Object_Chest.objectName: obj = new Object_Chest(gp); break;
            case Object_DemonLord_Helmet.objectName: obj = new Object_DemonLord_Helmet(gp); break;
            case Object_Door.objectName: obj = new Object_Door(gp); break;
            case Object_Fireball.objectName: obj = new Object_Fireball(gp); break;
            case Object_Gold.objectName: obj = new Object_Gold(gp); break;
            case Object_Health_Potion_Small.objectName: obj = new Object_Health_Potion_Small(gp); break;
            case Object_Heart.objectName: obj = new Object_Heart(gp); break;
            case Object_Iron_Gate.objectName: obj = new Object_Iron_Gate(gp); break;
            case Object_Key.objectName: obj = new Object_Key(gp); break;
            case Object_Lantern.objectName: obj = new Object_Lantern(gp); break;
            case Object_Lavaball.objectName: obj = new Object_Lavaball(gp); break;
            case Object_Lever.objectName: obj = new Object_Lever(gp); break;
            case Object_Mana_Potion_Small.objectName: obj = new Object_Mana_Potion_Small(gp); break;
            case Object_ManaCrystal.objectName: obj = new Object_ManaCrystal(gp); break;
            case Object_Paddle.objectName: obj = new Object_Paddle(gp); break;
            case Object_Purple_Haze.objectName: obj = new Object_Purple_Haze(gp); break;
            case Object_Sacred_Rose.objectName: obj = new Object_Sacred_Rose(gp); break;
            case Object_Seal.objectName: obj = new Object_Seal(gp); break;
            case Object_Shield_Tinvaak.objectName: obj = new Object_Shield_Tinvaak(gp); break;
            case Object_Shield_Victoria.objectName: obj = new Object_Shield_Victoria(gp); break;         
            case Object_Shield_Wood.objectName: obj = new Object_Shield_Wood(gp); break;
            case Object_Slimeball.objectName: obj = new Object_Slimeball(gp); break;
            case Object_Sword_Tinvaak.objectName: obj = new Object_Sword_Tinvaak(gp); break;   
            case Object_Sword_Vorlorn.objectName: obj = new Object_Sword_Vorlorn(gp); break;
            case Object_Tent.objectName: obj = new Object_Tent(gp); break; 
            case Object_WaterBall.objectName: obj = new Object_WaterBall(gp); break;
            case Object_WitheredTree.objectName: obj = new Object_WitheredTree(gp); break;
        }
        return obj;
    }

    public Entity getNPC(String npcName){

        Entity npc = null;

        switch(npcName){
            case NPC_BJ.npcName: npc = new NPC_BJ(gp); break;
            case NPC_Bogart.npcName: npc = new NPC_Bogart(gp); break;
            case NPC_Cally.npcName: npc = new NPC_Cally(gp); break;
            case NPC_Farid.npcName: npc = new NPC_Farid(gp); break;
            case NPC_Fischer.npcName: npc = new NPC_Fischer(gp); break;
            case NPC_Hal.npcName: npc = new NPC_Hal(gp); break;
            case NPC_Holly.npcName: npc = new NPC_Holly(gp); break;
            case NPC_JB.npcName: npc = new NPC_JB(gp); break;
            case NPC_Jobert.npcName: npc = new NPC_Jobert(gp); break;
            case NPC_Kane.npcName: npc = new NPC_Kane(gp); break;
            case NPC_Lazlow.npcName: npc = new NPC_Lazlow(gp); break;
            case NPC_Martha.npcName: npc = new NPC_Martha(gp); break;
            case NPC_Mounsi.npcName: npc = new NPC_Mounsi(gp); break;
            case NPC_Piyaye.npcName: npc = new NPC_Piyaye(gp); break;
            case NPC_Reul.npcName: npc = new NPC_Reul(gp); break;
            case NPC_Wally.npcName: npc = new NPC_Wally(gp); break;    
        }
        return npc;
    }
    
}
