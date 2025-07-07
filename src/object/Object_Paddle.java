package object;

import entity.Entity;
import entity.NPC_Piyaye;
import main.GamePanel;
import main.QuestEvent;

public class Object_Paddle extends Entity{
    GamePanel gp;
	public static final String objectName = "Piyaye's Paddle";

	public Object_Paddle(GamePanel gp){
		super(gp);
		type = type_consumable;
		this.gp = gp; 
		name = objectName;
		price = 23;
		down1 = setup("/objects/paddle", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nPiyaye's paddle.\nYou should give it to him.";
	
		setDialogue();
	}

	public void setDialogue(){
		dialogue[0][0] = "You got the paddle? Perfect! Let's \ngo to the boat right away!";
		dialogue[0][1] = "Come follow me.";

		dialogue[1][0] = "Maybe someone needs this paddle?\nI should find them and give it to them.";
	}

	public boolean use(Entity entity){
		
		int objIndex = getDetected(entity, gp.npc, NPC_Piyaye.npcName);
		
		if (objIndex != 999){ 	
			startDialogue(this, 0);
			questOver();
			gp.playSoundEffect(4);
		
			return true;
		}

		else{
			startDialogue(this, 1);
			return false;
		}
	}
	
	public void questOver(){
		triggerNPCPathAndQuest(NPC_Piyaye.npcName, QuestEvent.PIYAYE_PADDLE_GIVEN_COMPLETED);
		changeHomeCoordinates(NPC_Piyaye.npcName, 33, 38);
		unlockTransportation(Object_Boat.objectName);
	}
}
