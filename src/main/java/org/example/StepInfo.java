package org.example;

import java.util.Objects;

public class StepInfo {
    private int stepNumber;
    private boolean isKing = false;

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StepInfo stepInfo = (StepInfo) o;
        return stepNumber == stepInfo.stepNumber && isKing == stepInfo.isKing;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepNumber, isKing);
    }
}
