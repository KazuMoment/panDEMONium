package object;

import entity.Entity;
import main.GamePanel;

public class Object_WitheredTree extends Entity{

    GamePanel gp;
    public static final String objectName = "Withered Tree";

    public Object_WitheredTree(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objectName;
        direction = "down";
        down1 = setup("/objects/witheredtreeo", gp.tileSize, gp.tileSize);
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

        dialogue[0][0] = "It's a tree.";

    }

    public void interact(){

        startDialogue(this, 0);
    
    }
    
}
