package Action.Explosion;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {
    public static void main(String[] args) {
        try {
            // Load the audio file
            File soundFile = new File("/Action/Explosion/sound.wav"); // Replace with your actual file path
            //URL soundURL = SoundPlayer.class.getResource("/Action/Explosion/sound.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Get a sound clip resource
            Clip clip = AudioSystem.getClip();

            // Open the audio clip and load samples from the audio input stream
            clip.open(audioStream);

            // Play the audio clip
            clip.start();

            // Optional: wait for the sound to finish playing
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
