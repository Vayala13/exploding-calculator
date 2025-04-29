package Action;

// Class implementing square root operation
public class SquareRoot implements Operation {
    @Override
    public double performOp(double num) {
        if (num < 0) {
            throw new ArithmeticException("Square root of negative number");
        }
        return Math.sqrt(num);  // Square root
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return performOp(num1);  // Ignore second operand for square root
    }
} 