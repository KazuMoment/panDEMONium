package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, enterPressed, ePressed, shootPressed, spacePressed;

    //Debug 
    boolean showDebugMenu = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        
        int code = e.getKeyCode();

        // Title State
        if (gp.gameState == gp.titleState){
            titleState(code);
        }

        // Play State
        else if (gp.gameState == gp.playState){
            playState(code);
        }

        // Pause State
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }

        // Dialogue State
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }

        // Character State
        else if (gp.gameState == gp.characterState){
            characterState(code);
        }

        // Options State
        else if (gp.gameState == gp.optionsState){
            optionsState(code);
        }

        // Game Over State
        else if (gp.gameState == gp.gameOverState){
            gameOverState(code);
        }

        // Shop State
        else if (gp.gameState == gp.shopState){
            shopState(code);
        }

        // Map State
        else if (gp.gameState == gp.mapState){
            mapState(code);
        }

        // Save State
        else if (gp.gameState == gp.saveState){
            saveState(code);
        }

    }

    public void titleState(int code){

        if (gp.ui.titleScreenState == 0){
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                gp.ui.commandNumber--;
                if (gp.ui.commandNumber < 0){
                    gp.ui.commandNumber = 2;
                }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                    gp.ui.commandNumber++;
                    if (gp.ui.commandNumber > 2){
                        gp.ui.commandNumber = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER){
                    if (gp.ui.commandNumber == 0){
                        gp.gameState = gp.playState;
                        gp.checkMusic();
                    }
                    if (gp.ui.commandNumber == 1){
                        gp.saveLoad.load();
                        gp.gameState = gp.playState;
                        gp.checkMusic();
                    }
                    if (gp.ui.commandNumber == 2){
                        System.exit(0);
                    }
                } 
            }
    }

    public void playState(int code){
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                upPressed = true;
            }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = true; 
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if (code == KeyEvent.VK_E){
            ePressed = true;
        }
        if (code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_T){
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_SHIFT){
            shiftPressed = true;
        }
        if (code == KeyEvent.VK_F){
            shootPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
        }
        if (code == KeyEvent.VK_M){
            gp.gameState = gp.mapState;
        }
        if (code == KeyEvent.VK_X){
            if (gp.map.miniMapOn == false){
                gp.map.miniMapOn = true;
            }
            else{
                gp.map.miniMapOn = false;
            }
        }

        if (code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }

        // Debug
        if (code == KeyEvent.VK_F1){
            if (showDebugMenu == false){
                showDebugMenu = true;
            }
            else if (showDebugMenu == true){
                showDebugMenu = false;
            }
        }
        if (code == KeyEvent.VK_F5){
            switch(gp.currentMap){
                case 0: gp.tileM.loadMap("/maps/forest_tutorial.txt", 0);
                case 1: gp.tileM.loadMap("/maps/dungeon_1.txt", 1);
                case 2: gp.tileM.loadMap("/maps/merchant_house.txt", 2);
            }
            
        }
    }

    public void pauseState(int code){
        if (code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
        
    }

    public void dialogueState(int code){
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        
    }

    public void characterState(int code){
        if (code == KeyEvent.VK_T || code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
        playerInventory(code);
    }   

    public void optionsState(int code){

        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNumber = 0;
        switch (gp.ui.subState){
            case 0: maxCommandNumber = 5; break;
            case 3: maxCommandNumber = 1; break;
        }
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            gp.ui.commandNumber--;
            gp.playSoundEffect(10);
            if (gp.ui.commandNumber < 0){
                gp.ui.commandNumber = maxCommandNumber;
            }
        }

        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            gp.ui.commandNumber++;
             gp.playSoundEffect(10);
            if (gp.ui.commandNumber > maxCommandNumber){
                gp.ui.commandNumber = 0;
            }
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            if (gp.ui.subState == 0){
                if (gp.ui.commandNumber == 1 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSoundEffect(10);
                }
                if (gp.ui.commandNumber == 2 && gp.soundEffect.volumeScale > 0){
                    gp.soundEffect.volumeScale--;
                    gp.playSoundEffect(10);
                }
            }
        }

        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            if (gp.ui.subState == 0){
                if (gp.ui.commandNumber == 1 && gp.music.volumeScale < 5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSoundEffect(10);
                }
                if (gp.ui.commandNumber == 2 && gp.soundEffect.volumeScale < 5){
                    gp.soundEffect.volumeScale++;
                    gp.playSoundEffect(10);
                }
            }
        }
        
    }

    public void gameOverState(int code){

        if (code == KeyEvent.VK_W ){
            gp.ui.commandNumber--;
            if (gp.ui.commandNumber < 0){
                gp.ui.commandNumber = 1;
            }
            gp.playSoundEffect(10);
        }

        if (code == KeyEvent.VK_S ){
            gp.ui.commandNumber++;
            if (gp.ui.commandNumber > 1){
                gp.ui.commandNumber = 0;
            }
            gp.playSoundEffect(10);
        }

        if (code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNumber == 0){
                gp.gameState = gp.playState;
                gp.resetGame(false);
                gp.checkMusic();
            }
            else if (gp.ui.commandNumber == 1){
                gp.gameState = gp.titleState;
                gp.ui.titleScreenState = 0;
                gp.resetGame(true);
            }
        }

    }

    public void saveState(int code){
         if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            gp.ui.commandNumber--;
            if (gp.ui.commandNumber < 0){
                gp.ui.commandNumber = 1;
            } 
            gp.playSoundEffect(10);
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            gp.ui.commandNumber++;
            if (gp.ui.commandNumber > 1){
                gp.ui.commandNumber = 0;
            } 
            gp.playSoundEffect(10);
        }
    }

    public void shopState(int code){
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        if (gp.ui.subState == 0){
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
               gp.ui.commandNumber--;
               if (gp.ui.commandNumber < 0){
                    gp.ui.commandNumber = 2;
               } 
               gp.playSoundEffect(10);
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
               gp.ui.commandNumber++;
               if (gp.ui.commandNumber > 2){
                    gp.ui.commandNumber = 0;
               } 
               gp.playSoundEffect(10);
            }
        }
        if (gp.ui.subState == 1){
            npcInventory(code);
            if (code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0; 
            }
        }
        if (gp.ui.subState == 2){
            playerInventory(code);
            if (code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0; 
            }
        }
    }

    public void mapState(int code){

        if (code == KeyEvent.VK_M){
            gp.gameState = gp.playState;
        }

    }

    public void playerInventory(int code){
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            if(gp.ui.playerSlotRow != 0){
                gp.ui.playerSlotRow--;
                gp.playSoundEffect(10);
            }  
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            if (gp.ui.playerSlotColumn != 0){
                gp.ui.playerSlotColumn--;
                gp.playSoundEffect(10);
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
           if(gp.ui.playerSlotRow != 3){
                gp.ui.playerSlotRow++;
                gp.playSoundEffect(10);
            }  
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            if (gp.ui.playerSlotColumn != 4){
                gp.ui.playerSlotColumn++;
                gp.playSoundEffect(10);
            }
        }
    }

    public void npcInventory(int code){
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            if(gp.ui.npcSlotRow != 0){
                gp.ui.npcSlotRow--;
                gp.playSoundEffect(10);
            }  
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            if (gp.ui.npcSlotColumn != 0){
                gp.ui.npcSlotColumn--;
                gp.playSoundEffect(10);
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
           if(gp.ui.npcSlotRow != 3){
                gp.ui.npcSlotRow++;
                gp.playSoundEffect(10);
            }  
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            if (gp.ui.npcSlotColumn != 4){
                gp.ui.npcSlotColumn++;
                gp.playSoundEffect(10);
            }
        }
    }
    

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false; 
        }

        if (code == KeyEvent.VK_SHIFT){
            shiftPressed = false;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
        if (code == KeyEvent.VK_E){
            ePressed = false;
        }
        if (code == KeyEvent.VK_F){
            shootPressed = false;
        }
        if (code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
    }
}