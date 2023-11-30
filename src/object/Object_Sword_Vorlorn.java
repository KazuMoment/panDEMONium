package object;

import entity.Entity;
import main.GamePanel;

public class Object_Sword_Vorlorn extends Entity{

    public static final String objectName = "Vorlorn Sword";

    public Object_Sword_Vorlorn (GamePanel gp){
        super(gp);
        type = type_sword;
        name =  objectName;
        down1 = setup("/objects/vorlorn_sword", gp.tileSize, gp.tileSize);
        attackValue = 3;
        attackArea.width = 36;
        attackArea.height = 37; 
        description = "[" + name + "]\nThe sword from the town of \nVorlorn. It looks sharp.";  
        price = 48;
        knockbackPower = 9;
        motion1_duration = 5;
        motion2_duration = 25;
    }
    
}
