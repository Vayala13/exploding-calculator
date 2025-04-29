package Action;

public class Division implements Operation {
    @Override
    public double performOp(double num1, double num2) {
        return num2 != 0 ? num1 / num2 : 0;  // Prevent division by zero
    }

    @Override
    public double performOp(double num1) {
        return num1;  // Single operand division just returns the number
    }
} 