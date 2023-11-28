package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_BJ extends Entity {

	public static final String npcName = "BJ";
		public NPC_BJ(GamePanel gp){
			super(gp);
            
			name = npcName;
			direction = "down";
			speed = 0;
			solidArea = new Rectangle(8,16,32,32);
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
			
			getImage();
			setDialogue();
		}
        
		public void getImage(){

			up1 = setup("/npc/child1_down_1",gp.tileSize,gp.tileSize);
			up2 = setup("/npc/child1_down_2",gp.tileSize,gp.tileSize);
			down1 = setup("/npc/child1_down_1",gp.tileSize,gp.tileSize);
			down2 = setup("/npc/child1_down_2",gp.tileSize,gp.tileSize);
			left1 = setup("/npc/child1_down_1",gp.tileSize,gp.tileSize);
			left2 = setup("/npc/child1_down_2",gp.tileSize,gp.tileSize);
			right1 = setup("/npc/child1_down_1",gp.tileSize,gp.tileSize);
			right2 = setup("/npc/child1_down_2",gp.tileSize,gp.tileSize);
			
		}

		public void setDialogue(){
			dialogue[0][0]= "H-Hi. I'm sorry. I don't talk to strangers.";
			dialogue[0][1]= "Mommy told me.";
			
			dialogue[1][0]= "Go away, please...";
			
		}

		public void setMovement(){
			
		}

		public void speak(){
			facePlayer();
			startDialogue(this, dialogueSet);
            dialogueSet++;
			
			if (dialogue[dialogueSet][0] == null){
				dialogueSet = 0;
			}
		}
}
