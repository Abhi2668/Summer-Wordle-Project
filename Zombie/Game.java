import java.awt.Canvas;

public class Game extends Canvas implements Runnable {

    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    public String title = "Zombie Game";

    public Game() {
        new Window(WIDTH,HEIGHT,title,this);
    }

    //gameloop
    @Override
    public void run() {

    }

    public static void main(String[] args) {
        new Game();
    }
}
