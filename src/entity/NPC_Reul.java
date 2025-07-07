package entity;

import java.awt.Rectangle;
import java.awt.desktop.QuitStrategy;

import data.Progress;
import enemy.Enemy_GreenSlime;
import main.GamePanel;
import main.QuestEvent;
import main.QuestListener;
import object.Object_Axe_Normal;
import tile_interactive.IT_DryTree;

public class NPC_Reul extends Entity implements QuestListener{

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

        this.homeCol = 22;
        this.homeRow = 43;

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

        dialogue[0][0] = "You gave me a fright, lass.";
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

        dialogue[1][0] = "Now, how do we get out of this forest...?";
        dialogue[1][1] = "Maybe pick up that axe over there?";

        dialogue[2][0] = "Oh, you got the axe. Good. Now, just cut the tree over there!";
        dialogue[2][1] = "It's the tree over there with a different shape!";
        dialogue[2][2] = "Press T to open your inventory, \nselect the axe with ENTER, and press E!";
        dialogue[2][3] = "You can interact with objects and people too by pressing ENTER!";

        dialogue[3][0] = "You cut the tree! Good job!";
        dialogue[3][1] = "When people ask you to find stuff,";
        dialogue[3][2] = "Select the item in your inventory and press ENTER!";
        dialogue[3][3] = "Usually, you don't need to do that.";
        dialogue[3][3] = "Now, follow me.";
        
        dialogue[4][0] = "I will be staying here for a while.";
        dialogue[4][1] = "There are many slimes around.";
        dialogue[4][2] = "Can you take care of them for me?";

        dialogue[5][0] = "Thank you for taking care of the slimes.";
        dialogue[5][1] = "I'll just stay here for a bit. Gather my bearings.";
        dialogue[5][2] = "Anyway, here's a potion! For getting rid of the slimes!";

        dialogue[6][0] = "Received a Small Health Potion from Reul!";

        dialogue[7][0] = "I can't give you it yet. Your inventory is full.";

        dialogue[8][0] = "As I thought, summoning you here was the best idea.";
        dialogue[8][1] = "Do not believe what the Demon Lord said.\nYou should be thankful to be here.";

    }

    public void setReward(Entity reward){
        this.reward = reward;
        setDialogue();
    }

    public void setMovement(){

        if (!gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.INTRO_DONE)) {
            handleIntroMilestone();
            gp.player.addNpcQuestEvent(NPC_Reul.npcName, QuestEvent.PICK_QUEST_OBJECT_ACCEPTED);
        }

        else if (gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.PICK_QUEST_OBJECT_COMPLETED) 
        && !gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REUL_TREE_CUT_ACCEPTED)) {
            handleQuestMilestone(NPC_Reul.npcName, QuestEvent.REUL_TREE_CUT_ACCEPTED, true);
        }

        else if (gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REUL_TREE_CUT_COMPLETED)
        && !gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REUL_KILL_SLIMES_ACCEPTED)){
            handleQuestMilestoneSpeak(NPC_Reul.npcName, QuestEvent.REUL_KILL_SLIMES_ACCEPTED, false);
        }

        else if (gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REUL_KILL_SLIMES_ACCEPTED)
        && !gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REUL_MOVED)) {
            moveAndQuestAdvance(22, 43, NPC_Reul.npcName, QuestEvent.REUL_MOVED);
        }

        else if (gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REUL_MOVED)){
            roam(homeCol, homeRow);
        }   


        if (standby == true){
            if (gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.PICK_QUEST_OBJECT_COMPLETED) == true){
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
        
        // Set dialogueSet based on player's quest progress
        if (!gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.INTRO_DONE)) {
            dialogueSet = 0;
        } else if (!gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.PICK_QUEST_OBJECT_COMPLETED)){
            dialogueSet = 1;
        } else if (!gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REUL_TREE_CUT_COMPLETED)) {
            dialogueSet = 2;
        } else if (!gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REUL_KILL_SLIMES_ACCEPTED)) {
            dialogueSet = 3;
        } else if (!gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REUL_KILL_SLIMES_COMPLETED)) {
            dialogueSet = 4;
        } else if (gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REUL_KILL_SLIMES_COMPLETED) && !gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REWARD_RECEIVED)){
            if (gp.player.canObtainItem(reward)) {
                gp.playSoundEffect(2);
                startDialogue(this, 6);
                gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REWARD_RECEIVED);
            } else {
                startDialogue(this, 7);
            }
        } else if (gp.player.hasNpcQuestEvent(NPC_Reul.npcName, QuestEvent.REWARD_RECEIVED)) {
            dialogueSet = 5;
        } else if (Progress.completedGame == true) {
            dialogueSet = 8;
        }

        
    }
    
}
