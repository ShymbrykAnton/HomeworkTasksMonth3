package painterballs;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class BallListener1 extends MouseAdapter {
    private final ArrayList<Ball1> ball1s;
    private final Random random = new Random();
    private final JFrame jFrame;

    public BallListener1(ArrayList<Ball1> ball1s, JFrame frame) {
        this.jFrame = frame;
        this.ball1s = ball1s;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jFrame.setSize(jFrame.getWidth()+1,jFrame.getHeight()+1);
        int radius = getRandomRadius();
        int x = e.getX() - radius / 2;
        int y = e.getY() - radius / 2;
        int speedX = getRandomSpeed(jFrame.getWidth());
        int speedY = getRandomSpeed(jFrame.getHeight());
        Ball1 ball1 = new Ball1(x, y, radius, speedX, speedY, jFrame.getWidth(), jFrame.getHeight());
        ball1s.add(ball1);
        Thread thread = new Thread(ball1);
        thread.start();
        
    }

    private int getRandomRadius(){
        return random.nextInt(Math.min(jFrame.getWidth(),jFrame.getHeight())/8 + 1);
    }

    private int getRandomSpeed(int speed) {
        int speedConst = speed / 100;
        int randomSpeed = random.nextInt(speedConst * 2) - speedConst;
        return randomSpeed!=0?randomSpeed:getRandomSpeed(speed);
    }
}
