package steelballs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Canvas extends JComponent implements Runnable {
    private Shape shape;
    private int radius;

    public Canvas() {

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int direction = (int) ((Math.random() + 1) * 4);
                while (y != 0) {
                    x++;
                    y++;
                    shape = drawBrush(x, y);
                    repaint();
                }
            }

        });
    }

    Timer timer = new Timer(50000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphSettings = (Graphics2D) g;
        graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphSettings.setStroke(new BasicStroke(4));
        if (shape != null) {
        graphSettings.draw(shape);
        }
    }

    private Ellipse2D.Float drawBrush(int x1, int y1) {
        radius = (int) (Math.random() * this.getWidth() / 8);
        return new Ellipse2D.Float(x1 - radius / 2, y1 - radius / 2, radius,
                radius);
    }


    @Override
    public void run() {
        while (true) {
            try {
                shape = drawBrush(getX() + 1, getY() + 1);
                repaint();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
//    public void move() {
//        shape=drawBrush()
//
//    }
}
