package Action.Explosion;

import javax.swing.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Pictures {
    // public static void main(String[] args) {
    //     // Change to correct paths
        private static String[] imagePaths = {
            "image1",
            "image2",
            "image3" };

        public Pictures() {
            //showPicture(); 
            JFrame newWindow = new JFrame("New Window Title");
            newWindow.setSize(400, 300);
            newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newWindow.setVisible(true);
        }

        public void showPicture() {
                        // Picks a pic at random
            Random random = new Random();
            String selectedImage = imagePaths[random.nextInt(imagePaths.length)];

            // Create the frame
            JFrame frame = new JFrame("Random Image Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500); // adjust as needed

            // Load and display the image
            JLabel label = new JLabel(new ImageIcon(selectedImage));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
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
    //}
}
