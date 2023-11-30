package object;

import entity.Entity;
import main.GamePanel;

public class Object_Boat extends Entity {

    GamePanel gp;
	public static final String objectName = "Boat";
	
	public Object_Boat(GamePanel gp){

		super(gp);
		this.gp = gp;
		type = type_obstacle;
		name = objectName;
		price = 23;
		image = setup("/objects/boat",gp.tileSize,gp.tileSize);
		image2 = setup("/objects/boat1",gp.tileSize,gp.tileSize);
		down1 = image;
		collision = true;
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDialogue();
	}

	public void setDialogue(){

		dialogue[0][0] = "It's a boat. It's missing some parts though.";

	}

	public void interact(){
		startDialogue(this, 0);
	}
}