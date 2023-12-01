package main;

import data.Progress;
import entity.Entity;

public class EventHandler {

    GamePanel gp;
    EventRectangle eventRectangle[][][];
    public Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTriggerEvent = true;
    int tempMap, tempColumn, tempRow;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventMaster = new Entity(gp);

        eventRectangle = new EventRectangle[gp.maxMap][gp.maxWorldColumn][gp.maxWorldRow];

        int map = 0;
        int column = 0;
        int row = 0;

        while (map < gp.maxMap && column < gp.maxWorldColumn && row < gp.maxWorldRow){
            eventRectangle[map][column][row] = new EventRectangle();
            eventRectangle[map][column][row].x = 23;
            eventRectangle[map][column][row].y = 23;
            eventRectangle[map][column][row].width = 2;
            eventRectangle[map][column][row].height = 2;
            eventRectangle[map][column][row].eventRectangleDefaultX = eventRectangle[map][column][row].x;
            eventRectangle[map][column][row].eventRectangleDefaultY =  eventRectangle[map][column][row].y;
            column++;
            if (column == gp.maxWorldColumn){
                column = 0;
                row++;

                if (row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }

            }
        }

        setDialogue();
    }

    public void setDialogue(){

    }

    public void checkEvent(){
        // Check if player is more than one tile away after event
        int xDistance = Math.abs(gp.player.worldX - previousEventX); // Absolute value, takes positive
        int yDistance = Math.abs (gp.player.worldY - previousEventY);
        int distance = Math.max (xDistance, yDistance); // Picks the greater number of two integers, in this case either horizontal or vertical
        if (distance > gp.tileSize){
            canTriggerEvent = true;
        }

        if (canTriggerEvent == true){

            // Areas

            // Tutorial Forest
            if (hit(0, 40, 12, "up") == true){teleport(2, 25, 30, gp.outdoor, gp.merchant_tent);} // merchant tent 
            else if (hit(2, 25, 30, "down") == true && gp.previousLevel == gp.tutorial_forest){teleport(0, 40, 12, gp.outdoor, gp.tutorial_forest);} // back to forest 
            
            // Tinvaak Village
            else if (hit(0, 34, 39, "down") == true){teleport(3, 31, 42, gp.outdoor, gp.tinvaak_village);} // tinvaak village
            else if (hit(3, 31, 43, "down") == true){teleport(0, 34, 38, gp.outdoor, gp.tutorial_forest);} // back to forest 
            else if (hit(3, 15, 21, "up") == true){teleport(4, 23, 29, gp.indoor, gp.tinvaak_townhall);} // tinvaak town hall
            else if (hit (4, 23, 30, "down") == true){teleport(3, 15, 21, gp.outdoor, gp.tinvaak_village);} // village 1
            else if (hit (3, 19, 32, "any") == true){teleport(5, 30, 31, gp.indoor, gp.tinvaak_house1);} // house 1
            else if (hit (5, 30, 32, "any") == true){teleport(3, 19, 33, gp.outdoor, gp.tinvaak_village);} // to village 1
            else if (hit (3, 12, 34, "any") == true){teleport(6, 30, 31, gp.indoor, gp.tinvaak_house2);} // to house 2
			else if (hit (6, 30, 32, "any") == true){teleport(3, 12, 35, gp.outdoor, gp.tinvaak_village);} // to village 1
			else if (hit (3, 22, 37,"any") == true){teleport(7, 30, 31, gp.indoor, gp.tinvaak_house3);} // to house 3
			else if (hit (7, 30, 32,"any") == true){teleport(3, 22, 38, gp.outdoor, gp.tinvaak_village);} // to village 1
            else if (hit(3, 45, 6, "any") == true){teleport(1, 43, 41, gp.dungeon, gp.tinvaak_dungeon);} // dungeon 
            else if (hit(1, 45, 42, "right") == true){teleport(3, 45, 7, gp.outdoor, gp.tinvaak_village);} // back to village 1
            else if (hit(3, 19, 5, "any") == true){teleport(8, 26, 43, gp.dungeon, gp.vorlorn_village);} // to village 2
            
            // Vorlorn Village
            else if (hit(8, 26, 43, "any") == true){teleport(3, 19, 5, gp.outdoor, gp.tinvaak_village);} // to village 1
            else if (hit(8, 20, 7, "any") == true){teleport(9, 31, 40, gp.dungeon, gp.vorlorn_dungeon);} // dungeon 
            else if (hit(9, 31, 40, "any") == true){teleport(8, 20, 7, gp.dungeon, gp.vorlorn_village);} // back to village 
            else if (hit(8, 38, 8, "any") == true){teleport(2, 25, 31, gp.indoor, gp.merchant_tent);} // merchant tent 
            else if (hit(2, 25, 31, "any") == true && gp.previousLevel == gp.vorlorn_village){teleport(8, 38, 8, gp.dungeon, gp.vorlorn_village);} // to village 2
            else if (hit(8, 27, 28, "up") == true){teleport(10, 23, 29, gp.indoor, gp.vorlorn_townhall);} // mayor office
            else if (hit(10, 23, 29, "down") == true){teleport(8, 27, 28, gp.dungeon, gp.vorlorn_village);} // back to village
            else if (hit(8, 18, 34, "up") == true){teleport(11, 30, 32, gp.indoor, gp.vorlorn_house1);} // house1
            else if (hit(11, 30, 32, "down") == true){teleport(8, 18, 34, gp.dungeon, gp.vorlorn_village);} // back to village
            else if (hit(8, 26, 33, "up") == true){teleport(12, 30, 32, gp.indoor, gp.vorlorn_house2);} // house2
            else if (hit(12, 30, 32, "down") == true){teleport(8, 26, 33, gp.dungeon, gp.vorlorn_village);} // back to village
            else if (hit(8, 34, 32, "up") == true){teleport(13, 30, 32, gp.indoor, gp.vorlorn_house3);} // house3
            else if (hit(13, 30, 32, "down") == true){teleport(8, 34, 32, gp.dungeon, gp.vorlorn_village);} // back to village
            else if (hit(8, 14, 22, "down") == true){teleport(14, 21, 40, gp.outdoor, gp.demon_lair);} // to demon lair

            // Demon Lair
            else if (hit(14, 21, 42, "down") == true){teleport(8, 14, 22, gp.dungeon, gp.vorlorn_village);} // to village 2
            

            // Cutscenes
            else if (hit(1, 35, 22, "any") == true){demonMonk();} // Demon Monk Cutscene
            else if (hit(9, 21, 15, "any") == true){necromancer();} // Necromancer Cutscene
            else if (hit(14, 21, 30, "any") == true){demonLord();} // Demon Lord Cutscene

            // Miscellaneous
            else if (hit (2, 25, 26, "up") == true){speak(gp.npc[gp.currentMap][0]);} // talk to merchant interacting with table
            else if (hit (4, 23, 28,"up") == true){speak(gp.npc[gp.currentMap][0]);} // talk to the mayor 
            else if (hit (5, 30, 30,"up") == true){speak(gp.npc[gp.currentMap][0]);} // speak to tinvaak homeowner 1
            else if (hit (6, 30, 30,"up") == true){speak(gp.npc[gp.currentMap][0]);} // speak to homeowner 2
            else if (hit (7, 30, 30,"up") == true){speak(gp.npc[gp.currentMap][0]);}	//speak to homeowner 3

        }
    

    }

    public boolean hit (int map, int column, int row, String requiredDirection){

        boolean hit = false;

        if (map == gp.currentMap){

            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRectangle[map][column][row].x = column * gp.tileSize + eventRectangle[map][column][row].x;
            eventRectangle[map][column][row].y = row * gp.tileSize + eventRectangle[map][column][row].y;

            if (gp.player.solidArea.intersects(eventRectangle[map][column][row]) && eventRectangle[map][column][row].eventDone == false){
                if (gp.player.direction.contentEquals(requiredDirection) || requiredDirection.contentEquals("any")){
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRectangle[map][column][row].x = eventRectangle[map][column][row].eventRectangleDefaultX;
            eventRectangle[map][column][row].y = eventRectangle[map][column][row].eventRectangleDefaultY;

        }

        return hit;
    }

    public void teleport(int map, int column, int row, int area, int level){
        gp.gameState = gp.transitionState;
        gp.previousLevel = gp.currentLevel;
        gp.nextArea = area;
        gp.nextLevel = level;
        tempMap = map;
        tempColumn = column;
        tempRow = row;
        
        canTriggerEvent = false;
        gp.playSoundEffect(14);

    }

    public void speak (Entity entity){
        if (gp.keyH.enterPressed == true){
            gp.gameState = gp.dialogueState;
            entity.speak();
        }
    }

    public void demonMonk(){
        if (gp.bossBattleOn == false && Progress.demonMonkDefeated == false){
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNumber = gp.csManager.demonMonk;
        }
    }

    public void necromancer(){
        if (gp.bossBattleOn == false && Progress.necromancerDefeated == false){
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNumber = gp.csManager.necromancer;
        }
    }

    public void demonLord(){
        if (gp.bossBattleOn == false && Progress.demonLordDefeated == false){
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNumber = gp.csManager.demonLord;
        }
    }

}
