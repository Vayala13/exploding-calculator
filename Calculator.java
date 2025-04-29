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
    }
    
    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();  // Get which button was pressed

        // Handle number input
        if (input.matches("[0-9]")) {
            display.setText(display.getText() + input);  // Append digit to display
        }
        // Handle basic arithmetic operators
        else if (input.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(display.getText());  // Store first number
            operator = input;                               // Store operator
            display.setText("");                           // Clear display for second number
        } 
        // Handle parentheses
        else if (input.equals("(") || input.equals(")")) {
            display.setText(display.getText() + input);  // Append parentheses to display
        }
        // Handle logarithmic and square root functions
        else if(input.matches("log|sqrt")) {
            operator = input;
            display.setText(operator + "(");  // Format as function call
        }
        // Handle trigonometric functions
        else if (input.matches("sin|cos|tan")) {
            operator = input;
            display.setText(operator + "(");  // Format as function call
        }
        // Handle degree/radian mode switch
        else if (input.matches("RAD|DEG")) {
            isDegree = !isDegree;  // Toggle mode
            // Update button label to show current mode
            ((JButton)e.getSource()).setText(isDegree ? "DEG" : "RAD");
        }
        // Handle equals button (calculate result)
        else if (input.equals("=")) {
            double result = 0;

            // Handle single-operand operations (log, sqrt, trig functions)
            if(operator.equals("log") || operator.equals("sqrt") ||
               operator.equals("sin") || operator.equals("cos") || operator.equals("tan")) {
                
                display.setText(display.getText() + ")");  // Close function parentheses
                String text2 = display.getText(); 
                text2 = text2.replaceAll("[a-zA-Z()]", "");  // Extract number from function call
                num1 = Double.parseDouble(text2);

                // Convert to radians if in degree mode
                double angle = isDegree ? Math.toRadians(num1) : num1;

                // Create appropriate operation object based on operator
                Operation op = switch (operator) {
                    case "log" -> new Logarithm();
                    case "sqrt" -> new SquareRoot();
                    case "sin" -> new Sine();
                    case "cos" -> new Cosine();
                    case "tan" -> new Tangent();
                    default -> null;
                };
                
                // Perform the operation
                if (op != null) {
                    result = operator.matches("sin|cos|tan") ? op.performOp(angle) : op.performOp(num1);
                }
            }
            // Handle two-operand operations (basic arithmetic)
            else {
                num2 = Double.parseDouble(display.getText());  // Get second number
                
                // Create appropriate operation object based on operator
                Operation op = switch (operator) {
                    case "+" -> new Addition();
                    case "-" -> new Subtraction();
                    case "*" -> new Multiplication();
                    case "/" -> new Division();
                    default -> null;
                };
                
                // Perform the operation
                if (op != null) {
                    result = op.performOp(num1, num2);
                }
            }
            // Display the result
            display.setText(String.valueOf(result));
        }
        // Handle clear button
        else if (input.equals("C")) {
            display.setText("");     // Clear display
            num1 = num2 = 0;         // Reset numbers
            operator = "";           // Clear operator
        }
    }
}