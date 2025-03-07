package org.example;

import java.util.*;

public class HorseClass {
    public int getStepsCount(int n, String[] boardString) {
        char[][] board = new char[n][n];
        int[][] kingStepped = new int[n][n];
        int[][] horseStepped = new int[n][n];
        Coordinate currCoordinate =new Coordinate();

        for (int i = 0; i < n; i++) {
            board[i] = boardString[i].toCharArray();
            Arrays.fill(horseStepped[i], -1);
            Arrays.fill(kingStepped[i], -1);
            if(boardString[i].contains("S")){
                int column=boardString[i].indexOf('S');
                horseStepped[i][column]=0;
                currCoordinate.setRow(i);
                currCoordinate.setColumn(column);
            }
        }

        int stepNum=0;
        boolean isKing=false;



        return 3;
    }

    public int getStepCountFromThisCell(boolean isKingNow, Coordinate coordinate, int[][] kingStepped, int[][]horseStepped) {
        Queue<Coordinate> kingVariants=new LinkedList<>();
        Queue<Coordinate> horseVariants=new LinkedList<>();

        return 0;
    }


    public List<Coordinate> getNextStepsVariantsForKing(Coordinate coordinate, int[][] kingStepped){

        return null;
    }

    public List<Coordinate> getNextStepsVariantsForHorse(Coordinate coordinate, int[][] horseStepped){

        return null;
    }
}
