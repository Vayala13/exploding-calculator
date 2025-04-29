package Action;

public class Logarithm implements Operation {
    @Override
    public double performOp(double num1) {
        return Math.log10(num1);
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return Math.log10(num1);  // Ignore second parameter for logarithm
    }
} 