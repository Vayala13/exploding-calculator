package Action;

public class Tangent implements Operation {
    @Override
    public double performOp(double num1) {
        return Math.tan(num1);
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return Math.tan(num1);  // Ignore second parameter for tangent
    }
} 