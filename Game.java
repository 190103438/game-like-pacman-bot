import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Scanner;

public class Game extends Application{

    //Main class
    public void start(Stage primaryStage) throws Exception{
        //read file name containing map
        Scanner cin = new Scanner(System.in);
        String name = cin.next();

        //create map
        Map map = new Map(name);
        //create player
        MyBotPlayer player = new MyBotPlayer(map);
        int n = map.getSize();
        int u = map.getUnit();

        Group root = new Group();

        root.getChildren().add(map);
        //create food
        Food food = new Food(map, player);

        player.feed(food);

        //set scene size
        Scene scene = new Scene(root, u * n, u * n);

        primaryStage.setTitle("Eater");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
