package calculator.listeners;

import calculator.BusinessLogic;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationListener implements ActionListener {
    private final JTextField firstNumberText;
    private final JTextField operationText;
    private final JTextField secondNumberText;
    private final BusinessLogic businessLogic;

    public OperationListener(JTextField firstNumberText,
                             JTextField operationText,
                             JTextField secondNumberText) {
        this.firstNumberText = firstNumberText;
        this.operationText = operationText;
        this.secondNumberText = secondNumberText;
        this.businessLogic = new BusinessLogic();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "+":
                operationText.setText("+");
                break;
            case "-":
                operationText.setText("-");
                break;
            case "*":
                operationText.setText("*");
                break;
            case "/":
                operationText.setText("/");
                break;
            case "=":
                choseOperationAndCalculate();
                break;
        }

    }
    private void cleanFieldAndSetAnswer(double answer) {
        firstNumberText.setText("");
        operationText.setText("");
        secondNumberText.setText(String.valueOf(answer));
    }

    private void choseOperationAndCalculate() {
        switch (operationText.getText()) {
            case "+":
                cleanFieldAndSetAnswer(businessLogic
                        .plus(Double.parseDouble(firstNumberText.getText()),
                                Double.parseDouble(
                                        secondNumberText.getText())));
                break;
            case "-":
                cleanFieldAndSetAnswer(businessLogic
                        .minus(Double.parseDouble(firstNumberText.getText()),
                                Double.parseDouble(
                                        secondNumberText.getText())));
                break;
            case "*":
                cleanFieldAndSetAnswer(businessLogic
                        .multiply(Double.parseDouble(firstNumberText.getText()),
                                Double.parseDouble(
                                        secondNumberText.getText())));
                break;
            case "/":
                cleanFieldAndSetAnswer(businessLogic
                        .division(Double.parseDouble(firstNumberText.getText()),
                                Double.parseDouble(
                                        secondNumberText.getText())));
                break;
        }
    }

}
