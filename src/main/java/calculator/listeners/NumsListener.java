package calculator.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumsListener implements ActionListener {
   private final JTextField firstNumberText;
   private final JTextField operationText;
   private final JTextField secondNumberText;

    public NumsListener(JTextField firstNumberText,JTextField operationText,JTextField secondNumberText){
        this.firstNumberText = firstNumberText;
        this.operationText = operationText;
        this.secondNumberText = secondNumberText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String textInFieldNow = secondNumberText.getText();
        String setString = textInFieldNow.concat(e.getActionCommand());
        if (chekJustOneDot(setString)) {
            secondNumberText.setText(setString);
        } else {
            secondNumberText.setText(textInFieldNow);
        }
    }

    private boolean chekJustOneDot(String stringForChek) {
        return stringForChek.matches("^[0-9]*\\.?[0-9]*$");
    }

}
