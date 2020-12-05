/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaproyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import static java.util.Map.Entry.comparingByValue;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;

/**
 *
 * @author Usuario
 */
public class Matriz {
        
        public static int fila;
        public static int columna;
        //matriz inicial del txt.
        public static ArrayList<ArrayList<Integer>> matriz=new ArrayList<>();
        //matriz igual a la inicial para recojer clusteres.
        public static ArrayList<ArrayList<Integer>> matriz2=new ArrayList<>();
        //matriz de -1 donde se va a pintar los clusteres en orden
        public static ArrayList<ArrayList<Integer>> matriz3=new ArrayList<>();
    
    public static void obtenerMatriz(String archivo)throws FileNotFoundException, IOException{
    File file = new File(archivo); 
    BufferedReader br = new BufferedReader(new FileReader(file)); 
    fila=Integer.parseInt(br.readLine());
    columna=Integer.parseInt(br.readLine());
    String st;
    int f=0;
    while ((st = br.readLine()) != null){
        String[] numeros=st.split(",");
        matriz.add(new ArrayList<>());
        matriz2.add(new ArrayList<>());
        matriz3.add(new ArrayList<>());
        for(String s:numeros){
            matriz.get(f).add(Integer.parseInt(s));
            matriz2.get(f).add(Integer.parseInt(s));
            matriz3.get(f).add(-1);
        }      
        f++;
    }       
    }

    

     public static void printMatriz(ArrayList<ArrayList<Integer>> matriz){
        int i=0;
        for (ArrayList<Integer> f:matriz3) {
            int j=0;
            for (int ce:f){
                System.out.print(matriz3.get(i).get(j)+ " ");
                j++;
            }
         System.out.println();
         i++;
         }
     }
    
    public static boolean isIndexInsideOfBoundsAndSameColor( int fila, int columna,int color,ArrayList<ArrayList<Integer>> matriz) {     
    return !(fila<0||matriz.size()-1<fila ||columna<0 || matriz.get(0).size()-1<columna) 
            && matriz.get(fila).get(columna)==color;
                 
}
 

    
    /*public static void  sortPixeles(ArrayList<SitioCiudad> ciudad){
        

        Collections.sort(ciudad, (SitioCiudad c1, SitioCiudad c2) -> {
            if (Double.compare( c2.getPuntaje(),c1.getPuntaje())==0){
                if(c2.getLonglat()[0]<c1.getLonglat()[0])
                    return -1;
                else if (c1.getLonglat()[0]==c2.getLonglat()[0] && c2.getLonglat()[1]>c1.getLonglat()[1])
                    return -1;
                else if (c1.getLonglat()[0]==c2.getLonglat()[0] && c2.getLonglat()[1]<c1.getLonglat()[1])
                    return 1;
            }
            return Double.compare( c2.getPuntaje(),c1.getPuntaje());
        });


        }
    
    public void pintarCuadro(int x, int y ,int color){
    Punto p= new Punto(x,y);
    ArrayDeque <Punto> pila = new ArrayDeque();
    boolean fin=false;
    while(!fin){
        if (vacio(p)){
            matriz[p.x][p.y]=color;
            pila.push(new Punto(p.x,p.y));                   
        }
        if (izqVacio(p)){
            p= new Punto(p.x,p.y-1);
        }else if (derVacio(p)){
            p= new Punto(p.x,p.y+1);
        }else if (derVacio(p)){
            p=new Punto(p.x-1,p.y);
        }else if (abjVacio(p)){
            p=new Punto(p.x+1,p.y);
        }else{
            if(!pila.isEmpty()){
                p=pila.pop();
            }else{fin=true;}
        }              
    }
}*/

    
    
}
