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
import Action.*;

// cal class inherits JFrame for creating the window && ActionListener for the buttons
public class Calculator extends JFrame implements ActionListener {
    // gui displays field where numbers && results are shown
    private JTextField display;
    // Variables to store the first && second number && the operation selection
    private double num1 = 0, num2 = 0;
    private String operator = "";
    private boolean isDegree = true; // Starts calc in DEG mode

    // constructor to set up the GUI
    public Calculator() {
        // the title of the window
        this.setTitle("Calculator");
        // the size of window 
        this.setSize(400, 500);
        // IMPORTANT: makes sure that the program closes
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // BorderLayout organizes the GUI Components (reason it takes 30 min lol)
        this.setLayout(new BorderLayout());

        // create the text field and configure for displayin input && output
        display = new JTextField();
        display.setEditable(false);                                 // user wont type directly into the text box
        display.setFont(new Font("Arial", Font.BOLD, 24));  // text configurations
        this.add(display, BorderLayout.NORTH);                             // configured to top of window

        // 6 x 4 panel for the buttons n also the spacing
        JPanel panel = new JPanel(new GridLayout(7, 4, 5, 5));
        // button labels
        String[] buttons = {
            "sin", "cos", "tan", "RAD",
            "log", "sqrt", "^", "%", 
            "Del", "(", ")", "/",
            "7", "8", "9", "x",            
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "C", "0", ".", "=",
        };

        // for loop to configure th bottons and andd to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);                         // create a button with the label
            button.setFont(new Font("Arial", Font.BOLD, 20)); // text config
            button.addActionListener(this);                             // set this to handle clicks
            panel.add(button);                                          // addin the button to panel line 32 (IF no changes have been made)
        } 

        // add the panel to the middle (praise be the BorderLayout)
        this.add(panel, BorderLayout.CENTER);
        // make it a real boy (papa)
        this.setVisible(true);
    } // end of constructor 
    
    // Method called anytime a registered JButton (calculator button) variable is pressed 
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();    // get the label of the botton that was pressed

        // if its number append 
        if (input.matches("[0-9]")) {
            display.setText(display.getText() + input); // append digit
        }
        // if its an operator store number and operator, clear display too
        else if (input.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(display.getText());   // Convert current display to num
            operator = input;                               // Store the operator
            display.setText("");                          // Clear display
        } 
        else if (input.equals("(") || input.equals(")")) {
            display.setText(display.getText() + input); // append digit or close parentheses 
        }
        // if user presses log or sqrt button
        else if(input.matches("log|sqrt")) 
        {
            operator  = input;
            //display.setText("");
            display.setText(operator + "(");
        }
        // if user presses sin, cos, tan
        else if (input.matches("sin|cos|tan")) {
            operator = input;
            display.setText(operator + "(");
        }
        else if (input.matches("RAD|DEG")) {
            isDegree = !isDegree;
            // Change RAD/DEG. Cast to JButton since you want to change button label 
            ((JButton)e.getSource()).setText(isDegree ? "DEG" : "RAD");
        }
        // if equals then calculate the result
        else if (input.equals("=")) {
            
            //double result = 0;
            double result = new Addition(num1, num2);

            if(operator.equals("log") || operator.equals("sqrt") ||
            operator.equals("sin") || operator.equals("cos") || operator.equals("tan")) {
                
                display.setText(display.getText() + ")"); // trying to close parentheses 
                String text2 = display.getText(); 
                text2 = text2.replaceAll("[a-zA-Z()]", ""); // remove "log", "sin", "cos", etc.
                num1 = Double.parseDouble(text2);
                //num1 = Double.parseDouble(display.getText());

                double angle = isDegree ? Math.toRadians(num1) : num1; // Check if rad or deg

                result = switch (operator)
                {
                    case "log" -> Math.log10(num1);
                    case "sqrt" -> Math.sqrt(num1);
                    case "sin" -> Math.sin(angle);
                    case "cos" -> Math.cos(angle); 
                    case "tan" -> Math.tan(angle); 
                    default -> 0;
                };
            }
            else {
                num2 = Double.parseDouble(display.getText());   // get the second number from display
                // switch case for each operator
                result = switch (operator) 
                {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> num2 != 0 ? num1 / num2 : 0; // HERE: allows for the funny magic XD
                    default -> 0;
                };
            }
            // show the results in the box
            display.setText(String.valueOf(result));
            // display.setText(String.format("%.2f", result)); // Do this for trig stuff 
        }
        // if C reset it all
        else if (input.equals("C")) {
            display.setText("");    // clear display
            num1 = num2 = 0;          // reset numbers
            operator = "";            // clear operators
        }
    }
}