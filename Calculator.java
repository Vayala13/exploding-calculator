/*  BREAKING DOWN SOME OF THIS CODE
    
PACKAGES: 
    About javax.swing
        - Main source of GUI
        - Contains public classes: JTextField, JFrame, JButton, JPanel, SwingUtilities, etc.
        - Contains packages awt and awt.event
    About java.awt 
        - Contains public classes border.BorderLayout, Font, GridLayout, etc. 
        - Makes things look pretty  
    About java.awt.event
        - Contains class: ActionEvent and interface: ActionListener
        - Makes buttons work 

CLASSES:
    About JFrame
        - Contains public methods: setTitle, setSize, setDefaultCloseOperation,
            setLayout, add, setVisible
    About ActionEvent
        - Contains method: getActionCommand 
    About Double
        - Contains static method: parseDouble (converts string to double) 

INTERFACES:
    About ActionListener
        - Responds to ActionEvent 
*/ 

// Importing necessary classes to make GUI
import javax.swing.*; 
import java.awt.*;  
import java.awt.event.*;
import Action.*;  // Import our custom operation classes

// Calculator class inherits JFrame for creating the window and implements ActionListener for button clicks
public class Calculator extends JFrame implements ActionListener {
    // GUI components
    private JTextField display;  // Text field to display numbers and results
    private InputHandler inputHandler;  // Handles input processing
    
    // Calculator state variables
    private double num1 = 0, num2 = 0;  // Store the two numbers for operations
    private String operator = "";        // Store the current operation
    private boolean isDegree = true;     // Track if calculator is in degrees mode (true) or radians (false)

    // Constructor to set up the GUI
    public Calculator() {
        // Configure the main window
        this.setTitle("Calculator");                    // Set window title
        this.setSize(400, 500);                         // Set window size
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);   // Make program exit when window is closed
        this.setLayout(new BorderLayout());             // Use BorderLayout for component arrangement

        // Configure the display text field
        display = new JTextField();
        display.setEditable(false);                     // Prevent direct typing into display
        display.setFont(new Font("Arial", Font.BOLD, 24));  // Set display font
        this.add(display, BorderLayout.NORTH);          // Add display to top of window

        // Create input handler
        inputHandler = new InputHandler(display);

        // Create button panel with 7 rows and 4 columns
        JPanel panel = new JPanel(new GridLayout(7, 4, 5, 5));
        
        // Define all calculator buttons
        String[] buttons = {
            "sin", "cos", "tan", "RAD",    // Trigonometric functions and mode switch
            "log", "sqrt", "^", "%",       // Logarithm, square root, power, percentage
            "Del", "(", ")", "/",          // Delete, parentheses, division
            "7", "8", "9", "*",            // Numbers and multiplication
            "4", "5", "6", "-",            // Numbers and subtraction
            "1", "2", "3", "+",            // Numbers and addition
            "C", "0", ".", "=",            // Clear, zero, decimal point, equals
        };

        // Create and configure all buttons
        for (String text : buttons) {
            JButton button = new JButton(text);         // Create button with label
            button.setFont(new Font("Arial", Font.BOLD, 20));  // Set button font
            button.addActionListener(this);             // Make this class handle button clicks
            panel.add(button);                          // Add button to panel
        } 

        // Add the button panel to the center of the window
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);  // Make the window visible
        inputHandler.setDegreeMode(true); // Set calc default mode to degree 
    }
    
    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        inputHandler.processInput(e);
        // Update RAD/DEG button text if needed
        if (e.getActionCommand().matches("RAD|DEG")) {
            ((JButton)e.getSource()).setText(inputHandler.getDegreeMode() ? "DEG" : "RAD");
        }
    }
}