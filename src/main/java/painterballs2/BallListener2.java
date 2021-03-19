package painterballs2;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

public class BallListener2 extends MouseAdapter {
    private volatile List<Ball2> ball2s;
    private final Random random = new Random();
    private final JFrame jFrame;

    public BallListener2(List<Ball2> ball2s, JFrame frame) {
        this.jFrame = frame;
        this.ball2s = ball2s;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jFrame.setSize(jFrame.getWidth() + 1, jFrame.getHeight() + 1);
        int radius = getRandomRadius();
        int x = e.getX() - radius / 2;
        int y = e.getY() - radius / 2;
        int speedX = getRandomSpeed(jFrame.getWidth());
        int speedY = getRandomSpeed(jFrame.getHeight());
        Ball2 ball2 = new Ball2(x, y, radius, speedX, speedY, jFrame.getWidth(),
                jFrame.getHeight());
        Thread thread = new Thread(ball2);
        ball2s.add(ball2);
        thread.start();

    }

    private int getRandomRadius() {
        return random.nextInt(
                Math.min(jFrame.getWidth(), jFrame.getHeight()) / 8 + 1);
    }

    private int getRandomSpeed(int speed) {
        int speedConst = speed / 100;
        int randomSpeed = random.nextInt(speedConst * 2) - speedConst;
        return randomSpeed!=0?randomSpeed:getRandomSpeed(speed);
    }
}
