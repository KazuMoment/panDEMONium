package main;

import data.Progress;
import enemy.Enemy_DemonLord;
import enemy.Enemy_GreenSlime;
import enemy.Enemy_DemonMonk;
import enemy.Enemy_Necromancer;
import enemy.Enemy_Orc;
import enemy.Enemy_RockSlime;
import enemy.Enemy_Zombie;
import entity.NPC_BJ;
import entity.NPC_Bogart;
import entity.NPC_Cally;
import entity.NPC_Farid;
import entity.NPC_Fischer;
import entity.NPC_Hal;
import entity.NPC_Holly;
import entity.NPC_JB;
import entity.NPC_Jobert;
import entity.NPC_Lazlow;
import entity.NPC_Martha;
import entity.NPC_Piyaye;
import entity.NPC_Mounsi;
import entity.NPC_Reul;
import entity.NPC_Wally;
import object.Object_Axe_Normal;
import object.Object_Boat;
import object.Object_Bonfire;
import object.Object_Chest;
import object.Object_Door;
import object.Object_Health_Potion_Small;
import object.Object_Iron_Gate;
import object.Object_Key;
import object.Object_Lantern;
import object.Object_Lever;
import object.Object_Mana_Potion_Small;
import object.Object_Shield_Tinvaak;
import object.Object_Shield_Wood;
import object.Object_Sword_Tinvaak;
import object.Object_Sword_Vorlorn;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_WitheredTree;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter (GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
		//forest tutorial
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

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Shield_Wood(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 18;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 10;
		i++;

		gp.obj[mapNumber][i] = new Object_Boat(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 34;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 39;
		i++;
		
		//dungeon 1
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
		
		//village 1
		mapNumber = 3;
		i = 0;

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Mana_Potion_Small(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 27;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 4;
		i++;

		gp.obj[mapNumber][i] = new Object_Lantern(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 14;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 13;
		i++;

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Health_Potion_Small(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 22;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 17;
		i++;

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Shield_Tinvaak(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 38;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 42;
		i++;

		gp.obj[mapNumber][i] = new Object_Iron_Gate(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 19;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 7;
		i++;

		gp.obj[mapNumber][i] = new Object_Bonfire(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize * 17;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 34;
		i++;

		mapNumber = 5;
		i = 0; 
		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Sword_Tinvaak(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 19;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 20;
		i++;
        

		
		// village_2
		mapNumber = 8;
		i = 0;

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Health_Potion_Small(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 12;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 9;
		i++;

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Health_Potion_Small(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 42;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 14;
		i++;

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Health_Potion_Small(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 42;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 36;
		i++;

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Health_Potion_Small(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize * 14;
		gp.obj[mapNumber][i].worldY = gp.tileSize * 25;
		i++;

		gp.obj[mapNumber][i] = new Object_Boat(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize*14;
		gp.obj[mapNumber][i].worldY = gp.tileSize*22;
		i++;

		// dungeon_2
		mapNumber = 9;
		i = 0;

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Health_Potion_Small(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize*40;
		gp.obj[mapNumber][i].worldY = gp.tileSize*28;
		i++;

		
		//house5
		mapNumber = 12;
		i = 0;

		gp.obj[mapNumber][i] = new Object_Chest(gp);
		gp.obj[mapNumber][i].setLoot(new Object_Sword_Vorlorn(gp));
		gp.obj[mapNumber][i].worldX = gp.tileSize*19;
		gp.obj[mapNumber][i].worldY = gp.tileSize*20;
		i++;

		mapNumber = 14;
		i = 0;
		gp.obj[mapNumber][i] = new Object_Boat(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize*21;
		gp.obj[mapNumber][i].worldY = gp.tileSize*42;
		gp.obj[mapNumber][i].collision = false;
		i++;

		gp.obj[mapNumber][i] = new Object_Bonfire(gp);
		gp.obj[mapNumber][i].worldX = gp.tileSize*20;
		gp.obj[mapNumber][i].worldY = gp.tileSize*37;
		i++;

    }

    public void setNPC(){

        int mapNumber = 0;
        int i = 0;

        gp.npc[mapNumber][i] = new NPC_Reul(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 25;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 26;
		gp.npc[mapNumber][i].setReward(new Object_Health_Potion_Small(gp));
        i++;

		gp.npc[mapNumber][i] = new NPC_Piyaye(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 10;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 12;
        i++;
       
        mapNumber = 2;
        i = 0;

        gp.npc[mapNumber][i] = new NPC_Farid(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 25;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 24;
		i++;

		mapNumber = 3;
		i = 0;

		gp.npc[mapNumber][i] = new NPC_Cally(gp);
		gp.npc[mapNumber][i].worldX = gp.tileSize*30;
		gp.npc[mapNumber][i].worldY = gp.tileSize*36;
		i++;
		
		mapNumber = 4;
		i = 0;

		gp.npc[mapNumber][i] = new NPC_Fischer(gp);
		gp.npc[mapNumber][i].worldX = gp.tileSize*23;
		gp.npc[mapNumber][i].worldY = gp.tileSize*26;
		i++;
		
		mapNumber = 5;
		i = 0;

		gp.npc[mapNumber][i] = new NPC_Holly(gp);
		gp.npc[mapNumber][i].worldX = gp.tileSize*30;
		gp.npc[mapNumber][i].worldY = gp.tileSize*28;
		i++;
		
		mapNumber = 6;
		i = 0;
		
		gp.npc[mapNumber][i] = new NPC_Hal(gp);
		gp.npc[mapNumber][i].worldX = gp.tileSize*30;
		gp.npc[mapNumber][i].worldY = gp.tileSize*28;
		i++;

		gp.npc[mapNumber][i] = new NPC_JB(gp);
		gp.npc[mapNumber][i].worldX = gp.tileSize*20;
		gp.npc[mapNumber][i].worldY = gp.tileSize*20;
		i++;
		
		mapNumber = 7;
		i = 0;

		gp.npc[mapNumber][i] = new NPC_Wally(gp);
		gp.npc[mapNumber][i].worldX = gp.tileSize*30;
		gp.npc[mapNumber][i].worldY = gp.tileSize*28;
		i++;
		
		gp.npc[mapNumber][i] = new NPC_BJ(gp);
		gp.npc[mapNumber][i].worldX = gp.tileSize*20;
		gp.npc[mapNumber][i].worldY = gp.tileSize*20;
		i++;

		// village_2
		mapNumber = 8;
        i = 0;    

        gp.npc[mapNumber][i] = new NPC_Bogart(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 14;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 27;
        i++;
        
        gp.npc[mapNumber][i] = new NPC_Jobert(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 34;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 33;
        i++;
        
        gp.npc[mapNumber][i] = new NPC_Martha(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 26;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 34;
        i++;
        
        
    	// mayor_office
        mapNumber = 10;
        i = 0;

        gp.npc[mapNumber][i] = new NPC_Mounsi(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 25;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 28;
        i++;
        
        // house5
        mapNumber = 12;
        i = 0;

        gp.npc[mapNumber][i] = new NPC_Lazlow(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 18;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 21;
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

		if (Progress.orcDefeated == false){
			gp.enemy[mapNumber][i] = new Enemy_Orc(gp);
			gp.enemy[mapNumber][i].worldX = gp.tileSize * 28;
			gp.enemy[mapNumber][i].worldY = gp.tileSize * 16;
			i++;
		}

		mapNumber = 1;
		i = 0;

		gp.enemy[mapNumber][i] = new Enemy_RockSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 25;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 38;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_RockSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 22;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 38;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_RockSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 25;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 40;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_RockSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 11;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 32;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_RockSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 14;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 32;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_RockSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 13;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 29;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_RockSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 13;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 23;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_RockSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 14;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 14;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_RockSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 9;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 9;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_RockSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 29;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 11;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_RockSlime(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize * 33;
		gp.enemy[mapNumber][i].worldY = gp.tileSize * 11;
		i++;

		if (Progress.demonMonkDefeated == false){
			gp.enemy[mapNumber][i] = new Enemy_DemonMonk(gp);
			gp.enemy[mapNumber][i].worldX = gp.tileSize * 29;
			gp.enemy[mapNumber][i].worldY = gp.tileSize * 27;
			i++;
		}

		// village_2
		mapNumber = 8;
		i = 0;

		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*20;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*11;
		i++;

		
		// dungeon_2
		mapNumber = 9;
		i = 0;
		
		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*17;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*34;
		i++;	

		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*13;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*24;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*15;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*26;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*19;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*24;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*24;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*24;
		i++;
		
		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*28;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*29;
		i++;
		
		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*25;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*34;
		i++;

		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*37;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*23;
		i++;
		
		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*35;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*18;
		i++;
		
		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*41;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*13;
		i++;
		
		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*33;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*8;
		i++;
		
		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*31;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*16;
		i++;
		
		gp.enemy[mapNumber][i] = new Enemy_Zombie(gp);
		gp.enemy[mapNumber][i].worldX = gp.tileSize*24;
		gp.enemy[mapNumber][i].worldY = gp.tileSize*12;
		i++;	


		if (Progress.necromancerDefeated == false){
			gp.enemy[mapNumber][i] = new Enemy_Necromancer(gp);
			gp.enemy[mapNumber][i].worldX = gp.tileSize * 15;
			gp.enemy[mapNumber][i].worldY = gp.tileSize * 12;
			i++;
		}	

		mapNumber = 14;
		i = 0;
		if (Progress.demonLordDefeated == false){
			gp.enemy[mapNumber][i] = new Enemy_DemonLord(gp);
			gp.enemy[mapNumber][i].worldX = gp.tileSize * 20;
			gp.enemy[mapNumber][i].worldY = gp.tileSize * 21;
			i++;
		}	

    }

    public void setInteractiveTile(){
        int mapNumber = 0;
        int i = 0;

        gp.iTile[mapNumber][i] = new IT_DryTree(gp, 23, 29); i++;
		gp.iTile[mapNumber][i] = new IT_DryTree(gp, 18, 38); i++;
		gp.iTile[mapNumber][i] = new IT_DryTree(gp, 13, 32); i++;
		gp.iTile[mapNumber][i] = new IT_DryTree(gp, 19, 15); i++;
		gp.iTile[mapNumber][i] = new IT_DryTree(gp, 11, 11); i++;
		gp.iTile[mapNumber][i] = new IT_DryTree(gp, 38, 30); i++;
		gp.iTile[mapNumber][i] = new IT_DryTree(gp, 30, 13); i++;

		mapNumber = 3;
		gp.iTile[mapNumber][i] = new IT_DryTree(gp, 36, 30); i++;
		gp.iTile[mapNumber][i] = new IT_DryTree(gp, 24, 18); i++;
		gp.iTile[mapNumber][i] = new IT_DryTree(gp, 22, 15); i++;
		gp.iTile[mapNumber][i] = new IT_DryTree(gp, 21, 15); i++;
		gp.iTile[mapNumber][i] = new IT_DryTree(gp, 11, 8); i++;

	 	mapNumber = 8;
		i = 0;

		gp.iTile[mapNumber][i] = new IT_WitheredTree(gp, 25, 12); i++;
		gp.iTile[mapNumber][i] = new IT_WitheredTree(gp, 42, 17); i++;
		gp.iTile[mapNumber][i] = new IT_WitheredTree(gp, 39, 40); i++;

    }
    
}
