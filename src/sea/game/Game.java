package sea.game;

import javafx.scene.control.Cell;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    Field field;

    public Game(Field field) {
        this.field = field;
    }

    String letters = "абвгдежзик";

    void moveRobot(){
        char[] chArray = letters.toCharArray();
        //char z = chArray[((int) Math.random()*10)];
       // int v = (int) Math.random()*11;
        System.out.println("Хожу: " + chArray[((int) (Math.random()*10))] + (int) (Math.random()*11));
        Scanner in = new Scanner(System.in);

        String str = in.nextLine();

        int x = Integer.valueOf(str);

        if (x == 1){
            System.out.println("Не попал, говоришь...Ну ходи");
            moveUser();
        }
        else if (x == 2){
            System.out.println("Ура! Попал!");
            moveRobot();
        }
        else if (x == 3){
            System.out.println("Убил!");
            moveRobot();
        }
        else {
            System.out.println("Повторика еще раз! (1-не попал; 2-попал; 3-убил)");
            moveRobot();
        }
    }




    void processingMoveUser(int x, int y){
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
            moveRobot();
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

    public void moveUser(){


        Scanner in = new Scanner(System.in);
        //System.out.println("Ходи!");

        String str = in.nextLine();
        str = str.replaceAll(" ", "");
        str = str.toLowerCase();
        char[] chArray = str.toCharArray();
        //int x = Character.getNumericValue(chArray[0]);
        if (letters.indexOf(chArray[0]) < 0 ||
                Character.getNumericValue(chArray[1])>10 || Character.getNumericValue(chArray[1])<1 ||
                chArray.length > 2){
            System.out.println("Разве это ход? Попробуй еще раз...что-то типа: а1, к2 или д10!");
        }
        else {
            int x = letters.indexOf(chArray[0]);
            int y = Character.getNumericValue(chArray[1]);

            processingMoveUser(x, (y - 1));


           /* for (int i = 0; i < field.field.length; i++) {
                System.out.println(Arrays.toString(field.field[i]));
            }*/
        }
    }



    public  void startGame(){
        field.fieldGame();
        /*for (int i = 0; i < field.field.length; i++) {
            System.out.println(Arrays.toString(field.field[i]));
        }*/

        if (field.zerroField(field.field, 1).length == 20){
            System.out.println("Поле готово. Для общения с роботом: 1-не попал; 2-попал; 3-убил");
        }
        else System.out.println("Не удалось сгенерить поле. Попробуйте еще раз");



        while (field.zerroField(field.field, 1).length > 0) {
            if (Math.random() > 0.5) {
                System.out.println("Ходи!");
                moveUser();
            }
            else moveRobot();

        }
    }
}

