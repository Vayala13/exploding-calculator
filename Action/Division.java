package Action;

import Action.Explosion.Pictures;

// Class implementing division operation
public class Division implements Operation {
    @Override
    public double performOp(double num) {
        return 1.0 / num;  // For single operand, return reciprocal
    }
    
    @Override
    public double performOp(double num1, double num2) {
        if (num2 < 0.1 && num2 > -0.1) {
            //System.out.print("Hello");
            //throw new ArithmeticException("Division by zero");
            Pictures pic = new Pictures(); 
        }
        return num1 / num2;  // Divide first number by second
    }
} 