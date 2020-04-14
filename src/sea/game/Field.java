package sea.game;

import java.util.Arrays;
import java.util.Random;

public class Field {

    int[] cell = new int[2];
    int[][] field = new int[10][10];

//Поиск всех пустых клеток поля
   public int[][] zerroField(int[][] field, int status){
       int n = 0;
       for (int i = 0; i < field.length ; i++) {
           for (int j = 0; j < field[i].length ; j++) {
               if (field[i][j] == status) n++;
           }

       }


    int[][] zerroFieldCell = new  int[n][2];
       int m = 0;
       for (int i = 0; i < field.length ; i++) {
           for (int j = 0; j < field[i].length ; j++) {
               if (field[i][j] == status) {
                   zerroFieldCell[m][0] = i;
                   zerroFieldCell[m][1] = j;
                   m++;
               }
           }

       }


       return zerroFieldCell;
   }

    //Поиск всех пустых соседних клеток
    public int[][] zerroCells(int[][] field, int[] cell, int status){
        int n = 0;
        if (cell[0] > 0){
            if (field[cell[0]-1][cell[1]] == status) n++;
        }
        if (cell[0] < 9){
            if (field[cell[0]+1][cell[1]] == status) n++;
        }
        if (cell[1] > 0){
            if (field[cell[0]][cell[1]-1] == status) n++;
        }
        if (cell[1] < 9){
            if (field[cell[0]][cell[1]+1] == status) n++;
        }



        int[][] zerroAdjacentCell = new  int[n][2];
        int m = 0;

        if (cell[0] > 0){
            if (field[cell[0]-1][cell[1]] == status) {
                zerroAdjacentCell[m][0] = cell[0]-1;
                zerroAdjacentCell[m][1] = cell[1];
                m++;
            }
        }
        if (cell[0] < 9){
            if (field[cell[0]+1][cell[1]] == status) {
                zerroAdjacentCell[m][0] = cell[0]+1;
                zerroAdjacentCell[m][1] = cell[1];
                m++;
            }
        }
        if (cell[1] > 0){
            if (field[cell[0]][cell[1]-1] == status) {
                zerroAdjacentCell[m][0] = cell[0];
                zerroAdjacentCell[m][1] = cell[1]-1;
                m++;
            }
        }
        if (cell[1] < 9){
            if (field[cell[0]][cell[1]+1] == status) {
                zerroAdjacentCell[m][0] = cell[0];
                zerroAdjacentCell[m][1] = cell[1]+1;
                m++;
            }
        }



        return zerroAdjacentCell;
    }

    //Ставим корабль
    void ship(int deck){
        int[][] ship = new  int[deck][2];

        int[] randomZerroCell = zerroField(field, 0)[(int) (Math.random()*zerroField(field, 0).length)];
        field[randomZerroCell[0]][randomZerroCell[1]] = 1;
        ship[0][0] = randomZerroCell[0];
        ship[0][1] = randomZerroCell[1];

        if (zerroCells(field, randomZerroCell, 0).length > 0) {
            for (int i = 0; i < deck - 1; i++) {
                int[] nextCell = zerroCells(field, randomZerroCell, 0)[(int) (Math.random() * zerroCells(field, randomZerroCell, 0).length)];
                field[nextCell[0]][nextCell[1]] = 1;
                randomZerroCell = nextCell;
                ship[i+1][0] = randomZerroCell[0];
                ship[i+1][1] = randomZerroCell[1];

            }
        }
        //else System.out.println("Не удалось сгенерить поле. Попробуйте еще раз");

        //Огораживаем корабль недоступными клетками
        for (int i = 0; i < deck; i++) {
          int[][] neighbor = zerroCells(field, ship[i], 0);
            for (int j = 0; j <neighbor.length ; j++) {
                field[neighbor[j][0]][neighbor[j][1]] = 2;
            }

        }

    }

    void fieldGame (){
        for (int i = 0; i < 1; i++) {
            ship(4);
        }
        for (int i = 0; i < 2; i++) {
            ship(3);
        }
        for (int i = 0; i < 3; i++) {
            ship(2);
        }
        for (int i = 0; i < 4; i++) {
            ship(1);
        }


    }



}
