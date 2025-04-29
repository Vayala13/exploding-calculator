package Action;

public class Cosine implements Operation {
    @Override
    public double performOp(double num1) {
        return Math.cos(num1);
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return Math.cos(num1);  // Ignore second parameter for cosine
    }
} 