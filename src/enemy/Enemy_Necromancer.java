package enemy;


import data.Progress;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

import object.Object_Purple_Haze;
import object.Object_Sacred_Rose;
import object.Object_WitheredTree;

public class Enemy_Necromancer extends Entity{

    GamePanel gp;
    public static final String enemyName = "Renovamen";

    public Enemy_Necromancer(GamePanel gp) {
        super(gp);

        this.gp = gp;

        boss = true;
        sleep = true;
        type = type_enemy;
        name = enemyName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxHP = 500;
        HP = maxHP;
        attack = 6; 
        defense = 7;
        knockbackPower = 1;
        EXP = 50;
        projectile = new Object_Purple_Haze(gp);

        int size = gp.tileSize*3;
        solidArea = new Rectangle(48, 48, size - 48 * 2, size - 48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
        getImage();
    }

    public void getImage(){
    	
    	int i = 3;

        down1 = setup("/enemy/necro_down1", gp.tileSize*i, gp.tileSize*i);
        down2 = setup("/enemy/necro_down2", gp.tileSize*i, gp.tileSize*i);
        up1 = setup("/enemy/necro_up1", gp.tileSize*i, gp.tileSize*i);
        up2 = setup("/enemy/necro_up2", gp.tileSize*i, gp.tileSize*i);
        left1 = setup("/enemy/necro_left1", gp.tileSize*i, gp.tileSize*i);
        left2 = setup("/enemy/necro_left2", gp.tileSize*i, gp.tileSize*i);
        right1 = setup("/enemy/necro_right1", gp.tileSize*i, gp.tileSize*i);
        right2 = setup("/enemy/necro_right2", gp.tileSize*i, gp.tileSize*i);

    }

    public void setDialogue(){

        dialogue[0][0] = "You do not understand what \nI am trying to achieve here.";
        dialogue[0][1] = "You saw them, haven't you?";
        dialogue[0][2] = "The dead, brought back to life?";
        dialogue[0][3] = "I do not expect you to understand.";
        dialogue[0][4] = "Losing your loved one...";
        dialogue[0][5] = "I am trying to achieve greatness!";
        dialogue[0][6] = "I am bringing my lover back!";
        dialogue[0][7] = "And if I have to become God to do it...";
        dialogue[0][8] = "I will.";

    }

    public void setMovement(){

        if (rage == false && HP < maxHP/2){
            rage = true;
            defaultSpeed++;
            speed = defaultSpeed;
            attack += 3;
        }

        if (rage == true){
            shootRate(100, 30);
        }

		if (getTileDistance(gp.player) < 10){	
			moveTowardPlayer(60);
		}

        if (getTileDistance(gp.player) > 3 && getTileDistance(gp.player) < 10 && rage == false){
            shootRate(200, 30);
        }

		else {
			// Get a random direction to move if not chasing
			getRandomDirection(120);
		}
		
		// Check if it attacks
		if (attacking == false){
            attackRate(40, gp.tileSize * 4, gp.tileSize);
		}

    }
    
    public void damageReaction(){

        actionLockCounter = 0;
        following = true;
        onPath = true;

    }

   
    public void checkDrop(){

        gp.bossBattleOn = false;
        Progress.necromancerDefeated = true;

        // Restore Previous Music
        gp.stopMusic();
        gp.checkMusic();

        // Remove the iron doors
        for (int i = 0; i < gp.obj[1].length; i++){
            if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(Object_WitheredTree.objectName)){
                gp.playSoundEffect(24);
                gp.obj[gp.currentMap][i] = null;
            }
        }
        
        dropItem(new Object_Sacred_Rose(gp));

        startDialogue(gp.eHandler.eventMaster, 3);

    }

    public void dropItem(){
        
    }
    
}
