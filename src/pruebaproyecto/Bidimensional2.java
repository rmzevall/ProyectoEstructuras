/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaproyecto;
import java.io.IOException;
import java.util.*;
import static pruebaproyecto.Cluster.cluster;
import static pruebaproyecto.Cluster.sortClusters;
import static pruebaproyecto.Matriz.matriz;
import static pruebaproyecto.Matriz.matriz2;
import static pruebaproyecto.Matriz.obtenerMatriz;





public class Bidimensional2 {

    public static void main(String[] args) throws IOException {
    obtenerMatriz("Cuadro.txt");  
        //Mostrar por pantalla los valores que contiene la matriz
        System.out.println("valores introducidos:");
        Matriz.printMatriz(matriz);
        int i=0;
        for (ArrayList<Integer> f:matriz2) {
            int j=0;
            for (int c:f){
                if(matriz2.get(i).get(j)!=-1)
                    Cluster.getCluster(i, j ,matriz2.get(i).get(j),Cluster.counternclusteres,Cluster.cluster);
                j++;
            }

            i++;
        }
            

    sortClusters();
    Cluster.printClusters();
    



    }
}
