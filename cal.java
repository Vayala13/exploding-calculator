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

// cal class inherits JFrame for creating the window && ActionListener for the buttons
public class cal extends JFrame implements ActionListener {
    // gui displays field where numbers && results are shown
    private JTextField display;
    // Variables to store the first && second number && the operation selection
    private double num1 = 0, num2 = 0;
    private String operator = "";

    // constructor to set up the GUI
    public cal() {
        // the title of the window
        this.setTitle("Calculator");
        // the size of window 
        this.setSize(300, 400);
        // IMPORTANT: makes sure that the program closes
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // BorderLayout organizes the GUI Components (reason it takes 30 min lol)
        this.setLayout(new BorderLayout());

        // create the text field and configure for displayin input && output
        display = new JTextField();
        display.setEditable(false);                                 // user wont type directly into the text box
        display.setFont(new Font("Arial", Font.BOLD, 24));  // text configurations
        this.add(display, BorderLayout.NORTH);                             // configured to top of window

        // 4 x 4 panel for the buttons n also the spacing
        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));
        // button labels
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+",
            "log", "sqrt"
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
    }
    
    // handle button clicks
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();    // get the label of the botton that was pressed

        // if its number append 
        if (input.matches("[0-9]")) {
            display.setText(display.getText() + input); // append digit
        // if its an operator store number and operator, clear display too
        } else if (input.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(display.getText());   // Convert current display to num
            operator = input;                               // Store the operator
            display.setText("");                          // Clear display
        // if equals then calculate the result
        } 
        // if user presses log or sqrt button
        else if(input.matches("log|sqrt")) 
        {
            operator  = input;
            display.setText("");
        }
        else if (input.equals("=")) 
        {
            double result = 0;
            if(operator.equals("log") || operator.equals("sqrt"))
            {
                num1 = Double.parseDouble(display.getText());
                result = switch (operator)
                {
                    case "log" -> Math.log10(num1);
                    case "sqrt" -> Math.sqrt(num1);
                    default -> 0;
                };
            }
            else
            {
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

        // if C reset it all
        } else if (input.equals("C")) {
            display.setText("");    // clear display
            num1 = num2 = 0;          // reset numbers
            operator = "";            // clear operators
        }
    }

    // main method
    public static void main(String[] args) {

        // runs the calculator GUI 
        // invokeLater is there to prevent bugs when a new GUI is launched every time. 
        SwingUtilities.invokeLater(() -> new cal());
    }
}
