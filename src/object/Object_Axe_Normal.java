package object;

import entity.Entity;
import main.GamePanel;

public class Object_Axe_Normal extends Entity{

    public Object_Axe_Normal (GamePanel gp){
        super (gp);
        type = type_axe;
        name = "Axe";
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30; 
        description = "[" + name + "]\nJust a normal axe.\nIt's not a woodcutter's, though.";
        price = 40;
        knockbackPower = 3;
        motion1_duration = 10;
        motion2_duration = 20;
    }
    
    
}
