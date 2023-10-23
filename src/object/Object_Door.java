package object;

import entity.Entity;
import main.GamePanel;

public class Object_Door extends Entity{

    GamePanel gp;

    public Object_Door(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Door";
        direction = "down";
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        down2 = setup("/objects/tent", gp.tileSize, gp.tileSize);
        collision = true;
        speed = 0;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    
    }

    public void interact(){

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need a key to open this door!";

    }
}
