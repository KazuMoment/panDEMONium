package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.Object_Iron_Gate;

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

		dialogue[2][0] = "Another village needs to be saved, help them too...";
		dialogue[2][1] = "Come follow me.";

		dialogue[3][0] = "Go underground!";
		
		
	}

	public void setMovement(){
		if (introDone == false){
			searchPath(getGoalColumn(gp.player), getGoalRow(gp.player));
			if (gp.collisionChecker.checkPlayer(this) == true){
				this.speak();
			} 
		}
		else if (introDone == true && doneQuest1 == false){ 
			goalReached = false;
			int goalCol = 15;
			int goalRow = 22;
			searchPath(goalCol, goalRow);

			if (goalReached == true){
				direction = "down";
				sleep = true;
			}
		}

		else if (doneQuest1 == true && doneQuest2 == false){
			dialogueSet = 2;
			searchPath(getGoalColumn(gp.player), getGoalRow(gp.player));
			if (gp.collisionChecker.checkPlayer(this) == true){
				this.speak();
				for (int i = 0; i < gp.obj[1].length; i++){
					if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(Object_Iron_Gate.objectName)){
						gp.obj[gp.currentMap][i] = null;
					}
				}
				doneQuest2 = true;
			}
		}

		else if (doneQuest2 == true){
			int goalCol = 19;
			int goalRow = 8;
			goalReached = false;
			searchPath(goalCol, goalRow);

			if (goalReached == true){
				sleep = true;
				dialogueSet = 3;
			}
		}
	}
	
	public void speak(){
		facePlayer();
		startDialogue(this, dialogueSet);
		
		if (introDone == true && doneQuest1 == false) {
			dialogueSet = 1;
		}
	}
}
