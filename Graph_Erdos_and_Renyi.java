/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
//import java.util.stream.IntStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map.Entry;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 *
 * @author flintlock
 */
public class Graph_Erdos_and_Renyi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int number_of_nodes = 500;
        int number_of_edges = 300;
        int number_1 = 0;
        int number_2 = 0;
        boolean dirigido = false;
        boolean auto_cycles = false;
        boolean flag = true;
        
        Random random = new Random();
        
        ArrayList<Integer> nodes_fin = new ArrayList<>();
        ArrayList<Integer> nodes_inicio = new ArrayList<>();
        HashMap<ArrayList<Integer>, ArrayList<Integer>> edges = new HashMap<>();
        
        
        // generar lista de valores del tamaño del número de aristas
        for (int i = 0; i < number_of_edges; i++) {
            flag = true;
            while (flag != false) {
                number_1 = random.nextInt(number_of_nodes);
                number_2 = random.nextInt(number_of_nodes);
                // no se permiten autociclos
                if (auto_cycles != true) {
                    // solo agregar si ambos números son diferentes
                    if (number_2 != number_1) {
                        if (nodes_fin.contains(number_2) != true & nodes_inicio.contains(number_1) != true) {
                            flag = false;
                            nodes_fin.add(number_2);
                            nodes_inicio.add(number_1);
                        }
                    }
                } 
                // se permiten autociclos
                else {
                    if (nodes_fin.contains(number_2) != true & nodes_inicio.contains(number_1) != true) {
                        flag = false;
                        nodes_fin.add(number_2);
                        nodes_inicio.add(number_1);
                    }
                }
            }
        }
                
        edges.put(nodes_inicio, nodes_fin);
        
        // Transforma HashMap a Set
        Set<Entry<ArrayList<Integer>,ArrayList<Integer>>> nodes_s;
        nodes_s = edges.entrySet();
        // Escribe en archivo de texto la llave y valor de Set
        try {
            PrintStream out = new PrintStream(new FileOutputStream("Graph_Erdos_and_Renyi_500.gv"));
            
            if (dirigido == false) {
                out.println("graph {");
                for(int i = 0; i < nodes_fin.size(); i++) {
                    out.println(nodes_inicio.get(i) + "--" + nodes_fin.get(i));
                }
               
                // Agrega las keys que faltan para completar todos los nodos
                for (int i = 0; i < number_of_nodes; i++){
                    if ((nodes_inicio.contains(i) != true) & (nodes_fin.contains(i) != true)){
                        out.println(i);
                    }
                }
                
                out.println("}");
            }
            else if (dirigido == true) {
                out.println("digraph {");
                for(int i = 0; i < nodes_fin.size(); i++) {
                    out.println(nodes_inicio.get(i) + "->" + nodes_fin.get(i));
                }
                
                // Agrega las keys que faltan para completar todos los nodos
                for (int i = 0; i < number_of_nodes; i++){
                    if ((nodes_inicio.contains(i) != true) & (nodes_fin.contains(i) != true)){
                        out.println(i);
                    }
                }
                
                out.println("}");
            }
            
        // Cierra archivo de texto
        out.close();
        }
        // Maneja las excepciones que haya al escribir en el archivo de texto
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
}
