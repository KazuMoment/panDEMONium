package object;

import entity.Entity;
import main.GamePanel;

public class Object_Shield_Wood extends Entity {
    
    public Object_Shield_Wood(GamePanel gp){
        super(gp);
        type = type_shield;
        name = "Tinvaak Shield";
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nA wooden shield from the town\nof Tinvaak. It has a weird art.";
        price = 30;
    }
    
}
