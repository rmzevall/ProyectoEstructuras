/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaproyecto;

/**
 *
 * @author Usuario
 */
public class Punto {
    private int fila;
    private int columna;
    private int color;

    public Punto(int fila, int columna, int color) {
        this.fila = fila;
        this.columna = columna;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "{"  + fila + "," + columna + "}";
    }
    


}
