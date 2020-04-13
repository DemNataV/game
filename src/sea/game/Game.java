package sea.game;

import javafx.scene.control.Cell;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    Field field;

    public Game(Field field) {
        this.field = field;
    }




    void moveUser(int x, int y){
        int[] cell = {x, y};
        if (field.field[x][y] != 1 ){
            System.out.println("Не попал!");
            field.field[x][y] = 3;
        }

        else if (field.zerroCells(field.field, cell, 1).length > 0) {
            System.out.println("Попал!");
            field.field[x][y] = 4;
        }
        else {
            System.out.println("Убил!");
            field.field[x][y] = 4;
        }



    }

    public  void startGame(){
        field.fieldGame();
        for (int i = 0; i < field.field.length; i++) {
            System.out.println(Arrays.toString(field.field[i]));
        }
        // System.out.println(Arrays.deepToString(field));
        while (field.zerroField(field.field, 1).length > 0) {

            Scanner in = new Scanner(System.in);
            System.out.println("Ходи!");

            String str = in.nextLine();
            char[] chArray = str.toCharArray();
            int x = Character.getNumericValue(chArray[0]);
            int y = Character.getNumericValue(chArray[1]);

            moveUser(x, y);

            for (int i = 0; i < field.field.length; i++) {
                System.out.println(Arrays.toString(field.field[i]));
            }
        }
    }
}
