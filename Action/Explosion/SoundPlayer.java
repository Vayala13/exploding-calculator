package Action.Explosion;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL; 

public class SoundPlayer {

    public SoundPlayer() {} 

    public void playSound() {
        try {
            // Making sure the file path isn't hardcoded 
            URL soundURL = getClass().getResource("/Action/Explosion/sound.wav");

            // Load the audio file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);

            // Get a sound clip resource
            Clip clip = AudioSystem.getClip();

            // Open the audio clip and load samples from the audio input stream
            clip.open(audioStream);

            // Play the audio clip
            clip.start();

            // Optional: wait for the sound to finish playing
            Thread.sleep(clip.getMicrosecondLength() / 1000); }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
