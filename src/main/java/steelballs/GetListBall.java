package steelballs;

import java.util.ArrayList;
import java.util.List;

public class GetListBall {


    private List<Ball> balls = new ArrayList<>();

    public void setBalls(List<Ball> balls) {
        this.balls = balls;
    }

    public  List<Ball> getBalls() {
        return balls;
    }
}
