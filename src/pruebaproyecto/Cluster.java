/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaproyecto;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import static pruebaproyecto.Matriz.matriz;
import static pruebaproyecto.Matriz.matriz2;
import static pruebaproyecto.Matriz.matriz3;

/**
 *
 * @author Usuario
 */
public class Cluster {
    public static int counternclusteres=1;
    private int ncluster;
    private int ncolor;        
    public static Map<Cluster, ArrayList<Punto>> cluster=new HashMap<>() ;

    public Cluster(int ncluster, int ncolor) {
        this.ncluster = ncluster;
        this.ncolor = ncolor;
    }
    //funcion que recoje un cluster en concreto de la matriz2 y lo añade en el hashmap cluster
    public static void getCluster(int fila, int columna ,int color,int ncluster,Map<Cluster,ArrayList<Punto>> Mcluster){
        Punto p= new Punto(fila,columna,color);
        ArrayDeque <Punto> pila = new ArrayDeque();
        ArrayList <Punto> puntos=new ArrayList();
        boolean fin=false;    
        while(!fin){
            if (matriz2.get(p.getFila()).get(p.getColumna())==color){  
                matriz2.get(p.getFila()).set(p.getColumna(), -1);
                pila.push(new Punto(p.getFila(),p.getColumna(),p.getColor()));  
                puntos.add(new Punto(p.getFila(),p.getColumna(),p.getColor()));
                        int i=0;
            }//izqvacio
            if (Matriz.isIndexInsideOfBoundsAndSameColor(p.getFila(),p.getColumna()-1,color,matriz2)){

                p=new Punto(p.getFila(),p.getColumna()-1,p.getColor());
            }//dervacio
            else if (Matriz.isIndexInsideOfBoundsAndSameColor(p.getFila(),p.getColumna()+1,color,matriz2)){
                p=new Punto(p.getFila(),p.getColumna()+1,p.getColor());

            }
            //ArribaVacio
            else if (Matriz.isIndexInsideOfBoundsAndSameColor(p.getFila()-1,p.getColumna(),color,matriz2)){
                p= new Punto(p.getFila()-1,p.getColumna(),p.getColor());

            }//Abajovacio
            else if (Matriz.isIndexInsideOfBoundsAndSameColor(p.getFila()+1,p.getColumna(),color,matriz2)){
                p= new Punto(p.getFila()+1,p.getColumna(),p.getColor());

            }
            else{
                if(!pila.isEmpty()){
                    p=pila.pop();
                }else{fin=true;}
            }

        
    }
    counternclusteres++;
    Mcluster.put(new Cluster(ncluster,color), puntos);
    }  
    
    //funcion para recorrer matriz2 en busca de todos los clusteres
    public static void añadirClusters(){
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
    }
    
