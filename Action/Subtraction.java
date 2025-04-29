package Action;

public class Subtraction implements Operation {
    @Override
    public double performOp(double num1, double num2) {
        return num1 - num2;
    }
    
    @Override
    public double performOp(double num1) {
        return -num1;  // Single operand subtraction negates the number
    }
} 