package enemy;

import java.awt.Rectangle;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.Object_DemonLord_Helmet;
import object.Object_Fireball;
import object.Object_Iron_Gate;

public class Enemy_DemonLord extends Entity {
	GamePanel gp;
	public static final String enemyName = "Demon Lord";

	public Enemy_DemonLord(GamePanel gp) {
		super(gp);

		this.gp = gp;

		type = type_enemy;
		boss = true;
		name = enemyName;
		defaultSpeed = 3;
		speed = defaultSpeed;
		maxHP = 1000;
		HP = maxHP;
		attack = 6;
		defense = 8;
		EXP = 10000;
		knockbackPower = 30;
        sleep = true;
		projectile = new Object_Fireball(gp);
		
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
		up1 = setup("/enemy/demonlord_up_1", gp.tileSize*i, gp.tileSize*i);
		up2 = setup("/enemy/demonlord_up_2", gp.tileSize*i, gp.tileSize*i);
		down1 = setup("/enemy/demonlord_down_1", gp.tileSize*i, gp.tileSize*i);
		down2 = setup("/enemy/demonlord_down_2", gp.tileSize*i, gp.tileSize*i);
		left1 = setup("/enemy/demonlord_left_1", gp.tileSize*i, gp.tileSize*i);
		left2 = setup("/enemy/demonlord_left_2", gp.tileSize*i, gp.tileSize*i);
		right1 = setup("/enemy/demonlord_right_1", gp.tileSize*i, gp.tileSize*i);
		right2 = setup("/enemy/demonlord_right_2", gp.tileSize*i, gp.tileSize*i);
	}

	public void getAttackImage(){
		int i = 3;
		attackUp1 = setup("/enemy/demonlord_attack_up_1", gp.tileSize*i, gp.tileSize*i*2);
		attackUp2 = setup("/enemy/demonlord_attack_up_2", gp.tileSize*i, gp.tileSize*i*2);
		attackDown1 = setup("/enemy/demonlord_attack_down_1", gp.tileSize*i, gp.tileSize*i*2);
		attackDown2 = setup("/enemy/demonlord_attack_down_2", gp.tileSize*i, gp.tileSize*i*2);
		attackLeft1 = setup("/enemy/demonlord_attack_left_1", gp.tileSize*i*2, gp.tileSize*i);
		attackLeft2 = setup("/enemy/demonlord_attack_left_2", gp.tileSize*i*2, gp.tileSize*i);
		attackRight1 = setup("/enemy/demonlord_attack_right_1", gp.tileSize*i*2, gp.tileSize*i);
		attackRight2 = setup("/enemy/demonlord_attack_right_2", gp.tileSize*i*2, gp.tileSize*i);
	}

	public void setDialogue(){
		dialogue[0][0] = "Ah, hero.";
		dialogue[0][1] = "You have finally arrived.";
        dialogue[0][2] = "It seems you have met Lao Xi the Monk...";
        dialogue[0][3] = "And Renovamen the Necromancer...";
 		dialogue[0][4] = "But what you don't understand is that they were once \nhuman.";
		dialogue[0][5] = "And needless to say, it wasn't my fault they turned out \nthis way.";
		dialogue[0][6] = "But alas, I exist because they allowed it.";
		dialogue[0][7] = "And I will end because they demand it.";
		dialogue[0][8] = "Hero...";
		dialogue[0][9] = "Key forces are at play here...";
		dialogue[0][10] = "Everything happens for a reason.";
		dialogue[0][11] = "And all of my generals... They keep the chaos at bay.";
		dialogue[0][12] = "May I ask you... Who sent you here?";
		dialogue[0][13] = "You are clearly not from this world.";
		dialogue[0][14] = "No matter. Just know that whoever did summon you here is...";
		dialogue[0][15] = "I cannot say any more. I fear it may ruin the fabric of time.";
		dialogue[0][16] = "Grant me this final battle, hero.";

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
        Progress.demonLordDefeated = true;

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

		dropItem(new Object_DemonLord_Helmet(gp));

	}
}
