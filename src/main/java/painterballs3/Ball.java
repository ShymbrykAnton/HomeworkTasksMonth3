package painterballs3;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Ball extends Thread{
    private Ellipse2D.Double ball;
    private final Dimension ballDimension;
    private final Point2D.Double ballCoordinates;
    private int speedX;
    private int speedY;
    private  Color ballColor;
    private  int radius;
    private final int width;
    private final int height;

    public Ball(double x, double y, int radius, int speedX, int speedY, int width, int height) {
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.ballColor = getRandomColor();
        this.speedX = speedX;
        this.speedY = speedY;
        ballCoordinates = new Point2D.Double(x, y);
        ballDimension = new Dimension(radius, radius);
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
        if (ballCoordinates.getX() + radius >= width-(width*0.01)-speedX) {
            speedX *= -1;
            ballCoordinates.setLocation(width-(width*0.01) - radius, ballCoordinates.getY() + speedY);
        } else if (ballCoordinates.getY() + radius >= height-(height*0.035) -speedY) {
            speedY *= -1;
            ballCoordinates.setLocation(ballCoordinates.getX() + speedX,
                    height-(height*0.035) - radius);
        } else if (ballCoordinates.getX() <= 0){
            speedX *= -1;
            ballCoordinates.setLocation( speedX, ballCoordinates.getY() + speedY);
        } else if (ballCoordinates.getY() <= 0) {
            speedY *= -1;
            ballCoordinates
                    .setLocation(ballCoordinates.getX() + speedX, speedY);
        } else {
            ballCoordinates.setLocation(ballCoordinates.getX()+speedX, ballCoordinates.getY()+speedY);
        }
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

    public int getRadius() {
        return radius;
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

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }
}
