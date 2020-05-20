public class Position {
    //i use x and y reversely for comfortableness.
    private int x, y;
    Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
    void setX(int x){
        this.x = x;
    }
    void setY(int y){
        this.y = y;
    }
    boolean equals(Position A){
        //check reversely, because checking position of player and food.
        return (A.y == this.x && A.x == this.y);
    }
}
