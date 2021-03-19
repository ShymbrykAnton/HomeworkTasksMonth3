package painterballs2;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Ball2 extends Thread{
    private Ellipse2D.Double ball;
    private final Dimension ballDimension;
    private final Point2D.Double ballCoordinates;
    private int speedX;
    private int speedY;
    private  Color ballColor;
    private  int diameter;
    private int frameWidth;
    private int frameHeight;

    public Ball2(double x, double y, int diameter, int speedX, int speedY, int frameWidth, int frameHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.diameter = diameter;
        this.ballColor = getRandomColor();
        this.speedX = speedX;
        this.speedY = speedY;
        ballCoordinates = new Point2D.Double(x, y);
        ballDimension = new Dimension(diameter, diameter);
        createBallObject();
    }

    @Override
    public void run() {
        while (true) {
            updateBallPosition();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateBallPosition() {
        if (ballCoordinates.getX() + diameter >= frameWidth -(frameWidth *0.01)-speedX || ballCoordinates.getX() <= 0) {
            speedX *= -1;
        } else if (ballCoordinates.getY() + diameter >= frameHeight -(frameHeight *0.035) -speedY || ballCoordinates.getY() <= 0) {
            speedY *= -1;
        }
        ballCoordinates.setLocation(ballCoordinates.getX()+speedX, ballCoordinates.getY()+speedY);
        setCurrentBallPosition();
    }

    private void createBallObject() {
        ball = new Ellipse2D.Double(ballCoordinates.getX(),
                ballCoordinates.getY(), ballDimension.getWidth(),
                ballDimension.getHeight());
    }

    private void setCurrentBallPosition() {
        ball.setFrame(ballCoordinates, ballDimension);
    }

    private Color getRandomColor() {
        return new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    }

    public int getDiameter() {
        return diameter;
    }

    public Point2D.Double getBallCoordinates() {
        return ballCoordinates;
    }

    public Color getBallColor() {
        return ballColor;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void setBallColor(Color ballColor) {
        this.ballColor = ballColor;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }
}
