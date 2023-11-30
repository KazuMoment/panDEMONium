package main;

import java.awt.Graphics2D;

import enemy.Enemy_DemonLord;
import enemy.Enemy_DemonMonk;
import enemy.Enemy_Necromancer;
import enemy.Enemy_Skeleton;
import entity.PlayerDummy;
import object.Object_Iron_Gate;
import object.Object_WitheredTree;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNumber;
    public int scenePhase;

    // Scene Number
    public final int NA = 0;
    public final int demonMonk = 1;
    public final int necromancer = 2;
    public final int demonLord = 3;


    public CutsceneManager(GamePanel gp){
        this.gp = gp;
    }
    
    public void draw(Graphics2D g2){
        this.g2 = g2;

        switch(sceneNumber){
            case demonMonk: scene_demonMonk(); break;
            case necromancer: scene_necromancer(); break;
            case demonLord: scene_demonLord(); break;
            
        }

    }

    public void scene_demonMonk(){

        if (scenePhase == 0){

            gp.bossBattleOn = true;
            gp.stopMusic();

            // Shut Iron Door
            for (int i = 0; i < gp.obj[1].length; i++){
                if (gp.obj[gp.currentMap][i] == null){
                    gp.obj[gp.currentMap][i] = new Object_Iron_Gate(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 36;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 22;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSoundEffect(24);
                    break;
                }
            }

            // Set Player Dummy in place of Player
            for (int i = 0; i < gp.npc[1].length; i++){
                if (gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }

            gp.player.drawing = false;
            scenePhase++;
        }

        if (scenePhase == 1){

            // Camera Control
            gp.player.worldX -= 2;

            if (gp.player.worldX < gp.tileSize * 30){
                scenePhase++;
            }
        }

        if (scenePhase == 2){

            // Camera Control
            gp.player.worldY += 2;

            if (gp.player.worldY > gp.tileSize * 28){
                scenePhase++;
            }
        }

        if (scenePhase == 3){
            
            // Search Boss
            for (int i = 0; i < gp.enemy[1].length; i++){
                if (gp.enemy[gp.currentMap][i] != null &&
                    gp.enemy[gp.currentMap][i].name.equals(Enemy_DemonMonk.enemyName)){
                        gp.enemy[gp.currentMap][i].sleep = false;
                        gp.ui.npc = gp.enemy[gp.currentMap][i];
                        scenePhase++;
                        break;
                    }
            }
        }

        if (scenePhase == 4){
            // Boss Speaks
            gp.ui.drawDialogueScreen();
        }

        if (scenePhase == 5){
            // Return Camera to Player

            // Search Dummy 
            for (int i = 0; i < gp.npc[1].length; i++){
                if(gp.npc[gp.currentMap][i] != null &&
                    gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){
                        // Restore Player Position
                        gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                        gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                        // Delete Dummy
                        gp.npc[gp.currentMap][i] = null;
                        break;
                    }
            }

            gp.player.drawing = true;
            
            // Reset
            sceneNumber = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;

            // Change Music
            gp.playMusic(20);
        }
    }

    public void scene_necromancer(){

        if (scenePhase == 0){

            gp.bossBattleOn = true;
            gp.stopMusic();

            // Shut Iron Door
            for (int i = 0; i < gp.obj[1].length; i++){
                if (gp.obj[gp.currentMap][i] == null){
                    gp.obj[gp.currentMap][i] = new Object_WitheredTree(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 22;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 15;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSoundEffect(24);
                    break;
                }
            }

            // Summon Skeletons   
            for (int i = 0; i < gp.enemy[1].length; i++){
                if (gp.enemy[gp.currentMap][i] == null){
                    gp.enemy[gp.currentMap][i] = new Enemy_Skeleton(gp);
                    gp.enemy[gp.currentMap][i].worldX = gp.tileSize * 16;
                    gp.enemy[gp.currentMap][i].worldY = gp.tileSize * 9;
                    gp.enemy[gp.currentMap][i].temp = true;
                    break;
                }
            }

            for (int i = 0; i < gp.enemy[1].length; i++){
                if (gp.enemy[gp.currentMap][i] == null){
                    gp.enemy[gp.currentMap][i] = new Enemy_Skeleton(gp);
                    gp.enemy[gp.currentMap][i].worldX = gp.tileSize * 19;
                    gp.enemy[gp.currentMap][i].worldY = gp.tileSize * 12;
                    gp.enemy[gp.currentMap][i].temp = true;
                    gp.enemy[gp.currentMap][i].direction = "left";
                    break;
                }
            }

            for (int i = 0; i < gp.enemy[1].length; i++){
                if (gp.enemy[gp.currentMap][i] == null){
                    gp.enemy[gp.currentMap][i] = new Enemy_Skeleton(gp);
                    gp.enemy[gp.currentMap][i].worldX = gp.tileSize * 14;
                    gp.enemy[gp.currentMap][i].worldY = gp.tileSize * 17;
                    gp.enemy[gp.currentMap][i].temp = true;
                    break;
                }
            }

            for (int i = 0; i < gp.enemy[1].length; i++){
                if (gp.enemy[gp.currentMap][i] == null){
                    gp.enemy[gp.currentMap][i] = new Enemy_Skeleton(gp);
                    gp.enemy[gp.currentMap][i].worldX = gp.tileSize * 11;
                    gp.enemy[gp.currentMap][i].worldY = gp.tileSize * 13;
                    gp.enemy[gp.currentMap][i].temp = true;
                    break;
                }
            }
        
            

            // Set Player Dummy in place of Player
            for (int i = 0; i < gp.npc[1].length; i++){
                if (gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }

            gp.player.drawing = false;
            scenePhase++;
        }

        if (scenePhase == 1){
            // Camera Control
            gp.player.worldX -= 2;

            if (gp.player.worldX < gp.tileSize * 16){
                scenePhase++;
            }
        }

        if (scenePhase == 2){
            gp.player.worldY -= 2;

            if (gp.player.worldY < gp.tileSize *13){
                scenePhase++;
            }
        }

        if (scenePhase == 3){
            
            // Search Boss
            for (int i = 0; i < gp.enemy[1].length; i++){
                if (gp.enemy[gp.currentMap][i] != null &&
                    gp.enemy[gp.currentMap][i].name.equals(Enemy_Necromancer.enemyName)){
                        gp.enemy[gp.currentMap][i].sleep = false;
                        gp.ui.npc = gp.enemy[gp.currentMap][i];
                        scenePhase++;
                        break;
                    }
            }
        }

        if (scenePhase == 4){
            // Boss Speaks
            gp.ui.drawDialogueScreen();
        }

        if (scenePhase == 5){
            // Return Camera to Player

            // Search Dummy 
            for (int i = 0; i < gp.npc[1].length; i++){
                if(gp.npc[gp.currentMap][i] != null &&
                    gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){
                        // Restore Player Position
                        gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                        gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                        // Delete Dummy
                        gp.npc[gp.currentMap][i] = null;
                        break;
                    }
            }

            gp.player.drawing = true;
            
            // Reset
            sceneNumber = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;

            // Change Music
            gp.playMusic(20);
        }

    }

    public void scene_demonLord(){

        if (scenePhase == 0){

            gp.bossBattleOn = true;
            gp.stopMusic();

            // Shut Iron Door
            for (int i = 0; i < gp.obj[1].length; i++){
                if (gp.obj[gp.currentMap][i] == null){
                    gp.obj[gp.currentMap][i] = new Object_Iron_Gate(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 21;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 31;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSoundEffect(24);
                    break;
                }
            }

            // Set Player Dummy in place of Player
            for (int i = 0; i < gp.npc[1].length; i++){
                if (gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }

            gp.player.drawing = false;
            scenePhase++;
        }

        if (scenePhase == 1){

            // Camera Control
            gp.player.worldY -= 2;

            if (gp.player.worldY < gp.tileSize * 22){
                scenePhase++;
            }
        }

        if (scenePhase == 2){
            
            // Search Boss
            for (int i = 0; i < gp.enemy[1].length; i++){
                if (gp.enemy[gp.currentMap][i] != null &&
                    gp.enemy[gp.currentMap][i].name.equals(Enemy_DemonLord.enemyName)){
                        gp.enemy[gp.currentMap][i].sleep = false;
                        gp.ui.npc = gp.enemy[gp.currentMap][i];
                        scenePhase++;
                        break;
                    }
            }
        }

        if (scenePhase == 3){
            // Boss Speaks
            gp.ui.drawDialogueScreen();
        }

        if (scenePhase == 4){
            // Return Camera to Player

            // Search Dummy 
            for (int i = 0; i < gp.npc[1].length; i++){
                if(gp.npc[gp.currentMap][i] != null &&
                    gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){
                        // Restore Player Position
                        gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                        gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                        // Delete Dummy
                        gp.npc[gp.currentMap][i] = null;
                        break;
                    }
            }

            gp.player.drawing = true;
            
            // Reset
            sceneNumber = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;

            // Change Music
            gp.playMusic(0);
        }

    }

    
}
