package steelballs;

import javax.swing.*;
import java.awt.*;

public class Ui {

    public Ui() {
        JFrame jFrame = new JFrame("SteelBalls");
        BallContainer ballContainer =  new BallContainer();

        jFrame.add(ballContainer);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBackground(Color.PINK);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);


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
