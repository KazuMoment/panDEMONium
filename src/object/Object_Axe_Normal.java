package object;

import entity.Entity;
import main.GamePanel;

public class Object_Axe_Normal extends Entity{

    public static final String objectName = "Axe";

    public Object_Axe_Normal (GamePanel gp){
        super (gp);
        type = type_axe;
        name = objectName;
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 30;
        attackArea.height = 30; 
        description = "[" + name + "]\nJust a normal axe.\nIt's not a woodcutter's, though.";
        price = 40;
        knockbackPower = 3;
        motion1_duration = 10;
        motion2_duration = 20;
    }
    
    
}
