package calculator;

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
        return secondNumber!=0?firstNumber/secondNumber:0;
    }
}
