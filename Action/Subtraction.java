package Action;

// Class implementing subtraction operation
public class Subtraction implements Operation {
    @Override
    public double performOp(double num) {
        return -num;  // For single operand, return negative of the number
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return num1 - num2;  // Subtract second number from first
    }
} 