    //funcion para pintar los clusteres en orden. Creo que de aqui se sacan las cosas necesarias para la interfaz junto a la matriz 3, la cual
    //se va pintando a medida que va corriendo esta funcion.
    public static void printClusters(){
        cluster.entrySet().forEach((entry) -> {
        Cluster c= entry.getKey();
        Punto PuntoInicial=entry.getValue().get(0);
        Punto p= new Punto(PuntoInicial.getFila(),PuntoInicial.getColumna(),PuntoInicial.getColor());
        //esta pila es la que se va a estar mostrando en la interfaz
        ArrayDeque <Punto> pila = new ArrayDeque();
        boolean fin=false;
        System.out.println("Cluster numero "+ c.getNcolor()+". Tamaño: "+entry.getValue().size());
        while(!fin){
            System.out.println("Coordenada actual: {" +p.getFila()+","+p.getColumna()+"}");
            if (matriz.get(p.getFila()).get(p.getColumna())==c.getNcolor()){  
                matriz3.get(p.getFila()).set(p.getColumna(), c.getNcolor());
                matriz.get(p.getFila()).set(p.getColumna(), -1);
                pila.push(new Punto(p.getFila(),p.getColumna(),p.getColor()));  
                Matriz.printMatriz(matriz3);
                System.out.println("pintada de color "+p.getColor()+", añadiendo coordenada a la pila");
                
                ArrayDequeToString(pila);
            }//izqvacio
            if (Matriz.isIndexInsideOfBoundsAndSameColor(p.getFila(),p.getColumna()-1,c.getNcolor(),matriz)){
                System.out.println("Movimiento a la izquierda");
                p=new Punto(p.getFila(),p.getColumna()-1,p.getColor());
            }//dervacio
            else if (Matriz.isIndexInsideOfBoundsAndSameColor(p.getFila(),p.getColumna()+1,c.getNcolor(),matriz)){
                p=new Punto(p.getFila(),p.getColumna()+1,p.getColor());
                System.out.println("Movimiento a la derecha");
            }
            //ArribaVacio
            else if (Matriz.isIndexInsideOfBoundsAndSameColor(p.getFila()-1,p.getColumna(),c.getNcolor(),matriz)){
                p= new Punto(p.getFila()-1,p.getColumna(),p.getColor());
                System.out.println("Movimiento hacia arriba");
            }//Abajovacio
            else if (Matriz.isIndexInsideOfBoundsAndSameColor(p.getFila()+1,p.getColumna(),c.getNcolor(),matriz)){
                p= new Punto(p.getFila()+1,p.getColumna(),p.getColor());
                System.out.println("Movimiento hacia abajo");
            }
            else{              
                if(!pila.isEmpty()){
                    System.out.println("Buscando coordenada para pintar...No se encontró coordenada, extrayendo coordenada de la pila. ");
                    p=pila.pop();
                    ArrayDequeToString(pila);
                    System.out.println("Volviendo a última cordenada...");
                }
                else{
                    System.out.println("No quedan mas coordenadas.Coloreando siguiente cluster");
                    fin=true;
                }
            }

        }
        
        System.out.println("siguiente cluster:");   

        });System.out.println("No hay mas clusteres. Finalizando programa.");
        
    }  
        
    //Funcion que ordena los clusteres en el orden especificado. Primero se ordenan lLos ArrayList    
    //de los clusteres donde se encuentran las coordenadas de la matriz para que el primer elemento 
    //del ArrayList sea el pixel mas a la izquierda de todo el cluster y despues poder ordenar los 
    //clusteres en base a su tamaño y pixel mas a la izquierda facilmente
    public static void  sortClusters(){
        //sort coordenadas por columnas de izquierda a derecha
        for(Map.Entry<Cluster, ArrayList<Punto>> entry:cluster.entrySet()) {         
            Collections.sort(entry.getValue(), (Punto c1, Punto c2) -> {
                return Integer.compare( c1.getColumna(),c2.getColumna());
            });
        }
        //sort clusteres en base a tamaño y coordenada mas a la izquierda
        Comparator<Map.Entry<Cluster,ArrayList<Punto>>> sortbypixel = (p, o) ->Integer.compare(p.getValue().get(0).getColumna(),o.getValue().get(0).getColumna()) ;
        Comparator<Map.Entry<Cluster,ArrayList<Punto>>> sortbytamaño = (p, o) ->Integer.compare(o.getValue().size(),p.getValue().size()) ;
        Map<Cluster,ArrayList<Punto>> sortedMap = cluster.entrySet()
                               .stream()
                               .sorted(sortbytamaño.thenComparing(sortbypixel))     
                               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                        (e1, e2) -> e1, LinkedHashMap::new));
        cluster=sortedMap; 

    }
    
    public int getNcluster() {
        return ncluster;
    }

    public void setNcluster(int ncluster) {
        this.ncluster = ncluster;
    }

    public int getNcolor() {
        return ncolor;
    }

    public void setNcolor(int ncolor) {
        this.ncolor = ncolor;
    }
     
    public static String ArrayDequeToString(ArrayDeque<Punto> list) {
        String s="[";
            for(Punto f:list){
                s+=f.toString()+" ";  
            }
            s+="]";
            System.out.println(s);
        return s;
    }
}
