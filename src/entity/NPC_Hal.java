package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_Hal extends Entity {
    
	public static final String npcName = "Hal";

    public NPC_Hal(GamePanel gp){
        super(gp);

        name = npcName;
        direction = "down";
        speed = 0;
        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        dialogueSet = -1;
        
        getImage();
        setDialogue();
    }

    public void getImage(){

        up1 = setup("/npc/HO2_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/HO2_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/HO2_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/HO2_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/HO2_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/HO2_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/HO2_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/HO2_down_2",gp.tileSize,gp.tileSize);
        
    }
    public void setDialogue(){
        dialogue[0][0] = "Monsters are scary and the dungeon is dark...";
        dialogue[0][1] = "But there is a lantern somewhere inside the forest.";
        dialogue[0][2] = "I accidentally dropped it when I was\nrunning away from the monsters...";
        dialogue[0][3] = "The dungeon is a scary place, it is meant for strong people.";
        
        dialogue[1][0] = "You can equip the lantern if you ever \nget it.";
        dialogue[1][1] = "Just access the inventory and select it.";
        
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
