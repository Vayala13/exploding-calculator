import javax.swing.*; 

// main method
public class Main {
    public static void main(String[] args) {

        // runs the calculator GUI 
        // invokeLater is there to prevent bugs when a new GUI is launched every time. 
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}

