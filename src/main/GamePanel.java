package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.Pathfinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import tile.Map;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import data.PlayerTime;

public class GamePanel extends JPanel implements Runnable{
    
    // Screen Settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // Will now be a 48x48 tile
    public final int maxScreenColumn = 20;
    public final int maxScreenRow = 12; 
    public final int screenWidth = tileSize * maxScreenColumn; // 960 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels 

    // World Settings
    public int maxWorldColumn;
    public int maxWorldRow;
    public final int worldWidth = tileSize * maxWorldColumn;
    public final int worldHeight = tileSize * maxWorldRow; 
    public final int maxMap = 10;
    public int currentMap = 0;

    // Full Screen
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;
    
    // FPS
    int FPS = 60; 

    // System
    public JDBC toSQL = new JDBC();
    public PlayerTime playerTime = new PlayerTime();
    public TileManager tileM = new TileManager(this); 
    public KeyHandler keyH = new KeyHandler(this);
    public SaveLoad saveLoad = new SaveLoad(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    public Pathfinder pFinder = new Pathfinder(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    public CutsceneManager csManager = new CutsceneManager(this);
    Map map = new Map(this);
    
    public EntityGenerator eGenerator = new EntityGenerator(this);
    Thread gameThread; 
   
    // Entity and Object
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity [maxMap][20];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity enemy[][] = new Entity[maxMap][20];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
    public Entity projectile[][]  = new Entity[maxMap][20];
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int shopState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;
    public final int saveState = 11;
    public final int cutsceneState = 12;

    // Others 
    public boolean bossBattleOn = false;

    // Area
    public int currentArea;
    public int nextArea;
    public final int outdoor = 50;
    public final int indoor = 51;
    public final int dungeon = 52;

    // Level
    public int currentLevel;
    public int nextLevel;
    public final int tutorial_forest = 100;
    public final int tinvaak_village = 101;
    public final int tinvaak_dungeon = 102;
    public final int tinvaak_townhall = 103;
    public final int tinvaak_house1 = 104;
    public final int tinvaak_house2 = 105;
    public final int tinvaak_house3 = 106;
    public final int merchant_tent = 107;
    public final int victoria_town = 108;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setEnemy();
        aSetter.setInteractiveTile();
        eManager.setup();
        
        playMusic(0);
        gameState = titleState;
        currentArea = outdoor;
        currentLevel = tutorial_forest;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();

        if (fullScreenOn == true){
            setFullscreen();
        }
    }

    public void resetGame(boolean restart){

        player.setDefaultPosition();
        player.restoreStatus();
        player.resetCounter();
        aSetter.setEnemy();
        
        if (restart == true){
            player.setDefaultValues();
            aSetter.setObject();
            aSetter.setInteractiveTile();
            eManager.lighting.resetDay(); 
        }
    }

    public void setFullscreen(){
        
        // Get Local Screen Device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // Get Full Screen Width and Height
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
 
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.1666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;


        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime; 

            if (delta >= 1){
                update();
                drawToTempScreen(); // Draw everything to the buffered image
                drawToScreen(); // Draw the buffered image to the screen
                delta--;   
            }

            if (timer >= 1000000000){
                timer = 0;
            }

        }
      
    }

        public void update(){

            if (gameState == playState){
                //timer start
                // Player
                player.update();

                //NPC
                for (int i = 0; i < npc[1].length; i++){
                    if (npc[currentMap][i] != null){
                        npc[currentMap][i].update();
                    }
                }
                // Enemy
                for (int i = 0; i < enemy[1].length; i++){
                    if (enemy[currentMap][i] != null){
                        if (enemy[currentMap][i].alive == true && enemy[currentMap][i].dying == false){
                            enemy[currentMap][i].update();
                        }
                        if (enemy[currentMap][i].alive == false){
                            enemy[currentMap][i].checkDrop();
                            enemy[currentMap][i] = null;
                        }
                    }
                }
                // Projectile
                for (int i = 0; i < projectile[1].length; i++){
                        if (projectile[currentMap][i] != null){
                            if (projectile[currentMap][i].alive == true){
                                projectile[currentMap][i].update();
                            }
                            if (projectile[currentMap][i].alive == false){
                                projectile[currentMap][i] = null;
                        }
                    }
                }

                // Particle
                for (int i = 0; i < particleList.size(); i++){
                        if (particleList.get(i) != null){
                            if (particleList.get(i).alive == true){
                                particleList.get(i).update();
                            }
                            if (particleList.get(i).alive == false){
                                particleList.remove(i);
                        }
                    }
                }

                // Interactive Tiles
                for (int i = 0; i < iTile[1].length; i++){
                    if (iTile[currentMap][i] != null){
                        iTile[currentMap][i].update(); 
                    }
                }
                eManager.update();
        }

            if (gameState == pauseState){

            }
            

        }

