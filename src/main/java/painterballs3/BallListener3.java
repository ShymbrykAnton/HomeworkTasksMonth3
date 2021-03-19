package painterballs3;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

public class BallListener3 extends MouseAdapter {
    private volatile List<Ball3> ball3s;
    private final Random random = new Random();
    private final JFrame jFrame;

    public BallListener3(List<Ball3> ball3s, JFrame frame) {
        this.jFrame = frame;
        this.ball3s = ball3s;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jFrame.setSize(jFrame.getWidth() + 1, jFrame.getHeight() + 1);
        int radius = getRandomRadius();
        int x = e.getX() - radius / 2;
        int y = e.getY() - radius / 2;
        int speedX = getRandomSpeed(jFrame.getWidth());
        int speedY = getRandomSpeed(jFrame.getHeight());
        Ball3 ball3 = new Ball3(x, y, radius, speedX, speedY, jFrame.getWidth(),
                jFrame.getHeight());
        Thread thread = new Thread(ball3);
        ball3s.add(ball3);
        thread.start();

    }

    private int getRandomRadius() {
        return random.nextInt((Math.min(jFrame.getWidth(), jFrame.getHeight())/8) + 1);
    }

    private int getRandomSpeed(int speed) {
        int speedConst = speed / 60;
        int randomSpeed = random.nextInt(speedConst * 2) - speedConst;
        return randomSpeed!=0?randomSpeed:getRandomSpeed(speed);
    }
}
