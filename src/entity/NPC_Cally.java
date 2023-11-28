package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_Cally extends Entity {

	public static final String npcName = "Cally";

	public NPC_Cally(GamePanel gp){
		super(gp);
		
		name = npcName;
		direction = "down";
		defaultSpeed = 3;
		speed = defaultSpeed;
		solidArea = new Rectangle(8,16,32,32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
			
		getImage();
		setDialogue();
	}

	public void getImage(){

		up1 = setup("/npc/secretary_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/secretary_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/npc/secretary_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/secretary_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/npc/secretary_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/secretary_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/npc/secretary_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/secretary_right_2",gp.tileSize,gp.tileSize);
		
	}

	public void setDialogue(){
		dialogue[0][0]= "Hello! I am the secratary of the Mayor!\nMy name is Cally.";
		dialogue[0][1]= "We are currently in need of someone to investigate a place...";
		dialogue[0][2]= "Follow me, I will lead you to the Mayor's Office.";
		
		dialogue[1][0] = "Come inside.";
		
		
	}

	public void setMovement(){
		int goalCol = 16;
		int goalRow = 22;

		if (onPath == true){
			searchPath(goalCol, goalRow);
		}

		else {
			if (standby == true){
				direction = "down";
				speed = 0;
			}
		}
	}

	public void speak(){
		facePlayer();
		startDialogue(this, dialogueSet);
		onPath = true;
		standby = false;
		speed = defaultSpeed;
		
		if (introDone == true) {
			dialogueSet = 1;
		}
	}
}
