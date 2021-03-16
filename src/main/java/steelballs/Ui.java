package steelballs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Ui {
    public Ui() {
        JFrame jFrame = new JFrame("SteelBalls");
        jFrame.setVisible(true);
        jFrame.setSize(1000, 1000);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(new Canvas(), BorderLayout.CENTER);
    }

}
