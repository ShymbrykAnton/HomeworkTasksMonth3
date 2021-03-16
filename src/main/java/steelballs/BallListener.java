package steelballs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class BallListener extends MouseAdapter {
    private final ArrayList<Ball> balls;
    private final Random random = new Random();

    public BallListener(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int radius = getRandomRadius();
        int x = e.getX() - radius / 2;
        int y = e.getY() - radius / 2;
        int speedX = getRandomSpeed();
        int speedY = getRandomSpeed();
        Ball ball = new Ball(x, y, radius, speedX, speedY);
        balls.add(ball);
        Thread thread = new Thread(ball);
        thread.start();
    }

    private int getRandomRadius(){
        return random.nextInt(1000/8 + 1);
    }

    private int getRandomSpeed() {
        int speedConst = 1000 / 100;
        return random.nextInt(speedConst * 2) - speedConst;
    }
}
