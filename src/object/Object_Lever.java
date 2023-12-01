package object;

import entity.Entity;
import main.GamePanel;

public class Object_Lever extends Entity{

    GamePanel gp;
    public static final String objectName = "Lever";

    public Object_Lever(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objectName;
        image = setup("/objects/lever_close", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/lever_open", gp.tileSize, gp.tileSize);
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

        dialogue[0][0] = "The lever moves, opening up an entrance somewhere.";
        dialogue[1][0] = "It won't budge.";

    }

    public void interact(){

        if (opened == false){
            gp.playSoundEffect(4);
            startDialogue(this, 0);
            down1 = image2;
            opened = true;
            for (int i = 0; i < gp.obj[1].length; i++){
                if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(Object_Iron_Gate.objectName)){
                    gp.obj[gp.currentMap][i] = null;
                }
            }
            gp.playSoundEffect(24);
        }

        else{
            startDialogue(this, 1);
        }
    }
    
}
