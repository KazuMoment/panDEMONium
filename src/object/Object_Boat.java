package object;

import entity.Entity;
import entity.NPC_Piyaye;
import main.GamePanel;

public class Object_Boat extends Entity {

    GamePanel gp;
	public static final String objectName = "Boat";
	
	public Object_Boat(GamePanel gp){

		super(gp);
		this.gp = gp;
		type = type_obstacle;
		name = objectName;
		price = 23;
		image = setup("/objects/boat",gp.tileSize,gp.tileSize);
		image2 = setup("/objects/boat1",gp.tileSize,gp.tileSize);
		down1 = image;
		collision = true;
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDialogue();
	}

	public void setDialogue(){

		dialogue[0][0] = "It's a boat. It's missing some parts though.";
		
		dialogue[1][0] = "Helped Piyaye fix the boat!";
		dialogue[1][1] = "Now you can hop on the boat!";

	}

	public void interact(){
	    for (int i = 0; i < gp.npc[1].length; i++) {
			if (gp.npc[gp.currentMap][i].name.equals(NPC_Piyaye.npcName)){
				if (gp.npc[gp.currentMap][i].doneQuest1 == false) {
					startDialogue(this, 0);
					break;
				} 
				else if (gp.npc[gp.currentMap][i].doneQuest1 == true){
					gp.playSoundEffect(4);
					opened = true;
					down1 = image2;
					collision = false;
					startDialogue(this, 1);
					break;
				}
			}        
	    }
	}
}