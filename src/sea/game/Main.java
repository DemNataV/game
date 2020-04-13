package sea.game;

public class Main {
    public static void main(String[] args) {

        Field field = new Field();
        Game game = new Game(field);
        game.startGame();

    }
}
