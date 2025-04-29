import javax.swing.*; 
import java.awt.*;  
import java.awt.event.*;

public interface Operation {
    double performOp(double num1, double num2);
    double performOp(double num1);
}

public class Addition implements Operation {
    public double performOp(double num1, double num2) {
        return num1 + num2;
    }
}

public class Subtraction implements Operation {
    public double performOp(double num1, double num2) {
        return num1 - num2;
    }
}

public class Multiplication implements Operation {
    public double performOp(double num1, double num2) {
        return num1 * num2;
    }
}

public class Division implements Operation {
    public double performOp(double num1, double num2) {
        return num1 / num2;
    }
}

public class Logarithm implements Operation {
    public double performOp(double num1) {
        return Math.log10(num1);
    }
}

public class SquareRoot implements Operation {
    public double performOp(double num1) {
        return Math.sqrt(num1);
    }
}

public class Sine implements Operation {
    public double performOp(double num1) {
        return Math.sin(num1);
    }
}

public class Cosine implements Operation {
    public double performOp(double num1) {
        return Math.cos(num1);
    }
}

public class Tangent implements Operation {
    public double performOp(double num1) {
        return Math.tan(num1);
    }
}

// ******************************************************** Got this from main. can be deleted
public class Operation {
    // handle button clicks
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
            
            double result = 0;
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
};