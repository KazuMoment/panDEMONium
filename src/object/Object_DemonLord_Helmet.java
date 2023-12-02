package object;

import entity.Entity;
import main.GamePanel;

public class Object_DemonLord_Helmet extends Entity {

    GamePanel gp;
    public static final String objName = "Demon Lord Helmet";

    public Object_DemonLord_Helmet(GamePanel gp){
        super(gp);

        this.gp = gp;

        type = type_pickuponly;
        name = objName;
        down1 = setup("/objects/Demon Lord Helm", gp.tileSize, gp.tileSize);

        setDialogue();

    }

    public void setDialogue(){

        dialogue[0][0] = "It's the Demon Lord's helmet.";
        dialogue[0][1] = "The horns are attached to it...";
        dialogue[0][2] = "You feel its compelling presence to wear it...";

    }

    public boolean use (Entity entity){

        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNumber = gp.csManager.ending;
        
        return true;    
    }

    
}
