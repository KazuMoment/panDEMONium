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
		for (int i = 0; i < gp.npc[1].length; i++){
			if (gp.npc[gp.currentMap][i].name.equals(NPC_Piyaye.npcName)){
				gp.npc[gp.currentMap][i].onPath = true;
				gp.player.addNpcQuestEvent(NPC_Piyaye.npcName, QuestEvent.PIYAYE_PADDLE_GIVEN);
				break;
			}
		}

		for (int i = 0; i < gp.obj[1].length; i++){
			if (gp.obj[gp.currentMap][i] != null && 
				gp.obj[gp.currentMap][i].name == Object_Boat.objectName &&
				gp.obj[gp.currentMap][i].collision == true){
					gp.obj[gp.currentMap][i].collision = false;
					gp.obj[gp.currentMap][i].opened = true;
					gp.obj[gp.currentMap][i].down1 = gp.obj[gp.currentMap][i].image2;
					break;
			}
		}
		
	}

    
}
