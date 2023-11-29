package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_Bogart extends Entity{

	public static final String npcName = "Bogart";
	
    public NPC_Bogart(GamePanel gp){
        super(gp);

        name = npcName;
        direction = "down";
        speed = 4;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        dialogueSet = -1;

        getImage();
        setDialogue();

    }

    public void getImage(){

        up1 = setup("/npc/bogart_up1", gp.tileSize, gp.tileSize);
        up2 = setup ("/npc/bogart_up2", gp.tileSize, gp.tileSize);
        down1 = setup ("/npc/bogart_down1", gp.tileSize, gp.tileSize);
        down2 = setup ("/npc/bogart_down2", gp.tileSize, gp.tileSize);
        left1 = setup ("/npc/bogart_left1", gp.tileSize, gp.tileSize);
        left2 = setup ("/npc/bogart_left2", gp.tileSize, gp.tileSize);
        right1 = setup ("/npc/bogart_right1", gp.tileSize, gp.tileSize);
        right2 = setup ("/npc/bogart_right2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){
    	
        dialogue[0][0] = "...";
        dialogue[0][1] = "...";
        dialogue[0][2] = "I used to be an adventurer like you.";
        dialogue[0][3] = "Then I took an arrow in the knee...";
        
        dialogue[1][0] = "I have a sword in my house.";
        dialogue[1][1] = "I no longer have a use for it.";
        
        dialogue[2][0] = "The forest is just past this tree.";
        dialogue[2][1] = "Good luck, adventurer.";
        
        dialogue[3][0] = "So the mayor asked you for help huh?";
        dialogue[3][1] = "I know the way into the forest.";
        dialogue[3][2] = "I will guide you there, just follow me.";
        
    }

    public void setMovement(){
    	    	
    	if (doneQuest1 == true && doneQuest2 == false){
			searchPath(getGoalColumn(gp.player), getGoalRow(gp.player));
			if (gp.collisionChecker.checkPlayer(this) == true){
				dialogueSet = 2;
				this.speak();
				doneQuest2 = true;
			} 
		}
    	
		else if (doneQuest2 == true) { 
			goalReached = false;
			int goalCol = 27;
			int goalRow = 11;
			searchPath(goalCol, goalRow);

			if (goalReached == true){
				direction = "down";
				sleep = true;
			}
		}
    }

    public void speak(){
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        
        if(doneQuest1 == false) {
    		if (dialogueSet > 1) {
    			dialogueSet = 0;
    		}
    	}
        
        if (doneQuest2 == true) {
        	dialogueSet = 2;
        }   
    }
}