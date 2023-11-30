package object;

import entity.Entity;
import main.GamePanel;

public class Object_Sword_Tinvaak extends Entity{

    public static final String objectName = "Tinvaak Sword";

    public Object_Sword_Tinvaak (GamePanel gp){
        super(gp);
        type = type_sword;
        name =  objectName;
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 37; 
        description = "[" + name + "]\nThe sword from the town of \nTinvaak. It looks weird.";  
        price = 34;
        knockbackPower = 7;
        motion1_duration = 5;
        motion2_duration = 25;
    }
    
}
