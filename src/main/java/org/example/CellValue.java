package org.example;

public class CellValue {

    /**
     * Знак ячейки
     */
    private char cellSign;

    /**
     * Значение на каком step фигурой конь мы попадали на эту ячейку
     */
    private int horseStepped;

    /**
     * Значение на каком step фигурой король мы попадали на эту ячейку
     */
    private int kingStepped;

    public char getCellSign() {
        return cellSign;
    }

    public void setCellSign(char cellSign) {
        this.cellSign = cellSign;
    }

    public int getHorseStepped() {
        return horseStepped;
    }

    public void setHorseStepped(int horseStepped) {
        this.horseStepped = horseStepped;
    }

    public int getKingStepped() {
        return kingStepped;
    }

    public void setKingStepped(int kingStepped) {
        this.kingStepped = kingStepped;
    }
}
