package painterballs2;

import javax.swing.*;
import java.awt.*;

public class Ui2 {

    public Ui2() {
        JFrame jFrame = new JFrame("PainterBalls2");
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBackground(Color.PINK);
        jFrame.setVisible(true);
        jFrame.setSize(1000,1000);
        jFrame.setLocationRelativeTo(null);
        BallContainer2 ballContainer2 =  new BallContainer2(jFrame);
        jFrame.add(ballContainer2);

        while (true){
            jFrame.repaint();
            ballContainer2.moveBall();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Ui2 ui2 = new Ui2();
    }

}
