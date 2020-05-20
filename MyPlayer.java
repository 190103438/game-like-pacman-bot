import java.util.Random;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyPlayer implements Player {
    private Circle ball;
    private Map map;
    private  Position position;
    //constructor for player
    MyPlayer(Map x){
        map = x;
        position = x.getStartPosition();
        double unit = map.getUnit();
        int varX = x.getStartPosition().getX();
        int varY = x.getStartPosition().getY();
        //set balls radius and center position
        ball = new Circle(varX * unit + unit / 2.0D, varY * unit + unit / 2.0D, unit / 2.0D);
        ball.setFill(Color.RED);
        //add it to pane
        x.getChildren().add(ball);
    }


    //move right method
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
