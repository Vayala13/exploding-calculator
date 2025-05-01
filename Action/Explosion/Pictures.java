package Action.Explosion;

import javax.swing.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Pictures {
    // public static void main(String[] args) {
    //     // Change to correct paths
        private static String[] imagePaths = {
            "image1.jpg",
            "image2.jpg",
            "image3.jpg" };

        public Pictures() {}

        public void showPicture() {
            // Create the frame
            JFrame frame = new JFrame("Random Image Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500); // adjust as needed

            // Picks a pic at random
            Random random = new Random();
            String selectedImage = imagePaths[random.nextInt(imagePaths.length)];
            ImageIcon icon = new ImageIcon(getClass().getResource("/Action/Explosion/" + selectedImage));

            // Load the image into the window 
            JLabel label = new JLabel(icon);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);

            // Play Sound
            // SoundPlayer explosion = new SoundPlayer();
            // explosion.playSound();

            // Play Sound in a new thread
            new Thread(() -> {
                SoundPlayer explosion = new SoundPlayer();
                explosion.playSound();
            }).start();
            
            // Display the image on the new window 
            frame.add(label);
            frame.setVisible(true);

            // Close the application after 3 seconds
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    frame.dispose();
                    System.exit(0);
                }
            }, 3000);
        }
}
