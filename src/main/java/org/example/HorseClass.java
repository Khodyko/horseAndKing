package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        //Заполняем hashMap
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                currCoordinate = new Coordinate(i, j);
                currCellValue = new CellValue();
                currCellValue.setCellSign(DOT);
                currCellValue.setKingStepped(-1);
                currCellValue.setHorseStepped(-1);
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

        return getStepCountFromThisCell(board, 1, startCoord, endCoord, n);
    }

    /**
     * Ищем по шагам от данной координаты,
     * смотрим все возможные варианты ходов текущей фигурой
     * Если в какую-то координату этой фигурой мы уже ходили, то её не рассматриваем как вариант.
     *
     * @param board информация о координатах и какими фигурами на каком шаге мы туда ступали
     * @param stepNum номер следующего шага поиска
     * @param currCoordinates содержит координаты, от которых мы делаем шаг на данном этапе
     * @param endCoord координата окончания (чтобы не искать каждый раз)
     * @param boardSize размер доски
     */
    public int getStepCountFromThisCell(Map<Coordinate, CellValue> board, int stepNum, Queue<Coordinate> currCoordinates, Coordinate endCoord, int boardSize) {
        CellValue currCellValue;
        Coordinate coor;
        int currCoordinatesSize=currCoordinates.size();
        for (int i = 0; i < currCoordinatesSize; i++) {
            coor = currCoordinates.poll();
            currCellValue = board.get(coor);
            if (currCellValue.getCellSign()==FINISH) {
                return stepNum-1;
            }
            if (currCellValue.getHorseStepped() == stepNum-1) {
                currCoordinates.addAll(fillBoardByThisStepsForHorse(board, coor, boardSize, stepNum));
            }
            if  (currCellValue.getKingStepped() == stepNum-1) {
                currCoordinates.addAll(fillBoardByThisStepsForKing(board, coor, boardSize, stepNum));
            }
        }
        if (currCoordinates.size() == 0) {
            return -1;
        }
        return getStepCountFromThisCell(board, stepNum + 1, currCoordinates, endCoord, boardSize);
    }

    /**
     * Заполняет значения доски информацией,
     * о том какой фигурой и в какие координаты на данном шаге мы можем ступить,
     * если шагаем королем
     *
     * @param board доска
     * @param currCoordinate текущая координата
     * @param boardSize размер доски
     * @param currStepNum номер текущего шага
     *
     * return координаты, которые были заполнены на этом шагу
     */
    private List<Coordinate> fillBoardByThisStepsForKing(Map<Coordinate, CellValue> board, Coordinate currCoordinate,
                                                         final int boardSize, int currStepNum) {
        List<Coordinate> possibleSteps= getPossibleCoordinatesForKing(currCoordinate);
        List<Coordinate> result=new ArrayList<>();
        for(Coordinate coord: possibleSteps){
            if(isCoordinateOnBoard(coord, boardSize) && !isStepWasBefore(board, coord, true)){
                fillKingCoordinateValue(board, currStepNum, coord);
                result.add(coord);
            }
        }

        return result;
    }

    /**
     * Заполняет значения доски информацией,
     * о том какой фигурой и в какие координаты на данном шаге мы можем ступить,
     * если шагаем конем
     *
     * @param board доска
     * @param currCoordinate текущая координата
     * @param boardSize размер доски
     * @param currStepNum номер текущего шага
     *
     * return координаты, которые были заполнены на этом шагу
     */
    private List<Coordinate> fillBoardByThisStepsForHorse(Map<Coordinate, CellValue> board, Coordinate currCoordinate,
                                                          int boardSize, int currStepNum) {
        List<Coordinate> possibleSteps= getPossibleCoordinatesForHorse(currCoordinate);
        List<Coordinate> result=new ArrayList<>();
        for(Coordinate coord: possibleSteps){
            if(isCoordinateOnBoard(coord, boardSize) && !isStepWasBefore(board, coord, false)){
                fillHorseCoordinateValue(board, currStepNum, coord);
                result.add(coord);
            }
        }

        return result;
    }

    /**
     * Проверяет возможен ли шаг на данную координату данной фигурой
     * с учетом знаков на доске
     *
     * @param board
     * @param coordinate
     * @param isKingNow
     * @return
     */
    private boolean isStepWasBefore(Map<Coordinate, CellValue> board, Coordinate coordinate, boolean isKingNow){
        CellValue val=board.get(coordinate);
        if(isKingNow){
            if(val.getCellSign()==TO_HORSE){
                return val.getHorseStepped()>=0;
            }
            return val.getKingStepped()>0;
        } else {
            if(val.getCellSign()==TO_KING){
                return val.getKingStepped()>0;
            }
            return val.getHorseStepped()>=0;
        }
    }

    /**
     * Заполняет значение board для данной координаты
     * при фигуре король
     *
     * @param board
     * @param currentStep
     * @param coordinate
     */
    private void fillKingCoordinateValue(Map<Coordinate, CellValue> board, int currentStep, Coordinate coordinate){
        CellValue val=board.get(coordinate);
        if(val.getCellSign()==TO_HORSE){
            fillHorseCoordinateValue(board, currentStep, coordinate);
            return;
        }
        if(val.getKingStepped()>0){
            throw new RuntimeException("This cell was already stepped");
        }
        val.setKingStepped(currentStep);
    }

    /**
     * Заполняет значение board для данной координаты
     * при фигуре конь
     *
     * @param board
     * @param currentStep
     * @param coordinate
     */
    private void fillHorseCoordinateValue(Map<Coordinate, CellValue> board, int currentStep, Coordinate coordinate){
        CellValue val=board.get(coordinate);
        if(val.getCellSign()==TO_KING){
           fillKingCoordinateValue(board, currentStep, coordinate);
           return;
        }
        if(val.getHorseStepped()>=0){
            throw new RuntimeException("This cell was already stepped");
        }
        val.setHorseStepped(currentStep);
    }

    /**
     * Возвращает все возможные шаги для фигуры король
     *
     * @param coordinate
     * @return
     */
    private List<Coordinate> getPossibleCoordinatesForKing(Coordinate coordinate) {
        List<Coordinate> result;
        Coordinate up = new Coordinate(coordinate.getRow() - 1, coordinate.getColumn());
        Coordinate upLeft = new Coordinate(coordinate.getRow() - 1, coordinate.getColumn() - 1);
        Coordinate upRight = new Coordinate(coordinate.getRow() - 1, coordinate.getColumn() + 1);
        Coordinate right = new Coordinate(coordinate.getRow(), coordinate.getColumn() + 1);
        Coordinate left = new Coordinate(coordinate.getRow(), coordinate.getColumn() - 1);
        Coordinate down = new Coordinate(coordinate.getRow() + 1, coordinate.getColumn());
        Coordinate downLeft = new Coordinate(coordinate.getRow() + 1, coordinate.getColumn() - 1);
        Coordinate downRight = new Coordinate(coordinate.getRow() + 1, coordinate.getColumn() + 1);

        result = Stream.of(up, upLeft, upRight, right, left, down, downLeft, downRight).collect(Collectors.toList());
        return result;
    }

    /**
     * Возвращает все возможные шаги для фигуры конь
     * @param coordinate
     * @return
     */
    private List<Coordinate> getPossibleCoordinatesForHorse(Coordinate coordinate) {
        List<Coordinate> result;
        Coordinate twoUpOneLeft = new Coordinate(coordinate.getRow() - 2, coordinate.getColumn() - 1);
        Coordinate twoUpOneRight = new Coordinate(coordinate.getRow() - 2, coordinate.getColumn() + 1);
        Coordinate twoRightOneUp = new Coordinate(coordinate.getRow() - 1, coordinate.getColumn() + 2);
        Coordinate twoRightOneDown = new Coordinate(coordinate.getRow() + 1, coordinate.getColumn() + 2);
        Coordinate twoLeftOneUp = new Coordinate(coordinate.getRow() - 1, coordinate.getColumn() - 2);
        Coordinate twoLeftOneDown = new Coordinate(coordinate.getRow() + 1, coordinate.getColumn() - 2);
        Coordinate twoDownOneRight = new Coordinate(coordinate.getRow() + 2, coordinate.getColumn() + 1);
        Coordinate twoDownOneLeft = new Coordinate(coordinate.getRow() + 2, coordinate.getColumn() - 1);

        result = Stream.of(twoUpOneLeft, twoUpOneRight, twoRightOneUp, twoRightOneDown, twoLeftOneUp,
                twoLeftOneDown, twoDownOneRight, twoDownOneLeft).collect(Collectors.toList());
        return result;
    }

    /**
     * Проверяет попадает ли координата на доску переданного размера.
     *
     * @param coordinate
     * @param boardSize
     * @return
     */
    private boolean isCoordinateOnBoard(Coordinate coordinate, int boardSize) {
        return  coordinate.getRow() >= 0 &&
                coordinate.getColumn() >= 0 &&
                coordinate.getColumn() < boardSize &&
                coordinate.getRow() < boardSize;
    }
}
