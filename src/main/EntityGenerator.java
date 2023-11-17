package main;

import entity.Entity;
import object.Object_Axe_Normal;
import object.Object_Boots;
import object.Object_Chest;
import object.Object_Door;
import object.Object_Fireball;
import object.Object_Gold;
import object.Object_Health_Potion_Small;
import object.Object_Heart;
import object.Object_Key;
import object.Object_Lantern;
import object.Object_ManaCrystal;
import object.Object_Shield_Tinvaak;
import object.Object_Shield_Victoria;
import object.Object_Slimeball;
import object.Object_Sword_Tinvaak;
import object.Object_Tent;

public class EntityGenerator {

    GamePanel gp;

    public EntityGenerator(GamePanel gp){
        this.gp = gp;
    }

     public Entity getObject(String itemName){

        Entity obj = null;

        switch(itemName){
            case Object_Axe_Normal.objectName: obj = new Object_Axe_Normal(gp); break;
            case Object_Boots.objectName: obj = new Object_Boots(gp); break;
            case Object_Chest.objectName: obj = new Object_Chest(gp); break;
            case Object_Door.objectName: obj = new Object_Door(gp); break;
            case Object_Fireball.objectName: obj = new Object_Fireball(gp); break;
            case Object_Gold.objectName: obj = new Object_Gold(gp); break;
            case Object_Health_Potion_Small.objectName: obj = new Object_Health_Potion_Small(gp); break;
            case Object_Heart.objectName: obj = new Object_Heart(gp);
            case Object_Key.objectName: obj = new Object_Key(gp); break;
            case Object_Lantern.objectName: obj = new Object_Lantern(gp); break;
            case Object_ManaCrystal.objectName: obj = new Object_ManaCrystal(gp);
            case Object_Sword_Tinvaak.objectName: obj = new Object_Sword_Tinvaak(gp); break;
            case Object_Shield_Tinvaak.objectName: obj = new Object_Shield_Tinvaak(gp); break;
            case Object_Shield_Victoria.objectName: obj = new Object_Shield_Victoria(gp); break;
            case Object_Slimeball.objectName: obj = new Object_Slimeball(gp); break;
            case Object_Tent.objectName: obj = new Object_Tent(gp); break; 
        }
        return obj;
    }
    
}
