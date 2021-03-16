package steelballs;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {
    Ellipse2D.Double ball;
    double x;
    double y;
    int speedX;
    int speedY;
    double radius;
    Color ballColor;

    public Ellipse2D.Double getBall() {
        return ball;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public Color getBallColor() {
        return ballColor;
    }

    public Ball(double x, double y){
        this.x = x;
        this.y = y;
        this.radius = getRandomRadius();
        this.ballColor = getRandomColor();
        this.speedX = getRandomSpeed();
        this.speedY = getRandomSpeed();
        this.ball = new Ellipse2D.Double(x,y,radius,radius);

    }
    private double getRandomRadius(){
        return (Math.random()+1)*(1000.0/8.0);
    }
    private Color getRandomColor(){
        return new Color((int) (Math.random() * 255),
                (int) (Math.random() * 255), (int) (Math.random() * 255));
    }
    private int getRandomSpeed(){
        return (int)(Math.random()+1)*(1000/6);
    }

    public double getRadius() {
        return radius;
    }
}
