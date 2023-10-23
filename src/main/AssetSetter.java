package main;

import enemy.Enemy_GreenSlime;
import enemy.Enemy_Orc;
import entity.NPC_Farid;
import entity.NPC_Kane;
import entity.NPC_Reul;
import object.Object_Axe_Normal;
import object.Object_Chest;
import object.Object_Door;
import object.Object_Health_Potion_Small;
import object.Object_Key;
import object.Object_Lantern;
import object.Object_Shield_Metal;
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

        gp.obj[mapNumber][i] = new Object_Key(gp);
        gp.obj[mapNumber][i].worldX = gp.tileSize * 25;
        gp.obj[mapNumber][i].worldY = gp.tileSize * 23;
        i++;

        gp.obj[mapNumber][i] = new Object_Axe_Normal(gp);
        gp.obj[mapNumber][i].worldX = gp.tileSize * 33;
        gp.obj[mapNumber][i].worldY = gp.tileSize * 21;
        i++;

        gp.obj[mapNumber][i] = new Object_Shield_Metal(gp);
        gp.obj[mapNumber][i].worldX = gp.tileSize * 23;
        gp.obj[mapNumber][i].worldY = gp.tileSize * 12;
        i++;

        gp.obj[mapNumber][i] = new Object_Door(gp);
        gp.obj[mapNumber][i].worldX = gp.tileSize * 12;
        gp.obj[mapNumber][i].worldY = gp.tileSize * 12;
        i++;

        gp.obj[mapNumber][i] = new Object_Door(gp);
        gp.obj[mapNumber][i].worldX = gp.tileSize * 14;
        gp.obj[mapNumber][i].worldY = gp.tileSize * 28;
        i++;

        gp.obj[mapNumber][i] = new Object_Chest(gp, new Object_Health_Potion_Small(gp));
        gp.obj[mapNumber][i].worldX = gp.tileSize * 23;
        gp.obj[mapNumber][i].worldY = gp.tileSize * 22;
        i++;

        gp.obj[mapNumber][i] = new Object_Lantern(gp);
        gp.obj[mapNumber][i].worldX = gp.tileSize * 18;
        gp.obj[mapNumber][i].worldY = gp.tileSize * 20;
        i++;

        gp.obj[mapNumber][i] = new Object_Tent(gp);
        gp.obj[mapNumber][i].worldX = gp.tileSize * 19;
        gp.obj[mapNumber][i].worldY = gp.tileSize * 20;
        i++;
         
    }

    public void setNPC(){

        int mapNumber = 0;
        int i = 0;
        gp.npc[mapNumber][i] = new NPC_Kane(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 19;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 21;
        i++;

        gp.npc[mapNumber][i] = new NPC_Reul(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 26;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 21;
        i++;

        mapNumber = 1;
        i = 0;
        gp.npc[mapNumber][i] = new NPC_Farid(gp);
        gp.npc[mapNumber][i].worldX = gp.tileSize * 12;
        gp.npc[mapNumber][i].worldY = gp.tileSize * 7;


    }

    public void setEnemy(){

        int mapNumber = 0;
        int i = 0;

        gp.enemy[mapNumber][i] = new Enemy_GreenSlime(gp);
        gp.enemy[mapNumber][i].worldX = gp.tileSize * 21;
        gp.enemy[mapNumber][i].worldY = gp.tileSize * 38;
        i++;

        gp.enemy[mapNumber][i] = new Enemy_GreenSlime(gp);
        gp.enemy[mapNumber][i].worldX = gp.tileSize * 23;
        gp.enemy[mapNumber][i].worldY = gp.tileSize * 42;
        i++;

        gp.enemy[mapNumber][i] = new Enemy_GreenSlime(gp);
        gp.enemy[mapNumber][i].worldX = gp.tileSize * 24;
        gp.enemy[mapNumber][i].worldY = gp.tileSize * 37;
        i++;

        gp.enemy[mapNumber][i] = new Enemy_GreenSlime(gp);
        gp.enemy[mapNumber][i].worldX = gp.tileSize * 34;
        gp.enemy[mapNumber][i].worldY = gp.tileSize * 42;
        i++;

        gp.enemy[mapNumber][i] = new Enemy_GreenSlime(gp);
        gp.enemy[mapNumber][i].worldX = gp.tileSize * 38;
        gp.enemy[mapNumber][i].worldY = gp.tileSize * 42;
        i++;

        gp.enemy[mapNumber][i] = new Enemy_Orc(gp);
        gp.enemy[mapNumber][i].worldX = gp.tileSize * 12;
        gp.enemy[mapNumber][i].worldY = gp.tileSize * 33;
        i++;

    }

    public void setInteractiveTile(){
        int mapNumber = 0;
        int i = 0;

        gp.iTile[mapNumber][i] = new IT_DryTree(gp, 25, 26); i++;
        gp.iTile[mapNumber][i] = new IT_DryTree(gp, 26, 26); i++;
        gp.iTile[mapNumber][i] = new IT_DryTree(gp, 27, 26); i++;
        gp.iTile[mapNumber][i] = new IT_DryTree(gp, 28, 26); i++;
        gp.iTile[mapNumber][i] = new IT_DryTree(gp, 29, 26); i++;
        gp.iTile[mapNumber][i] = new IT_DryTree(gp, 30, 26); i++;
        gp.iTile[mapNumber][i] = new IT_DryTree(gp, 31, 26); i++;
        gp.iTile[mapNumber][i] = new IT_DryTree(gp, 32, 26); i++;
        gp.iTile[mapNumber][i] = new IT_DryTree(gp, 33, 26); i++;
        gp.iTile[mapNumber][i] = new IT_DryTree(gp, 34, 26); i++;
    }
    
}
