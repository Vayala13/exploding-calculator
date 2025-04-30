package Action;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;

// Class to handle calculator input processing
public class InputHandler {
    private JTextField display;
    private double num1 = 0, num2 = 0;
    private String operator = "";
    private boolean isDegree;

    public InputHandler(JTextField display) {
        this.display = display;
    }

    // Process input from calculator buttons
    public void processInput(ActionEvent e) {
        String input = e.getActionCommand();

        // Handle number input
        if (input.matches("[0-9 | .]")) {
            display.setText(display.getText() + input);
        }
        // Handle basic arithmetic operators
        else if (input.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(display.getText());
            operator = input;
            display.setText("");
        } 
        // Handle parentheses
        else if (input.equals("(") || input.equals(")")) {
            display.setText(display.getText() + input);
        }
        // Handle logarithmic and square root functions
        else if(input.matches("log|sqrt")) {
            operator = input;
            display.setText(operator + "(");
        }
        // Handle trigonometric functions
        else if (input.matches("sin|cos|tan")) {
            operator = input;
            display.setText(operator + "(");
        }
        // Handle degree/radian mode switch
        else if (input.matches("RAD|DEG")) {
            isDegree = !isDegree; 
            //setDegreeMode(!isDegree);
        }
        // Handle equals button (calculate result)
        else if (input.equals("=")) {
            calculateResult();
        }
        // Handle clear button
        else if (input.equals("C")) {
            clearCalculator();
        }
        else if(input.equals("Del"))
        {
            if (display.getText().length() > 0) {
                display.setText(display.getText().substring(0, display.getText().length() - 1));
            }
        }
    }

    // Calculate the result based on current operation
    private void calculateResult() {
        double result = 0;

        // Handle single-operand operations (log, sqrt, trig functions)
        if(operator.equals("log") || operator.equals("sqrt") ||
           operator.equals("sin") || operator.equals("cos") || operator.equals("tan")) {
            
            // Close parentheses of unary operation
            display.setText(display.getText() + ")");
            // Get the string from the display 
            String originalString = display.getText(); 
            // Get only the number, no letters
            String newString = originalString.replaceAll("[a-zA-Z()]", "");
            // Store that number in num1
            num1 = Double.parseDouble(newString);

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
            num2 = Double.parseDouble(display.getText());
            
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

    // Clear calculator state
    private void clearCalculator() {
        display.setText("");
        num1 = num2 = 0;
        operator = "";
    }

    // Getter method for degree/radian mode
    public boolean getDegreeMode() {
        return isDegree;
    }

    // Setter method for degree/radian mode 
    public void setDegreeMode(boolean mode) {
        isDegree = mode; 
    }
} 