package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    Clip clip; 
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound(){

        soundURL[0] = getClass().getResource("/sound/panDEMONium - Main Theme.wav");
        soundURL[1] = getClass().getResource("/sound/panDEMONium - Demon King Lair.wav");
        soundURL[2] = getClass().getResource("/sound/PickUp.wav");
        soundURL[3] = getClass().getResource("/sound/heal.wav");
        soundURL[4] = getClass().getResource("/sound/unlock.wav");
        soundURL[5] = getClass().getResource("/sound/fanfare.wav");
        soundURL[6] = getClass().getResource("/sound/DamageEnemy.wav");
        soundURL[7] = getClass().getResource("/sound/ReceiveDamage.wav");
        soundURL[8] = getClass().getResource("/sound/SwingWeapon.wav");
        soundURL[9] = getClass().getResource("/sound/LevelUp.wav");
        soundURL[10] = getClass().getResource("/sound/CursorMove.wav");
        soundURL[11] = getClass().getResource("/sound/fireball.wav");
        soundURL[12] = getClass().getResource("/sound/Cut Tree.wav");
        soundURL[13] = getClass().getResource("/sound/Game Over.wav");
        soundURL[14] = getClass().getResource("/sound/Enter interior.wav");
        soundURL[15] = getClass().getResource("/sound/error.wav");
        soundURL[16] = getClass().getResource("/sound/sleep.wav");
        soundURL[17] = getClass().getResource("/sound/block.wav");
        soundURL[18] = getClass().getResource("/sound/parry.wav");
        soundURL[19] = getClass().getResource("/sound/speak.wav");
        soundURL[20] = getClass().getResource("/sound/Monk Dance - panDEMONium.wav");               
        soundURL[21] = getClass().getResource("/sound/Ending Theme - panDEMONium.wav");   
        soundURL[22] = getClass().getResource("/sound/Tutorial Forest - panDEMONium.wav");   
        soundURL[23] = getClass().getResource("/sound/Hyrule Castle Town - The Legend of Zelda Twilight Princess.wav"); 
        soundURL[24] = getClass().getResource("/sound/open wall.wav");   
        soundURL[25] = getClass().getResource("/sound/Tinvaak Dungeon - panDEMONium.wav");  
        soundURL[26] = getClass().getResource("/sound/Tinvaak Village - panDEMONium.wav"); 
        soundURL[27] = getClass().getResource("/sound/Under Cover of Vorlorn Village - panDEMONium.wav"); 
        soundURL[28] = getClass().getResource("/sound/Under Cover of Vorlorn Dungeon - panDEMONium.wav"); 
        soundURL[29] = getClass().getResource("/sound/Under Cover of Necromancy - panDEMONium.wav"); 

    }

    public void setFile (int i){

        try{

            //format to open audio files

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        }catch(Exception e){

        }

    }

    public void play(){

        clip.start();

    }

    public void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop(){

        clip.stop();


    }

    public void checkVolume(){

        switch(volumeScale){
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -6f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);

    }
    
}
