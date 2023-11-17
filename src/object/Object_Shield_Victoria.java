package object;

import entity.Entity;
import main.GamePanel;

public class Object_Shield_Victoria extends Entity{

    public static final String objectName = "Victoria Shield";

    public Object_Shield_Victoria(GamePanel gp){
        super(gp);
        type = type_shield;
        name = objectName;
        down1 = setup("/objects/shield_metal", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA wooden shield from the city\nof Victoria. It has a star symbol.";
        price = 75;
    }
    
}
