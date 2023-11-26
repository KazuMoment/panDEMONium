package main;

import enemy.Enemy_GreenSlime;
import enemy.Enemy_Orc;
import entity.NPC_Farid;
import entity.NPC_Kane;
import entity.NPC_Piyaye;
import entity.NPC_Reul;
import object.Object_Axe_Normal;
import object.Object_Bonfire;
import object.Object_Chest;
import object.Object_Door;
import object.Object_Health_Potion_Small;
import object.Object_Iron_Gate;
import object.Object_Key;
import object.Object_Lantern;
import object.Object_Lever;
import object.Object_Shield_Victoria;
import object.Object_Slimeball;
import object.Object_Sword_Tinvaak;
import object.Object_Tent;
import tile_interactive.IT_DryTree;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter (GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        int mapNumber = 0;
        int i = 0;

        gp.obj[mapNumber][i] = new Object_Axe_Normal(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 25;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 25;
		i++;

		gp.obj[mapNumber][i] = new Object_Bonfire(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 22;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 25;
		i++;

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Health_Potion_Small(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 29;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 40;
		i++;

        gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Health_Potion_Small(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 30;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 10;
		i++;

        gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Health_Potion_Small(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 32;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 22;
		i++;

		gp.obj[mapNumber][i] = new Object_Key(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 24;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 44;
		i++;

		gp.obj[mapNumber][i] = new Object_Door(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 18;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 38;
		i++;

		
		mapNumber = 1;
		i = 0;
		gp.obj[mapNumber][i] = new Object_Door(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 34;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 41;
		i++;

		gp.obj[mapNumber][i] = new Object_Door(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 9;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 37;
		i++;

		gp.obj[mapNumber][i] = new Object_Iron_Gate(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 16;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 7;
		i++;

		gp.obj[mapNumber][i] = new Object_Door(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 39;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 22;
		i++;

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Key(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 34;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 37;
		i++;

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Key(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 25;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 44;
		i++;

		gp.obj[mapNumber][i] = new Object_Key(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 6;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 6;
		i++;

		gp.obj[mapNumber][i] = new Object_Key(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 45;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 8;
		i++;

		gp.obj[mapNumber][i] = new Object_Lever(gp);
		gp.obj[mapNumber][i].setOpen(mapNumber, 2);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 15;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 20;
		i++;
        

         
    }

    public void setNPC(){

        int mapNumber = 0;
        int i = 0;

        // gp.npc[mapNumber][i] = new NPC_Reul(gp);
        // gp.npc[mapNumber][i].worldX = gp.tileSize  *  25;
        // gp.npc[mapNumber][i].worldY = gp.tileSize  *  26;
        // i++;

		gp.npc[mapNumber][i] = new NPC_Piyaye(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 10;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 12;
        i++;

        // gp.npc[mapNumber][i] = new NPC_Kane(gp);
        // gp.npc[mapNumber][i].worldX = gp.tileSize  *  19;
        // gp.npc[mapNumber][i].worldY = gp.tileSize  *  21;
        // i++;

       
        mapNumber = 2;
        i = 0;
        gp.npc[mapNumber][i] = new NPC_Farid(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 25;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 24;
		i++;


    }

    public void setEnemy(){

        int mapNumber = 0;
        int i = 0;

        gp.enemy[mapNumber][i] = new Enemy_GreenSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 10;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 33;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_GreenSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 13;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 36;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_GreenSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 12;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 35;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_GreenSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 21;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 37;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_GreenSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 20;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 34;
		i++;

		// gp.enemy[mapNumber][i] = new Enemy_Orc(gp);
		// gp.enemy[mapNumber][i].worldX = gp.tileSize * 27;
		// gp.enemy[mapNumber][i].worldY = gp.tileSize * 16;
		// i++;

		// gp.enemy[mapNumber][i] = new Enemy_Orc(gp);
		// gp.enemy[mapNumber][i].worldX = gp.tileSize * 31;
		// gp.enemy[mapNumber][i].worldY = gp.tileSize * 12;
		// i++;


    }

    public void setInteractiveTile(){
        int mapNumber = 0;
        int i = 0;

        gp.iTile[mapNumber][i] = new IT_DryTree(gp, 23, 29); i++;

    }
    
}
