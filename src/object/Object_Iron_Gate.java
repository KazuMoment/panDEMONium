package object;

import entity.Entity;
import main.GamePanel;

public class Object_Iron_Gate extends Entity{

    GamePanel gp;
    public static final String objectName = "Iron Door";

    public Object_Iron_Gate(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objectName;
        direction = "down";
        down1 = setup("/objects/leverdoor", gp.tileSize, gp.tileSize);
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

        dialogue[0][0] = "It's a barred gate. Maybe it needs a mechanism?";

    }

    public void interact(){

        startDialogue(this, 0);
    
    }
}
