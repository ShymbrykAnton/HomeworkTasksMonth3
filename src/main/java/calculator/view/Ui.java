package calculator.view;

import calculator.listeners.EditTextFields;
import calculator.listeners.NumsListener;
import calculator.listeners.OperationListener;
import javax.swing.*;
import java.awt.*;

public class Ui {

    public Ui() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 253, 94, 131));

        JTextField firstNumberText = new JTextField();
        firstNumberText.setBounds(0, 0, 483, 80);
        firstNumberText.setEditable(false);
        firstNumberText.setBorder(null);
        firstNumberText.setHorizontalAlignment(SwingConstants.RIGHT);


        JTextField operationText = new JTextField();
        operationText.setBounds(0, 80, 483, 80);
        operationText.setEditable(false);
        operationText.setBorder(null);
        operationText.setHorizontalAlignment(SwingConstants.RIGHT);

        JTextField secondNumberText = new JTextField();
        secondNumberText.setBounds(0, 160, 483, 80);
        secondNumberText.setEditable(false);
        secondNumberText.setBorder(null);
        secondNumberText.setHorizontalAlignment(SwingConstants.RIGHT);

        JButton cleanBtn = new JButton("C");
        cleanBtn.setBounds(55, 260, 80, 80);

        JButton backspaceBtn = new JButton("Backspace");
        backspaceBtn.setBounds(155, 260, 180, 80);

        JButton divisionBtn = new JButton("/");
        divisionBtn.setBounds(355, 260, 80, 80);
        Icon icon =
                new ImageIcon("./src/main/java/calculator/assets/division.png");
        divisionBtn.setIcon(icon);

        JButton oneBtn = new JButton("1");
        oneBtn.setBounds(55, 360, 80, 80);

        JButton twoBtn = new JButton("2");
        twoBtn.setBounds(155, 360, 80, 80);

        JButton threeBtn = new JButton("3");
        threeBtn.setBounds(255, 360, 80, 80);

        JButton plusBtn = new JButton("+");
        Icon icon1 =
                new ImageIcon("./src/main/java/calculator/assets/plus.png");
        plusBtn.setIcon(icon1);
        plusBtn.setBounds(355, 360, 80, 80);

        JButton fourBtn = new JButton("4");
        fourBtn.setBounds(55, 460, 80, 80);

        JButton fiveBtn = new JButton("5");
        fiveBtn.setBounds(155, 460, 80, 80);

        JButton sixBtn = new JButton("6");
        sixBtn.setBounds(255, 460, 80, 80);

        JButton minusBtn = new JButton("-");
        Icon icon2 =
                new ImageIcon("./src/main/java/calculator/assets/minus.png");
        minusBtn.setIcon(icon2);
        minusBtn.setBounds(355, 460, 80, 80);

        JButton sevenBtn = new JButton("7");
        sevenBtn.setBounds(55, 560, 80, 80);

        JButton eightBtn = new JButton("8");
        eightBtn.setBounds(155, 560, 80, 80);

        JButton nineBtn = new JButton("9");
        nineBtn.setBounds(255, 560, 80, 80);

        JButton multiplyBtn = new JButton("*");
        Icon icon3 =
                new ImageIcon("./src/main/java/calculator/assets/multiply.png");
        multiplyBtn.setIcon(icon3);
        multiplyBtn.setBounds(355, 560, 80, 80);

        JButton enter = new JButton("=");
        enter.setBounds(55, 660, 180, 80);

        JButton zeroBtn = new JButton("0");
        zeroBtn.setBounds(255, 660, 80, 80);

        JButton dotBtn = new JButton(".");
        Icon icon4 = new ImageIcon("./src/main/java/calculator/assets/dot.png");
        dotBtn.setIcon(icon4);
        dotBtn.setBounds(355, 660, 80, 80);

        frame.add(firstNumberText);
        frame.add(operationText);
        frame.add(secondNumberText);
        frame.add(cleanBtn);
        frame.add(backspaceBtn);
        frame.add(divisionBtn);
        frame.add(oneBtn);
        frame.add(twoBtn);
        frame.add(threeBtn);
        frame.add(plusBtn);
        frame.add(fourBtn);
        frame.add(fiveBtn);
        frame.add(sixBtn);
        frame.add(minusBtn);
        frame.add(sevenBtn);
        frame.add(eightBtn);
        frame.add(nineBtn);
        frame.add(multiplyBtn);
        frame.add(enter);
        frame.add(zeroBtn);
        frame.add(dotBtn);

        Font font = firstNumberText.getFont();
        firstNumberText.setFont(font.deriveFont(15.0f));
        operationText.setFont(font.deriveFont(15.0f));
        secondNumberText.setFont(font.deriveFont(15.0f));
        cleanBtn.setFont(font.deriveFont(25.0f));
        backspaceBtn.setFont(font.deriveFont(25.0f));
        oneBtn.setFont(font.deriveFont(25.0f));
        twoBtn.setFont(font.deriveFont(25.0f));
        threeBtn.setFont(font.deriveFont(25.0f));
        fourBtn.setFont(font.deriveFont(25.0f));
        fiveBtn.setFont(font.deriveFont(25.0f));
        sixBtn.setFont(font.deriveFont(25.0f));
        sevenBtn.setFont(font.deriveFont(25.0f));
        eightBtn.setFont(font.deriveFont(25.0f));
        nineBtn.setFont(font.deriveFont(25.0f));
        zeroBtn.setFont(font.deriveFont(25.0f));
        enter.setFont(font.deriveFont(80.0f));
        divisionBtn.setFont(font.deriveFont(0.1f));
        plusBtn.setFont(font.deriveFont(0.1f));
        minusBtn.setFont(font.deriveFont(0.1f));
        multiplyBtn.setFont(font.deriveFont(0.1f));
        dotBtn.setFont(font.deriveFont(0.1f));
        firstNumberText.setFont(font.deriveFont(40.0f));
        operationText.setFont(font.deriveFont(40.0f));
        secondNumberText.setFont(font.deriveFont(40.0f));

        NumsListener numsListener = new NumsListener(firstNumberText, operationText, secondNumberText);
        oneBtn.addActionListener(numsListener);
        twoBtn.addActionListener(numsListener);
        threeBtn.addActionListener(numsListener);
        fourBtn.addActionListener(numsListener);
        fiveBtn.addActionListener(numsListener);
        sixBtn.addActionListener(numsListener);
        sevenBtn.addActionListener(numsListener);
        eightBtn.addActionListener(numsListener);
        nineBtn.addActionListener(numsListener);
        zeroBtn.addActionListener(numsListener);
        dotBtn.addActionListener(numsListener);

        OperationListener operationListener = new OperationListener(firstNumberText, operationText, secondNumberText);
        divisionBtn.addActionListener(operationListener);
        plusBtn.addActionListener(operationListener);
        minusBtn.addActionListener(operationListener);
        multiplyBtn.addActionListener(operationListener);
        enter.addActionListener(operationListener);

        EditTextFields editTextFields = new EditTextFields(firstNumberText, operationText, secondNumberText);
        cleanBtn.addActionListener(editTextFields);
        backspaceBtn.addActionListener(editTextFields);

        frame.setSize(500, 800);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }


    public static void main(String[] args) {
        new Ui();
    }
}
