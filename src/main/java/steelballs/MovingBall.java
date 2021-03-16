package steelballs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MovingBall extends JPanel {

        BufferedImage img;
        int WIDTH;
        int HEIGHT;
        int X, Y, dx, dy, R;

        MovingBall() {
            WIDTH = 640;
            HEIGHT = 480;

            X = 0;
            Y = 0;
            dx = 1;
            dy = 1;
            R = 15;

            JFrame frame = new JFrame("movement");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            img = new BufferedImage(WIDTH + R, HEIGHT + R, BufferedImage.TYPE_INT_BGR);

            frame.add(this);
            frame.pack();

            javax.swing.Timer timer = new javax.swing.Timer(20, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (X + R < 0 | X + R > WIDTH) {
                        dx *= (-1);
                    }

                    if (Y + R < 0 | Y + R > HEIGHT) {
                        dy *= (-1);
                    }

                    X += dx;
                    Y += dy;

                    paintOnImg();
                }
            });

            timer.start();

            frame.setResizable(false);
            frame.setVisible(true);

        }

        public void paintOnImg() {
            Graphics g = img.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WIDTH + R, HEIGHT + R); //cleaning the image;

            g.setColor(Color.RED);
            g.fillOval(X + R, Y + R, R, R);

            g.dispose();

            repaint();
        }

        @Override
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }

        @Override
        public void update(Graphics g) {
            paint(g);
        }

        public static void main(String[] args) {
            new MovingBall();
        }
    }

