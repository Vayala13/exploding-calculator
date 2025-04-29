package Action;

public class Addition implements Operation {
    @Override
    public double performOp(double num1, double num2) {
        return num1 + num2;
    }
    
    @Override
    public double performOp(double num1) {
        return num1; // Single operand addition just returns the number
    }
} 