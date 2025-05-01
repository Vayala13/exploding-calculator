package Action;

// Class implementing natural logarithm operation
public class Logarithm implements Operation {
    @Override
    public double performOp(double num) {
        if (num <= 0) {
            throw new ArithmeticException("Logarithm of non-positive number");
        }
        return Math.log10(num);
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return performOp(num1);  // Ignore second operand for logarithm
    }
} 