package Action;

// Class implementing multiplication operation
public class Multiplication implements Operation {
    @Override
    public double performOp(double num) {
        return num;  // For single operand, just return the number
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return num1 * num2;  // Multiply the two numbers
    }
} 