package Action;

public class Sine implements Operation {
    @Override
    public double performOp(double num1) {
        return Math.sin(num1);
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return Math.sin(num1);  // Ignore second parameter for sine
    }
} 