package steelballs;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class BallListener extends MouseAdapter {
    private final ArrayList<Ball> balls;
    private final Random random = new Random();
    private int width;
    private int height;
    private JFrame jFrame;

    public BallListener(ArrayList<Ball> balls, JFrame frame) {
        this.jFrame = frame;
        this.width = width;
        this.height = height;
        this.balls = balls;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jFrame.setSize(jFrame.getWidth()+1,jFrame.getHeight()+1);
        int radius = getRandomRadius();
        int x = e.getX() - radius / 2;
        int y = e.getY() - radius / 2;
        int speedX = getRandomSpeed(jFrame.getWidth());
        int speedY = getRandomSpeed(jFrame.getHeight());
        Ball ball = new Ball(x, y, radius, speedX, speedY, jFrame.getWidth(), jFrame.getHeight());
        balls.add(ball);
        Thread thread = new Thread(ball);
        thread.start();
        
    }

    private int getRandomRadius(){
        return random.nextInt(Math.min(jFrame.getWidth(),jFrame.getHeight())/8 + 1);
    }

    private int getRandomSpeed(int speed) {
        int speedConst = speed / 100;
        return random.nextInt(speedConst * 2) - speedConst;
    }
}
