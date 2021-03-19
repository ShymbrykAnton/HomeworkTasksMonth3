package painterballs;

import javax.swing.*;
import java.awt.*;

public class Ui1 {

    public Ui1() {
        JFrame jFrame = new JFrame("PainterBalls");
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBackground(Color.PINK);
        jFrame.setVisible(true);
        jFrame.setSize(1000,1000);
        jFrame.setLocationRelativeTo(null);
        BallContainer1 ballContainer1 =  new BallContainer1(jFrame);
        jFrame.add(ballContainer1);

        while (true){
            jFrame.repaint();
            ballContainer1.moveBall();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Ui1 ui1 = new Ui1();
    }

}
