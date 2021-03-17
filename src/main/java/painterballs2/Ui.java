package painterballs2;

import javax.swing.*;
import java.awt.*;

public class Ui {

    public Ui() {
        JFrame jFrame = new JFrame("SteelBalls");
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBackground(Color.DARK_GRAY);
        jFrame.setVisible(true);
        jFrame.setSize(1000,1000);
        jFrame.setLocationRelativeTo(null);
        BallContainer ballContainer =  new BallContainer(jFrame);
        jFrame.add(ballContainer);

        while (true){
            jFrame.repaint();
            ballContainer.moveBall();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
    }

}
