package main;

import java.awt.Graphics2D;

import enemy.Enemy_Monk;
import entity.PlayerDummy;
import object.Object_Iron_Gate;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNumber;
    public int scenePhase;

    // Scene Number
    public final int NA = 0;
    public final int demonMonk = 1;

    public CutsceneManager(GamePanel gp){
        this.gp = gp;
    }
    
    public void draw(Graphics2D g2){
        this.g2 = g2;

        switch(sceneNumber){
            case demonMonk: scene_demonMonk(); break;
        }

    }

    public void scene_demonMonk(){

        if (scenePhase == 0){

            gp.bossBattleOn = true;

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
                    gp.enemy[gp.currentMap][i].name.equals(Enemy_Monk.enemyName)){
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
            gp.stopMusic();
            gp.playMusic(20);
        }

    }

    
}
