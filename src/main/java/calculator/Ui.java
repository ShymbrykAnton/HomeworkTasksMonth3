package calculator;

import javax.swing.*;
import java.awt.*;

public class Ui {


    JButton clean;
    JButton backspace;

    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    JButton button8;
    JButton button9;
    JButton button0;
    JButton buttonDot;

    JButton plus;
    JButton minus;
    JButton multiply;
    JButton division;
    JButton enter;

    public Ui() {
       JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 253, 94, 131));

        JTextField firstNumberText = new JTextField();
        firstNumberText.setBounds(0, 10, 500, 100);
//        firstNumberText.setEditable(false);


        JTextField operationText = new JTextField();
        operationText.setBounds(0, 110, 500, 100);
//        operationText.setEditable(false);

        JTextField secondNumberText = new JTextField();
        secondNumberText.setBounds(0, 210, 500, 100);
//        secondNumberText.setEditable(false);

        frame.add(firstNumberText);
        frame.add(operationText);
        frame.add(secondNumberText);

        frame.setSize(500, 800);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Ui ui = new Ui();

    }
}
