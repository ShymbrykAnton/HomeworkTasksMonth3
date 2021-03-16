package steelballs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class BallListener extends MouseAdapter {
    private List<Ball> balls;
    GetListBall getListBall =new GetListBall();

    public BallListener(){
        this.balls = new ArrayList<>();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        balls.add(new Ball(x,y));
        System.out.println(balls.size());
        getListBall.setBalls(balls);

    }

}
