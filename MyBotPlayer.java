import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.util.*;

public class MyBotPlayer implements BotPlayer{

    private Circle ball;
    private Map map;
    private  Position position;

    int used[][];
    int dp[][];
    Position go;

    MyBotPlayer(Map x){
        map = x;
        position = x.getStartPosition();
        go = new Position(1, 1);
        double unit = map.getUnit();
        int varX = x.getStartPosition().getX();
        int varY = x.getStartPosition().getY();
        //set balls radius and center position
        ball = new Circle(varX * unit + unit / 2.0D, varY * unit + unit / 2.0D, unit / 2.0D);
        ball.setFill(Color.RED);
        //add it to pane
        x.getChildren().add(ball);
    }

    public void feed(Food f){
        Thread var1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException var3) {
                }
                go.setX(f.getPosition().getY());
                go.setY(f.getPosition().getX());
                find();
                eat();
            }
        });
        var1.start();
    }
    public void eat(){
        while(true){
            int x = position.getX(), y = position.getY();
            if(x == go.getX() && y == go.getY()) break;
            if(dp[x][y] == 1){
                moveUp();
            }
            if(dp[x][y] == 2){
                moveDown();
            }
            if(dp[x][y] == 3){
                moveLeft();
            }
            if(dp[x][y] == 4){
                moveRight();
            }
            try {
                Thread.sleep(200L);
            } catch (InterruptedException var3) {
            }
        }
    }
    public void find(){
        dp = new int[map.getSize()][map.getSize()];
        used = new int[map.getSize()][map.getSize()];
        ArrayDeque<Integer> st = new ArrayDeque<>();
        st.add(go.getX() * map.getSize() + go.getY());
        while(!st.isEmpty()){
            int num = st.peek();
            st.poll();
            int x = num / map.getSize(), y = num % map.getSize();
            if(x + 1 < map.getSize() && map.getValue(x + 1, y) != 1 && used[x + 1][y] == 0){
                used[x + 1][y] = 1;
                dp[x + 1][y] = 1;
                st.add((x + 1) * map.getSize() + y);
            }
            if(x - 1 >= 0 && map.getValue(x - 1, y) != 1 && used[x - 1][y] == 0){
                used[x - 1][y]  = 1;
                dp[x - 1][y] = 2;
                st.add((x - 1) * map.getSize() + y);
            }
            if(y + 1 < map.getSize() && map.getValue(x, y + 1) != 1 && used[x][y + 1] == 0){
                used[x][y + 1]  = 1;
                dp[x][y + 1] = 3;
                st.add(x * map.getSize() + y + 1);
            }
            if(y - 1 >= 0 && map.getValue(x, y - 1) != 1 && used[x][y - 1] == 0){
                used[x][y - 1]  = 1;
                dp[x][y - 1] = 4;
                st.add(x * map.getSize() + y - 1);
            }
        }
        return ;
    }

    public void moveRight(){
        //checking for borders
        if(position.getY() + 1 >= map.getSize() || map.getValue(position.getX(), position.getY() + 1) == 1){
            System.out.println("Invalid position!");
            return ;
        }
        //change it position and balls positions in pane
        position.setY(position.getY() + 1);
        ball.setCenterX(ball.getCenterX() + map.getUnit());
    }
    //move left method
    public void moveLeft(){
        //checking for borders
        if(position.getY() - 1 < 0 || map.getValue(position.getX(), position.getY() - 1) == 1){
            System.out.println("Invalid position!");
            return ;
        }
        //change it position and balls positions in pane
        position.setY(position.getY() - 1);
        ball.setCenterX(ball.getCenterX() - map.getUnit());
    }
    //move up method
    public void moveUp(){
        //checking for borders
        if(position.getX() - 1 < 0 || map.getValue(position.getX() - 1, position.getY()) == 1){
            System.out.println("Invalid position!");
            return ;
        }
        //change it position and balls positions in pane
        position.setX(position.getX() - 1);
        ball.setCenterY(ball.getCenterY() - map.getUnit());
    }
    //move down method
    public void moveDown(){
        //checking for borders
        if(position.getX() + 1 >= map.getSize() || map.getValue(position.getX() + 1, position.getY()) == 1){
            System.out.println("Invalid position!");
            return ;
        }
        //change it position and balls positions in pane
        position.setX(position.getX() + 1);
        ball.setCenterY(ball.getCenterY() + map.getUnit());
    }

    public Position getPosition(){
        return position;
    }
}
