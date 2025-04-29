package Action;

// Class implementing tangent operation
public class Tangent implements Operation {
    @Override
    public double performOp(double num) {
        return Math.tan(num);  // Tangent function
    }
    
    @Override
    public double performOp(double num1, double num2) {
        return performOp(num1);  // Ignore second operand for tangent
    }
} 