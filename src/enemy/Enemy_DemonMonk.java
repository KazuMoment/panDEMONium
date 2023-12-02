package enemy;

import java.awt.Rectangle;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.Object_Iron_Gate;
import object.Object_Seal;

public class Enemy_DemonMonk extends Entity {
	GamePanel gp;
	public static final String enemyName = "Lao Xi";

	public Enemy_DemonMonk(GamePanel gp) {
		super(gp);

		this.gp = gp;

		type = type_enemy;
		boss = true;
		name = enemyName;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxHP = 500;
		HP = maxHP;
		attack = 5;
		defense = 7;
		EXP = 50;
		knockbackPower = 8;
        sleep = true;
		
		int size = gp.tileSize*3;
		solidArea = new Rectangle(48, 48, size - 48 * 2, size - 48);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 90;
		attackArea.height = 110;
		motion1_duration = 25;
		motion2_duration = 40;
		
		getImage();
		getAttackImage();
		setDialogue();
		
	}
	
	public void getImage(){
		int i = 3;
		up1 = setup("/enemy/monk_up_1", gp.tileSize*i, gp.tileSize*i);
		up2 = setup("/enemy/monk_up_2", gp.tileSize*i, gp.tileSize*i);
		down1 = setup("/enemy/monk_down_1", gp.tileSize*i, gp.tileSize*i);
		down2 = setup("/enemy/monk_down_2", gp.tileSize*i, gp.tileSize*i);
		left1 = setup("/enemy/monk_left_1", gp.tileSize*i, gp.tileSize*i);
		left2 = setup("/enemy/monk_left_2", gp.tileSize*i, gp.tileSize*i);
		right1 = setup("/enemy/monk_right_1", gp.tileSize*i, gp.tileSize*i);
		right2 = setup("/enemy/monk_right_2", gp.tileSize*i, gp.tileSize*i);
	}

	public void getAttackImage(){
		int i = 3;
		attackUp1 = setup("/enemy/monk_attack_up_1", gp.tileSize*i, gp.tileSize*i*2);
		attackUp2 = setup("/enemy/monk_attack_up_2", gp.tileSize*i, gp.tileSize*i*2);
		attackDown1 = setup("/enemy/monk_attack_down_1", gp.tileSize*i, gp.tileSize*i*2);
		attackDown2 = setup("/enemy/monk_attack_down_2", gp.tileSize*i, gp.tileSize*i*2);
		attackLeft1 = setup("/enemy/monk_attack_left_1", gp.tileSize*i*2, gp.tileSize*i);
		attackLeft2 = setup("/enemy/monk_attack_left_2", gp.tileSize*i*2, gp.tileSize*i);
		attackRight1 = setup("/enemy/monk_attack_right_1", gp.tileSize*i*2, gp.tileSize*i);
		attackRight2 = setup("/enemy/monk_attack_right_2", gp.tileSize*i*2, gp.tileSize*i);
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
			motion1_duration = 15;
			motion2_duration = 60;
            attack += 3;
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

		startDialogue(gp.eHandler.eventMaster, 2);

		if (Progress.completedGame == false){
			dropItem(new Object_Seal(gp));
		}

	}
}
