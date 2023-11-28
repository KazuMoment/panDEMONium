package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_JB extends Entity {
    
	public static final String npcName = "JB";
    
    public NPC_JB(GamePanel gp){
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

        up1 = setup("/npc/child2_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/child2_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/child2_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/child2_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/child2_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/child2_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/child2_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/child2_down_2",gp.tileSize,gp.tileSize);
        
    }

    public void setDialogue(){
        dialogue[0][0]= "Hello, I am JB.";
        dialogue[0][1]= "Are you an adventurer?";
        dialogue[0][2]= "I think being an adventurer is cool...";
        
        dialogue[1][0]= "Be safe, Mr. Adventurer!";
        
    }
    
    public void setAction(){
        
    }

    public void speak() {
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        
        if (dialogue[dialogueSet][0] == null){
            dialogueSet--;
        }
        
    }
}
