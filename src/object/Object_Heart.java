package object;

import entity.Entity;
import main.GamePanel;

public class Object_Heart extends Entity{

    GamePanel gp;

    public Object_Heart(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickuponly;
        name = "Heart";
        value = 2;
        down1 = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/heart_empty",gp.tileSize, gp.tileSize);
 
    }

    public boolean use (Entity entity){
        gp.playSoundEffect(3);
        gp.ui.addMessage("Healed by " + value + " HP!");
        entity.HP += value;
        return true;
    }
    
}
