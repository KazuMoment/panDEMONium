package object;

import entity.Entity;
import main.GamePanel;

public class Object_Bonfire extends Entity{

    GamePanel gp;
    public static final String objectName = "Bonfire";

    public Object_Bonfire(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objectName;
        image = setup("/objects/bonfire_off", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/bonfire_on", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue(){ 

        dialogue[0][0] = "Taking a rest might be wise.";
        dialogue[0][1] = "Would you like to pass the time and save?";

        dialogue[1][0] = "Would you like to take a rest?";

        dialogue[2][0] = "Remember to take a rest!";

    }

    public void interact(){

        if (opened == false){
            gp.playSoundEffect(11);
            startDialogue(this, 0);
            gp.gameState = gp.saveState;
            down1 = image2;
            opened = true;
        }

        else{
            startDialogue(this, 1);
            gp.gameState = gp.saveState;
        }
    }
    
}

