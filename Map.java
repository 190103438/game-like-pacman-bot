import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.util.Scanner;

public class Map extends Pane {
    private int UNIT;
    private int size;
    private int[][] map;
    private Position start;
    //constructor, take file name containing map
    Map(String s) throws Exception{
        //path containing map
        Scanner in = new Scanner(Syste.in);
        String pathToMap = in.nextLine();
        File file = new File(pathToMap);
        //read map
        Scanner cin = new Scanner(file);
        size = cin.nextInt();
        map = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                map[i][j] = cin.nextInt();
                if(map[i][j] == 2){
                    start = new Position(i, j);
                }
            }
        }
        //set unit 40 as default
        UNIT = 40;

        //draw border lines
        for(int i = 0; i <= size; i++){
            Line line = new Line();
            line.setStartX(0);
            line.setStartY(i * UNIT);
            line.setEndX(UNIT * size);
            line.setEndY(i * UNIT);
            this.getChildren().add(line);
        }
        for(int i = 0; i <= size; i++){
            Line line = new Line();
            line.setStartX(i * UNIT);
            line.setStartY(0);
            line.setEndX(i * UNIT);
            line.setEndY(UNIT * size);
            this.getChildren().add(line);
        }

        //draw walls
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(map[i][j] == 1){
                    Rectangle rec = new Rectangle(j * UNIT, i * UNIT, UNIT, UNIT);
                    this.getChildren().add(rec);
                }
            }
        }

    }
    int getUnit(){
        return UNIT;
    }
    int getSize(){
        return size;
    }
    int getValue(int x, int y){
        return map[x][y];
    }
    Position getStartPosition(){
        return start;
    }
}
