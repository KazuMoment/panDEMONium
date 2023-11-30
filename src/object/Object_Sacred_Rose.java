package object;
import entity.Entity;
import main.GamePanel;

public class Object_Sacred_Rose extends Entity{
	GamePanel gp;
	public static final String objectName = "Sacred Rose";
    
	public Object_Sacred_Rose(GamePanel gp){
		super(gp);
		type = type_consumable;
		this.gp = gp; 
		name = objectName;
		price = 23;
		down1 = setup("/objects/sacred_rose",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nA mystical rose, that gives life.";
	
		setDialogue();
	}

	public void setDialogue(){
		dialogue[0][0] = "Give this to Mayor Mounsi!";
	}

	public boolean use(Entity entity){

		startDialogue(this, 0);

		return false;
	}
}