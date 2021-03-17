package calculator.listeners;

import calculator.blogic.BusinessLogic;
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
                resetTextFields();
                break;
            case "-":
                operationText.setText("-");
                resetTextFields();
                break;
            case "*":
                operationText.setText("*");
                resetTextFields();
                break;
            case "/":
                operationText.setText("/");
                resetTextFields();
                break;
            case "=":
                choseOperationAndCalculate();
                break;
        }

    }

    private void resetTextFields() {
        firstNumberText.setText(secondNumberText.getText());
        secondNumberText.setText("");
    }

    private void cleanFieldAndSetAnswer(double answer) {
        firstNumberText.setText("");
        operationText.setText("");
        String answerStr = String.valueOf(answer);
        String afterDot = answerStr.substring(answerStr.lastIndexOf(".")+1);
        if (afterDot.length() == 1 && afterDot.equals("0")) {
            secondNumberText.setText(String.valueOf((int) answer));
        } else {
            secondNumberText.setText(String.valueOf(answer));
        }
    }

    private void choseOperationAndCalculate() {
        switch (operationText.getText()) {
            case "+":
                cleanFieldAndSetAnswer(businessLogic
                        .plus(Double.parseDouble(firstNumberText.getText()),
                                Double.parseDouble(secondNumberText.getText())));
                break;
            case "-":
                cleanFieldAndSetAnswer(businessLogic
                        .minus(Double.parseDouble(firstNumberText.getText()),
                                Double.parseDouble(secondNumberText.getText())));
                break;
            case "*":
                cleanFieldAndSetAnswer(businessLogic
                        .multiply(Double.parseDouble(firstNumberText.getText()),
                                Double.parseDouble(secondNumberText.getText())));
                break;
            case "/":
                try {
                    cleanFieldAndSetAnswer(businessLogic
                            .division(Double.parseDouble(
                                    firstNumberText.getText()),
                                    Double.parseDouble(
                                            secondNumberText.getText())));
                } catch (IllegalArgumentException e) {
                    firstNumberText.setText("");
                    operationText.setText("");
                    secondNumberText.setText("");
                    Icon errorIcon = new ImageIcon("./src/main/java/calculator/assets/Error.png");
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, errorIcon);
                }
                break;
        }
    }
}
