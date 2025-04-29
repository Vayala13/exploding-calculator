package Action;

// Interface defining the contract for all mathematical operations
public interface Operation {
    // Method for single-operand operations (like sin, cos, log)
    double performOp(double num);
    
    // Method for two-operand operations (like add, subtract)
    double performOp(double num1, double num2);
} 