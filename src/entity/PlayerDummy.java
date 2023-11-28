package entity;

import main.GamePanel;

public class PlayerDummy extends Entity{

    public static final String npcName = "Dummy";

    public PlayerDummy(GamePanel gp){
        super(gp);

        name = npcName;
        getImage();
    }

    public void getImage(){
        up1 = setup("/player/player_girl_up1", gp.tileSize, gp.tileSize);
        up2 = setup ("/player/player_girl_up2", gp.tileSize, gp.tileSize);
        down1 = setup ("/player/player_girl_down1", gp.tileSize, gp.tileSize);
        down2 = setup ("/player/player_girl_down2", gp.tileSize, gp.tileSize);
        left1 = setup ("/player/player_girl_left1", gp.tileSize, gp.tileSize);
        left2 = setup ("/player/player_girl_left2", gp.tileSize, gp.tileSize);
        right1 = setup ("/player/player_girl_right1", gp.tileSize, gp.tileSize);
        right2 = setup ("/player/player_girl_right2", gp.tileSize, gp.tileSize);
    }

    
}
