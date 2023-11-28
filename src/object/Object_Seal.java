package object;

import entity.Entity;
import main.GamePanel;

public class Object_Seal extends Entity{
	GamePanel gp;
	public static final String objectName = "Seal";
    
	public Object_Seal(GamePanel gp){
		super(gp);
		type = type_consumable;
		this.gp = gp; 
		name = objectName;
		price = 23;
		down1 = setup("/objects/seal",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nA seal that is emitting a really strange aura.";
	
		setDialogue();
	}

	public void setDialogue(){
		dialogue[0][0] = "Give this to Mayor Ficher!";
	}

	public boolean use(Entity entity){

		startDialogue(this, 1);

		return false;
	}
}
