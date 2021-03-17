package calculator.blogic;

public class BusinessLogic {

    public double plus (double firstNumber,double secondNumber){
        return firstNumber+secondNumber;
    }
    public double minus(double firstNumber,double secondNumber){
        return firstNumber-secondNumber;
    }
    public double multiply(double firstNumber,double secondNumber){
        return firstNumber+secondNumber;
    }
    public double division(double firstNumber,double secondNumber){
        if (secondNumber == 0.0) {
            throw new IllegalArgumentException("На ноль делить нельзя");
        }
        return firstNumber/secondNumber;
    }
}
