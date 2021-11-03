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
    }private static void play (){
        chooseTheDot();
        playRound();
        System.out.printf("SCORE : HUMAN   PC\n" + "%d         %d\n", scoreHuman,scorePc);
        System.out.print("Wanna play again? Y or N >>> ");
        if(!sc.next().toLowerCase().equals("y")){
            System.out.println("Good Bye");
        }
    }

    private static void playRound(){
        System.out.printf("ROUND %d START\n", ++roundCounter);
        initField(5,5);
        printField();
        humanFirstTurn();
    }

    private static void pcFirstTurn(){

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

    private static boolean checkWin(char playerDot){
        int hor, ver;
        for (int i = 0; i < field.length; i++) {
            hor = 0;
            ver = 0;
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == playerDot) {
                    hor++;
                } else if (field[i][j] != playerDot && hor < DOTS_TO_WIN) {
                    hor = 0;
                }
                if (field[j][i] == playerDot) {
                    ver++;
                }   else if (field[j][i] != playerDot && ver < DOTS_TO_WIN) {
                    ver = 0;
                }
            }
            if (hor >= DOTS_TO_WIN || ver >= DOTS_TO_WIN) {
                System.out.println("По горизонтали или вертикали " + hor + " " + ver);
                return true;
            }
        }


    return true;

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

