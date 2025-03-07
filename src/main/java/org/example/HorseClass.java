package org.example;

import java.util.ArrayList;
import java.util.List;

public class HorseClass {
    public int getStepsCount(int n, String[] boardString) {
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            board[i] = boardString[i].toCharArray();
        }
        List<StepInfo> stepInfoList = new ArrayList<>();


        return 3;
    }

    public int getStepCountFromThisCell(boolean isKingNow, int column, int row) {
        return 0;
    }


}
