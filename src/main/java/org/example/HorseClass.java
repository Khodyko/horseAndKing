package org.example;

import java.util.*;

public class HorseClass {

    char DOT = '.';
    char TO_KING = 'G';
    char TO_HORSE = 'K';
    char START = 'S';
    char FINISH = 'F';

    public int getStepsCount(int n, String[] boardString) {
        Coordinate endCoord = null;

        Queue<Coordinate> startCoord = new LinkedList<>();

        HashMap<Coordinate, CellValue> board = new HashMap<>();
        CellValue currCellValue;
        Coordinate currCoordinate;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                currCoordinate = new Coordinate(i, j);
                currCellValue = new CellValue();
                currCellValue.setCellSign(DOT);
                currCellValue.setCellSign(boardString[i].charAt(j));
                if (START == boardString[i].charAt(j)) {
                    currCellValue.setHorseStepped(0);
                    startCoord.offer(currCoordinate);
                } else if (FINISH == boardString[i].charAt(j)) {
                    endCoord = currCoordinate;
                }
                board.put(currCoordinate, currCellValue);
            }
        }

        return getStepCountFromThisCell(board, 0, startCoord, endCoord, n);
    }

    public int getStepCountFromThisCell(Map<Coordinate, CellValue> board, int stepNum, Queue<Coordinate> currCoordinates, Coordinate endCoord, int boardSize) {
        CellValue currCellValue;
        Coordinate coor;
        for (int i = 0; i < currCoordinates.size(); i++) {
            coor = currCoordinates.poll();
            currCellValue = board.get(coor);
            if (currCellValue.equals(endCoord)) {
                return stepNum;
            }
            if (currCellValue.horseStepped == stepNum) {
                currCoordinates.addAll(fillBoardByNextStepsForHorse(board, coor, boardSize));
            } else {
                currCoordinates.addAll(fillBoardByNextStepsForKing(board, coor, boardSize));
            }
        }
        if (currCoordinates.size() == 0) {
            return -1;
        }
        return getStepCountFromThisCell(board, stepNum + 1, currCoordinates, endCoord, boardSize);
    }


    public List<Coordinate> fillBoardByNextStepsForKing(Map<Coordinate, CellValue> board, Coordinate currCoordinate, int boardSize) {

        return null;
    }

    public List<Coordinate> fillBoardByNextStepsForHorse(Map<Coordinate, CellValue> board, Coordinate currCoordinate, int boardSize) {

        return null;
    }
}
