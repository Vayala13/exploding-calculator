package Action;

public interface Operation2 {
    double performOp(double num1, double num2);
    double performOp(double num1);
}


class Subtraction implements Operation2 {
    @Override
    public double performOp(double num1, double num2) {
        return num1 - num2;
    }
    @Override
    public double performOp(double num1){}
}

public class Multiplication implements Operation2 {
    @Override
    public double performOp(double num1, double num2) {
        return num1 * num2;
    }
}

public class Division implements Operation2 {
    @Override
    public double performOp(double num1, double num2) {
        return num1 / num2;
    }
}

public class Logarithm implements Operation2 {
    @Override
    public double performOp(double num1) {
        return Math.log10(num1);
    }
}

public class SquareRoot implements Operation2 {
    @Override
    public double performOp(double num1) {
        return Math.sqrt(num1);
    }
}

public class Sine implements Operation2 {
    @Override
    public double performOp(double num1) {
        return Math.sin(num1);
    }
}

public class Cosine implements Operation2 {
    @Override
    public double performOp(double num1) {
        return Math.cos(num1);
    }
}

public class Tangent implements Operation2 {
    @Override
    public double performOp(double num1) {
        return Math.tan(num1);
    }
}
