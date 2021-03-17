package calculator.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTextFields implements ActionListener {
   private final JTextField firstNumberText;
   private final JTextField operationText;
   private final JTextField secondNumberText;
    public EditTextFields(JTextField firstNumberText,JTextField operationText,JTextField secondNumberText){
        this.firstNumberText = firstNumberText;
        this.operationText = operationText;
        this.secondNumberText = secondNumberText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "C":
                firstNumberText.setText("");
                secondNumberText.setText("");
                operationText.setText("");
                break;
            case "Backspace":
                secondNumberText.setText(secondNumberText.getText().substring(0,secondNumberText.getText().length()-1));
                break;
        }
    }
}
