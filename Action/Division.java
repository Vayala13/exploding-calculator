package Action;

// Class implementing division operation
public class Division implements Operation {
    @Override
    public double performOp(double num) {
        return 1.0 / num;  // For single operand, return reciprocal
    }
    
    @Override
    public double performOp(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return num1 / num2;  // Divide first number by second
    }
} 