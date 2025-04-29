package Action;

// Class implementing addition operation
public class Addition implements Operation {
    @Override
    public double performOp(double num) {
        return num;  // For single operand, just return the number
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return num1 + num2;  // Add the two numbers
    }
} 