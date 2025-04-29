package Action;

// Class implementing sine operation
public class Sine implements Operation {
    @Override
    public double performOp(double num) {
        return Math.sin(num);  // Sine function
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return performOp(num1);  // Ignore second operand for sine
    }
} 