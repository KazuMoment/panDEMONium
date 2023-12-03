package entity;

import java.awt.Rectangle;

import data.Progress;
import enemy.Enemy_GreenSlime;
import main.GamePanel;

public class NPC_Reul extends Entity{

    public static final String npcName = "Reul";

    public NPC_Reul(GamePanel gp){
        super(gp);

        direction = "left";
        defaultSpeed = 3;
        speed = defaultSpeed;
        name = npcName;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        getImage();

    }

    public void getImage(){

        up1 = setup("/npc/Reul_up1", gp.tileSize, gp.tileSize);
        up2 = setup ("/npc/Reul_up2", gp.tileSize, gp.tileSize);
        down1 = setup ("/npc/Reul_down1", gp.tileSize, gp.tileSize);
        down2 = setup ("/npc/Reul_down2", gp.tileSize, gp.tileSize);
        left1 = setup ("/npc/Reul_left1", gp.tileSize, gp.tileSize);
        left2 = setup ("/npc/Reul_left2", gp.tileSize, gp.tileSize);
        right1 = setup ("/npc/Reul_right1", gp.tileSize, gp.tileSize);
        right2 = setup ("/npc/Reul_right2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogue[0][0] = "You gave me a fright, lad.";
        dialogue[0][1] = "You just suddenly appeared out of thin air!";
        dialogue[0][2] = "Huh? You don't know where you are?";
        dialogue[0][3] = "This is the land of Nurvia! The land of peace and tranquility.";
        dialogue[0][4] = "But we have a problem.";
        dialogue[0][5] = "The Demon King has taken over the land!";
        dialogue[0][6] = "We are hopeless in his tyranny!";
        dialogue[0][7] = "Listen closely, lad. My name is Reul.";
        dialogue[0][8] = "I have summoned you here for a purpose.";
        dialogue[0][9] = "It will be through only your power to defeat the Demon King!";
        dialogue[0][10] = "But do not worry. I will guide you every step of the way.";
        dialogue[0][11] = "Now, how do we get out of this forest...?";
        dialogue[0][12] = "Maybe pick up that axe over there?";

        dialogue[1][0] = "Oh, you got the axe. Good. Now, just cut the tree over there!";
        dialogue[1][1] = "It's the tree over there with a different shape!";
        dialogue[1][2] = "Just get close there and press E!";
        dialogue[1][3] = "You can interact with objects too by pressing ENTER!";

        dialogue[2][0] = "You cut the tree! Good job!";
        dialogue[2][1] = "Now, follow me.";
        
        dialogue[3][0] = "I will be staying here for a while.";
        dialogue[3][1] = "There are many slimes around.";
        dialogue[3][2] = "Can you take care of them for me?";

        dialogue[4][0] = "Thank you for taking care of the slimes.";
        dialogue[4][1] = "I'll just stay here for a bit. Gather my bearings.";
        dialogue[4][2] = "Anyway, here's a potion! For getting rid of the slimes!";

        dialogue[5][0] = "Received a Small Health Potion from Reul!";

        dialogue[6][0] = "I can't give you it yet. Your inventory is full.";

        dialogue[7][0] = "As I thought, summoning you here was the best idea.";
        dialogue[7][1] = "Do not believe what the Demon Lord said.\nYou should be thankful to be here.";

    }

    public void setReward(Entity reward){
        this.reward = reward;
        setDialogue();
    }

    public void setMovement(){

        if (introDone == false){
            onPath = true;
            searchPath(getGoalColumn(gp.player), getGoalRow(gp.player));
            if (gp.collisionChecker.checkPlayer(this) == true){
                this.speak();
                standby = true;
            } 
        }

        else if (gp.obj[0][0] == null && pickedQuestObject == false){
            goalReached = false;
            standby = false;
            speed = defaultSpeed;
            dialogueSet = 1;
            searchPath(getGoalColumn(gp.player), getGoalRow(gp.player));
            if (gp.collisionChecker.checkPlayer(this) == true){
                this.speak();
                pickedQuestObject = true;
                standby = true;
            }      
        }

        else if (gp.iTile[0][0].HP == 0 && doneQuest1 == false){
            goalReached = false;
            standby = false;
            speed = defaultSpeed;
            dialogueSet = 2;
            startDialogue(this, dialogueSet);
            doneQuest1 = true;
        }

        else if (doneQuest1 == true && doneQuest2 == false){
            
            int goalColumn = 22;
            int goalRow = 43;

            searchPath(goalColumn, goalRow);

            if (goalReached == true){
                standby = true;
            }
            
            boolean allSlimesKilled = true;

            for (int i = 0; i < gp.enemy[1].length; i++){
                if (gp.enemy[gp.currentMap][i] != null && gp.enemy[gp.currentMap][i].name.equals(Enemy_GreenSlime.enemyName)){
                    allSlimesKilled = false;
                }
            }

            if (allSlimesKilled == true){
                doneQuest2 = true;
                standby = false;
                sleep = true;
            }
        }

        if (standby == true){
            if (pickedQuestObject == true){
                direction = "left";
                speed = 0;
            }
            else{
                direction = "right";
                speed = 0;
            }
        }
        
    }

    public void speak(){
        facePlayer();
        startDialogue(this, dialogueSet);

        if (doneQuest1 == true && doneQuest2 == false){
            dialogueSet = 2;
            dialogueSet++;
            if (dialogue[dialogueSet][0] == null){
                dialogueSet--;
            }
        }

        if (doneQuest2 == true){
            dialogueSet = 3;
            dialogueSet++;
            if (receivedReward == false){
                    if (gp.player.canObtainItem(reward) == false){
                        startDialogue(this, 6);
                    }
                    else{
                        gp.playSoundEffect(2);
                        startDialogue(this, 5);
                        receivedReward = true;
                    }
                }
            else if (dialogue[dialogueSet][0] == null){
                dialogueSet--;
            }
        }

        if (Progress.completedGame == true){
            
        }

    }
    
}