        public void drawToTempScreen(){

            // Title Screen
            if (gameState == titleState){
                ui.draw(g2);
            }

            else if(gameState == mapState){
                map.drawFullMapScreen(g2);
            }

            else{

                // Draw Tile
                tileM.draw(g2);

                // Draw Interactive Tile
                for (int i = 0; i < iTile[1].length; i++){
                    if (iTile[currentMap][i] != null){
                        iTile[currentMap][i].draw(g2);
                    }
                }


                // Add Entities to List
                entityList.add(player);
                
                for (int i = 0; i < npc[1].length; i++){
                    if (npc[currentMap][i] != null){
                        entityList.add(npc[currentMap][i]);
                    }
                }

                for (int i = 0; i < obj[1].length; i++){
                    if (obj[currentMap][i] != null){
                        entityList.add(obj[currentMap][i]);
                    }
                }

                for (int i = 0; i < enemy[1].length; i++){
                    if (enemy[currentMap][i] != null){
                        entityList.add(enemy[currentMap][i]);
                    }
                }

                for (int i = 0; i < projectile[1].length; i++){
                    if (projectile[currentMap][i] != null){
                        entityList.add(projectile[currentMap][i]);
                    }
                }

                for (int i = 0; i < particleList.size(); i++){
                    if (particleList.get(i) != null){
                        entityList.add(particleList.get(i));
                    }
                }

                // Sort from lowest worldY to highest worldY
                Collections.sort(entityList, new Comparator<Entity>() {

                    @Override
                    public int compare(Entity e1, Entity e2) {
                        int result  = Integer.compare(e1.worldY, e2.worldY);
                        return result;
                    }
                    
                });

                // Draw Entities
                for(int i = 0; i < entityList.size(); i++){
                    entityList.get(i).draw(g2);
                }
                entityList.clear();

                // Environment
                eManager.draw(g2);

                // Minimap
                map.drawMiniMap(g2);

                // Draw UI
                ui.draw(g2);
                
            }

            if (keyH.showDebugMenu ==  true){
                g2.setFont(new Font ("Arial", Font.PLAIN, 20));
                g2.setColor(Color.white);
                int x = 10;
                int y = 400;
                int lineHeight = 20;

                g2.drawString("WorldX: " + player.worldX, x, y); y += lineHeight;
                g2.drawString("WorldY: " + player.worldY, x, y); y += lineHeight;
                g2.drawString("Column: " + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
                g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
            } 

        }

        public void drawToScreen(){
            Graphics g = getGraphics();
            g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
            g.dispose();
        }


        public void playMusic(int i){
            music.setFile(i);
            music.play();
            music.loop();
        }

        public void stopMusic(){
            music.stop();
        }

        public void playSoundEffect(int i){
            soundEffect.setFile(i);
            soundEffect.play();
        }

        public void checkMusic(){
            stopMusic();
            switch(currentLevel){
                case tutorial_forest: playMusic(22); break;
                case tinvaak_village: playMusic(20); break;
                case tinvaak_dungeon: playMusic(21); break;
                case merchant_tent: playMusic(23); break;
                case victoria_town: playMusic(20); break;
            }
        }

        public void changeArea(){

            if (nextLevel != currentLevel){
                currentLevel = nextLevel;
                checkMusic();
            }
            currentArea = nextArea; 
            aSetter.setEnemy();
        }

}
