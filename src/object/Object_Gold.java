package object;

import entity.Entity;
import main.GamePanel;

public class Object_Gold extends Entity {

    GamePanel gp;
    public static final String objectName = "Gold";

    public Object_Gold (GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickuponly;
        name = objectName;
        value = 1;
        down1 = setup("/objects/gold", gp.tileSize, gp.tileSize);
    }

    public boolean use (Entity entity){
        gp.playSoundEffect(2);
        gp.ui.addMessage("Picked up " + value + "Gold!");
        gp.player.gold += value;
        return true;
    }
    
}
