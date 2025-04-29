package Action;

public class SquareRoot implements Operation {
    @Override
    public double performOp(double num1) {
        return Math.sqrt(num1);
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return Math.sqrt(num1);  // Ignore second parameter for square root
    }
} 