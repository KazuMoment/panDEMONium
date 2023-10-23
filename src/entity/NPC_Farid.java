package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.Object_Health_Potion_Small;

public class NPC_Farid extends Entity {

    public NPC_Farid(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        setDialogue();
        setItems();
    }

    public void getImage(){

        up1 = setup("/npc/merchant_down1", gp.tileSize, gp.tileSize);
        up2 = setup ("/npc/merchant_down2", gp.tileSize, gp.tileSize);
        down1 = setup ("/npc/merchant_down1", gp.tileSize, gp.tileSize);
        down2 = setup ("/npc/merchant_down2", gp.tileSize, gp.tileSize);
        left1 = setup ("/npc/merchant_down1", gp.tileSize, gp.tileSize);
        left2 = setup ("/npc/merchant_down2", gp.tileSize, gp.tileSize);
        right1 = setup ("/npc/merchant_down1", gp.tileSize, gp.tileSize);
        right2 = setup ("/npc/merchant_down2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogue[0] = "Ah. Hello, stranger.";
        dialogue[1] = "Before we do some business, it is appropriate \nthat I introduce myself";
        dialogue[2] = "I am Farid, a humble merchant.";
        dialogue[3] = "Ah. These? They are my goggles!";
        dialogue[4] = "Huh? You were talking about my horns?\nThey're nothing special, I tell you.";
        dialogue[5] = "I am a demon, so what? Am I not \nallowed to do business?";
        dialogue[6] = "So? Are we doing business or what?";
        
    }

    public void setItems(){
        inventory.add(new Object_Health_Potion_Small(gp));
        
    }

    public void speak(){
        if (dialogue[dialogueIndex] != null){
            super.speak();
        }
        if (dialogue[dialogueIndex] == null){
            gp.ui.currentDialogue = dialogue[6];
            gp.gameState = gp.shopState;
            gp.ui.npc = this;
        }
    }
    
}
