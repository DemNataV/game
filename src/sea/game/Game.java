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
        if (field.field[x][y] == 3) {
            System.out.println("Ты сюда уже промахивался)))");
        }
        else if (field.field[x][y] == 4){
                System.out.println("Хочешь потопить дважды? Давай другую клетку");
        }
        else if (field.field[x][y] == 0 || field.field[x][y] == 2){
            System.out.println("Не попал!");
            if (field.field[x][y] == 2 && Math.random() > 0.7){
                System.out.println("...но заставил меня понервничать");
            }
            field.field[x][y] = 3;
        }

        else if (field.zerroCells(field.field, cell, 1).length > 0) {
            System.out.println("Попал!");
            field.field[x][y] = 4;
        }
        else if (field.zerroCells(field.field, cell, 4).length > 0) {
            for (int i = 0; i < field.zerroCells(field.field, cell, 4).length; i++) {
                if (field.zerroCells(field.field, field.zerroCells(field.field, cell, 4)[i], 1).length > 0) {
                    System.out.println("Попал!");
                    field.field[x][y] = 4;
                }
                else if (field.zerroCells(field.field, field.zerroCells(field.field, cell, 4)[i], 4).length > 0) {
                    for (int j = 0; j < field.zerroCells(field.field, field.zerroCells(field.field, cell, 4)[i], 4).length; j++) {
                        if (field.zerroCells(field.field, field.zerroCells(field.field, field.zerroCells(field.field, cell, 4)[i], 4)[j], 1).length > 0) {
                            System.out.println("Попал!");
                            field.field[x][y] = 4;
                        }
                    }
                }
            }
        }
        else {
            System.out.println("Убил!");
            field.field[x][y] = 4;
        }



    }

    /*char[] letters = new char[];
    letters =  {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К"};*/

    public  void startGame(){
        field.fieldGame();
        /*for (int i = 0; i < field.field.length; i++) {
            System.out.println(Arrays.toString(field.field[i]));
        }*/

        if (field.zerroField(field.field, 1).length == 20){
            System.out.println("Поле готово. Ходи!");
        }
        else System.out.println("Не удалось сгенерить поле. Попробуйте еще раз");

        String letters = "абвгдежзик";

        while (field.zerroField(field.field, 1).length > 0) {

            Scanner in = new Scanner(System.in);
            //System.out.println("Ходи!");

            String str = in.nextLine();
            char[] chArray = str.toCharArray();
            //int x = Character.getNumericValue(chArray[0]);
            if (letters.indexOf(chArray[0]) < 0 ||
                    Character.getNumericValue(chArray[1])>10 || Character.getNumericValue(chArray[1])<1){
                System.out.println("Разве это ход? Попробуй еще раз...что-то типа: а1, к2 или д10!");
            }
            else {
                int x = letters.indexOf(chArray[0]);
                int y = Character.getNumericValue(chArray[1]);

                moveUser(x, (y - 1));


           /* for (int i = 0; i < field.field.length; i++) {
                System.out.println(Arrays.toString(field.field[i]));
            }*/
            }
        }
    }
}

