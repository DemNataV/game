package sea.game;

import javafx.scene.control.Cell;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    Field robotField;
    Field userField;

    public Game(Field field) {
        this.robotField = field;
    }

    String letters = "АБВГДЕЖИК";

    void processingUserAnswer(){
        Scanner in = new Scanner(System.in);

        String str = in.nextLine();

        if (str.equals("1") || str.equals("2") || str.equals("3")) {

            int x = Integer.valueOf(str);

            if (x == 1) {
                System.out.println("Не попал, говоришь...Ну ходи");
                moveUser();
            } else if (x == 2) {
                System.out.println("Ура! Попал!");
                moveRobot();
            } else if (x == 3) {
                System.out.println("Убил!");
                moveRobot();
            }
        }
        else {
            System.out.println("Повторика еще раз! (1-не попал; 2-попал; 3-убил)");
            processingUserAnswer();
        }
    }



    void moveRobot(){
        char[] chArray = letters.toCharArray();



        System.out.println("Хожу: " + chArray[((int) (Math.random()*8+1))] + (int) (Math.random()*11));

        processingUserAnswer();
    }




    void processingMoveUser(int x, int y){
        int[] cell = {x, y};
         if (robotField.field[x][y] == 0 || robotField.field[x][y] == 2){
            System.out.println("Не попал!");
            if (robotField.field[x][y] == 2 && Math.random() > 0.7){
                System.out.println("...но заставил меня понервничать");

            }
            robotField.field[x][y] = 3;
            moveRobot();
        }

        else if (robotField.field[x][y] == 3) {
            System.out.println("Ты сюда уже промахивался)))");
        }
        else if (robotField.field[x][y] == 4){
                System.out.println("Хочешь потопить дважды? Давай другую клетку");
        }


        else if (robotField.zerroCells(robotField.field, cell, 1).length > 0) {
            System.out.println("Попал!");
            robotField.field[x][y] = 4;
        }
        else if (robotField.zerroCells(robotField.field, cell, 4).length > 0) {
            for (int i = 0; i < robotField.zerroCells(robotField.field, cell, 4).length; i++) {
                if (robotField.zerroCells(robotField.field, robotField.zerroCells(robotField.field, cell, 4)[i], 1).length > 0) {
                    System.out.println("Попал!");
                    robotField.field[x][y] = 4;
                }
                else if (robotField.zerroCells(robotField.field, robotField.zerroCells(robotField.field, cell, 4)[i], 4).length > 0) {
                    for (int j = 0; j < robotField.zerroCells(robotField.field, robotField.zerroCells(robotField.field, cell, 4)[i], 4).length; j++) {
                        if (robotField.zerroCells(robotField.field, robotField.zerroCells(robotField.field, robotField.zerroCells(robotField.field, cell, 4)[i], 4)[j], 1).length > 0) {
                            System.out.println("Попал!");
                            robotField.field[x][y] = 4;
                        }
                    }
                }
            }
        }
        else {
            System.out.println("Убил!");
            robotField.field[x][y] = 4;
        }



    }

    public void moveUser(){


        Scanner in = new Scanner(System.in);
        //System.out.println("Ходи!");

        String str = in.nextLine();
        str = str.replaceAll(" ", "");
        str = str.toUpperCase();
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
        robotField.fieldGame();
        /*for (int i = 0; i < field.field.length; i++) {
            System.out.println(Arrays.toString(field.field[i]));
        }*/

        if (robotField.zerroField(robotField.field, 1).length == 20){
            System.out.println("Поле готово - ходи! Для общения с роботом: 1-не попал; 2-попал; 3-убил");
        }
        else {
            System.out.println("Что-то я запутался, расставляя корабли-_- давай попробуем еще раз");
            return;
        }



        while (robotField.zerroField(robotField.field, 1).length > 0) {
            /*if (Math.random() > 0.5) {
                System.out.println("Ходи!");
                moveRobot();
            }
            else*/ moveUser();

        }
    }
}

