package org.example;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {

    private static final Scanner sc = new Scanner(System.in);
    private static final Random ramdom = new Random();
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int scoreHuman;
    private static int scorePc;
    private static int roundCounter;
    private static int winLength;
    private static char dot_empty = '.';
    private static char dot_x = 'X';
    private static char dot_y = 'Y';
    private static char dotHuman;
    private static char dotPc;
    public static final int DOTS_TO_WIN = 4;



    public static void main(String[] args) {
        play();
    }private static void play () {
    while(true) {
        chooseTheDot();
        playRound();
        System.out.printf("SCORE: HUMAN   PC\n" + "     %d    %d\n", scoreHuman, scorePc);

        if (!sc.next().toLowerCase().equals("y")) {
            System.out.println("Good Bye");
            break;
        }
    }

    }


    private static void playRound(){
        System.out.printf("ROUND %d START\n", ++roundCounter);
        initField(3,3);
        printField();
        if(dotHuman== dot_x){
            humanFirstTurn();
        } else {
            pcFirstTurn();
        }
    }

    private static void pcFirstTurn(){
        while(true) {
            pcTurn();
            printField();
            if(checkWin(dotPc)) break;
            humanTurn();
            printField();
            if(checkDraw(dotHuman)) break;
        }
    }

    private static void humanFirstTurn(){
        while(true) {
            humanTurn();
            printField();
            if(checkWin(dotHuman)) break;
            pcTurn();
            printField();
            if(checkDraw(dotPc)) break;
        }
    }

    private static void chooseTheDot(){
        System.out.println("If you want to play with 'X' then enter 'Y' otherwise enter anything >>> ");
        if (sc.next().toLowerCase().equals("x")){
            dotHuman = dot_x;
            dotPc = dot_y;
        } else {
            dotHuman = dot_y;
            dotPc = dot_x;
        }
    }

    private static boolean checkGame(char dot){
        if(checkWin(dot)){
            if (dot == dotHuman){
                System.out.println("Human Won!");
                scoreHuman++;
            } else if(dot == dotPc){
                System.out.println("Pc Won!");
                   scorePc++;
            }
        }
        return true;
    }

    private static void humanTurn(){
        int x;
        int y;
        do {

            System.out.print("Please enter coordinates of your turn x and y spilt by whitespace");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while(!isCellValid(x,y) || !isCellEmpty(x,y));
            field[y][x] = dotHuman;
    }

    private static void pcTurn(){
        int x;
        int y;
        do {
            x = ramdom.nextInt(fieldSizeX);
            y = ramdom.nextInt(fieldSizeY);
        } while(!isCellEmpty(x,y));
        field[y][x] = dotPc;
    }

    private static boolean checkWin(char dot){
        //horizontal
        for (int i = 0; i < 3; i++)
            if ((field[i][0] == dot && field[i][1] == dot &&
                    field[i][2] == dot) ||
                    (field[0][i] == dot && field[1][i] == dot &&
                            field[2][i] == dot))
                return true;
        //vertical
            if ((field[0][0] == dot && field[1][1] == dot &&
                field[2][2] == dot) ||
                (field[2][0] == dot && field[1][1] == dot &&
                        field[0][2] == dot))
            return true;
        return false;
    }

    private static boolean checkDraw(char dotPc) {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        System.out.println("Draw is done!");
        return true;
    }

    private static boolean isCellEmpty(int x, int y){
        return field[y][x] == dot_empty;
    }

    private static boolean isCellValid(int x, int y){
        return x >= 0 && y >= 0 && x < fieldSizeX && y < fieldSizeY;


    }

    private static void initField(int sizeX, int sizeY){
       fieldSizeX = sizeX;
       fieldSizeY = sizeY;
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y <fieldSizeY ; y++) {
            for (int x = 0; x <fieldSizeX; x++) {
                field[y][x] = dot_empty;
            }
        }
    }

    private static void printField(){
        for (int y = 0; y < fieldSizeY ; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + " ");
            }
            System.out.println();
        }
        System.out.print("+");
        for (int i = 0; i <fieldSizeX * 2 + 1; i++) {
            System.out.print(i % 2 == 0 ? "-" : i / 2 + 1);
        }
        System.out.println();
        for (int i = 0; i <fieldSizeY; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j <fieldSizeY ; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
    }
}

