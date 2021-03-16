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
        Container container =  new Container();
        container.addMouseListener(new BallListener());
        jFrame.add(container);
        jFrame.setLocationRelativeTo(null);
        while (true){
            jFrame.repaint();
            container.moveBall();
        }

    }

    public static void main(String[] args) {
        Ui ui = new Ui();
    }

}
