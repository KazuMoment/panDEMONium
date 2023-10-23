package object;

import entity.Entity;
import main.GamePanel;

public class Object_Shield_Metal extends Entity{

    public Object_Shield_Metal(GamePanel gp){
        super(gp);
        type = type_shield;
        name = "Victoria Shield";
        down1 = setup("/objects/shield_metal", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA wooden shield from the city\nof Victoria. It has a star symbol.";
        price = 75;
    }
    
}
