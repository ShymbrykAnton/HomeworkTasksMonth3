package painterballs3;

import javax.swing.*;
import java.awt.*;

public class Ui3 {

    public Ui3() {
        JFrame jFrame = new JFrame("PainterBalls3");
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBackground(Color.PINK);
        jFrame.setVisible(true);
        jFrame.setSize(1000,1000);
        jFrame.setLocationRelativeTo(null);
        BallContainer3 ballContainer3 =  new BallContainer3(jFrame);
        jFrame.add(ballContainer3);

        while (true){
            jFrame.repaint();
            ballContainer3.moveBall();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Ui3 ui3 = new Ui3();
    }

}
