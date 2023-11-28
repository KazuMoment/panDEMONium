package enemy;

import java.awt.Rectangle;
import java.util.Random;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.Object_Gold;
import object.Object_Heart;
import object.Object_Iron_Gate;
import object.Object_ManaCrystal;

public class Enemy_DemonLord extends Entity {
	GamePanel gp;
	public static final String enemyName = "Demon Lord";

	public Enemy_DemonLord(GamePanel gp) {
		super(gp);

		this.gp = gp;

		type = type_enemy;
		boss = true;
		name = enemyName;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxHP = 50;
		HP = maxHP;
		attack = 4;
		defense = 3;
		EXP = 50;
		knockbackPower = 8;
        sleep = true;
		
		int size = gp.tileSize*3;
		solidArea = new Rectangle(48, 48, size - 48 * 2, size - 48);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 110;
		attackArea.height = 113;
		motion1_duration = 25;
		motion2_duration = 40;
		
		getImage();
		getAttackImage();
		setDialogue();
		
	}
	
	public void getImage(){
		int i = 3;
		up1 = setup("/enemy/demon_knight", gp.tileSize*i, gp.tileSize*i);
		up2 = setup("/enemy/demon_knight", gp.tileSize*i, gp.tileSize*i);
		down1 = setup("/enemy/demon_knight", gp.tileSize*i, gp.tileSize*i);
		down2 = setup("/enemy/demon_knight", gp.tileSize*i, gp.tileSize*i);
		left1 = setup("/enemy/demon_knight", gp.tileSize*i, gp.tileSize*i);
		left2 = setup("/enemy/demon_knight", gp.tileSize*i, gp.tileSize*i);
		right1 = setup("/enemy/demon_knight", gp.tileSize*i, gp.tileSize*i);
		right2 = setup("/enemy/demon_knight", gp.tileSize*i, gp.tileSize*i);
	}

	public void getAttackImage(){
		int i = 3;
		attackUp1 = setup("/enemy/demon_knight", gp.tileSize*i, gp.tileSize*i*2);
		attackUp2 = setup("/enemy/demon_knight", gp.tileSize*i, gp.tileSize*i*2);
		attackDown1 = setup("/enemy/demon_knight", gp.tileSize*i, gp.tileSize*i*2);
		attackDown2 = setup("/enemy/demon_knight", gp.tileSize*i, gp.tileSize*i*2);
		attackLeft1 = setup("/enemy/demon_knight", gp.tileSize*i*2, gp.tileSize*i);
		attackLeft2 = setup("/enemy/demon_knight", gp.tileSize*i*2, gp.tileSize*i);
		attackRight1 = setup("/enemy/demon_knight", gp.tileSize*i*2, gp.tileSize*i);
		attackRight2 = setup("/enemy/demon_knight", gp.tileSize*i*2, gp.tileSize*i);
	}

	public void setDialogue(){
		dialogue[0][0] = "Welcome.";
		dialogue[0][1] = "You seem to be making a name for yourself, hero.";
        dialogue[0][2] = "Unfortunately, the Demon Lord sees it fit to \nsee your true capabilities.";
        dialogue[0][3] = "Let us do battle.";
	}

	public void setMovement(){	

        if (rage == false && HP < maxHP/2){
            rage = true;
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;
        }

		if (getTileDistance(gp.player) < 10){	
			moveTowardPlayer(60);
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
	}

	public void checkDrop(){

        gp.bossBattleOn = false;
        Progress.demonMonkDefeated = true;

        // Restore Previous Music
        gp.stopMusic();
        gp.checkMusic();

        // Remove the iron doors
        for (int i = 0; i < gp.obj[1].length; i++){
            if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(Object_Iron_Gate.objectName)){
                gp.playSoundEffect(24);
                gp.obj[gp.currentMap][i] = null;
            }
        }

		int i = new Random().nextInt(4);

		if (i == 0 || i == 3){
			dropItem(new Object_Gold(gp));
		}

		if(i == 1){
			dropItem(new Object_Heart(gp));
		}
		if(i == 2){
			dropItem(new Object_ManaCrystal(gp));
		}
	}
}
