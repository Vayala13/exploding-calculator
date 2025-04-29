package Action;

// Class implementing cosine operation
public class Cosine implements Operation {
    @Override
    public double performOp(double num) {
        return Math.cos(num);  // Cosine function
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return performOp(num1);  // Ignore second operand for cosine
    }
} 