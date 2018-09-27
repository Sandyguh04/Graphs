package grafos;

import java.util.Random;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map.Entry;
import java.util.Set;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author flintlock
 */
public class Graph_Gilbert {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int number_of_edges = 500;
        double probabilidad = 0.3;
        double tiro;
        boolean dirigido = false;
        boolean auto_cycles = false;
        boolean flag = true;
        
        Random random = new Random();
        
        ArrayList<Integer> nodes_fin = new ArrayList<>();
        ArrayList<Integer> nodes_inicio = new ArrayList<>();
        HashMap<ArrayList<Integer>, ArrayList<Integer>> edges = new HashMap<>();
        
        // Genera pares de números aleatorios de 0 hasta número de nodos
        // Número de pares es igual al número de aristas
        for (int i = 0; i < number_of_edges; i++) {
            for (int j = 0; j < number_of_edges; j++) {
                flag = true;
                    if (auto_cycles != true) {
                            tiro = random.nextDouble();
                                if (tiro > probabilidad) {
                                    if (j != i & j >= i + 1){
                                        flag = false;
                                        nodes_inicio.add(i);
                                        nodes_fin.add(j);
                                    }
                                }
                    }
                    else {
                        tiro = random.nextDouble();
                        if (tiro > probabilidad) {
                            if (j != i & j >= i + 1){
                                flag = false;
                                nodes_inicio.add(i);
                                nodes_fin.add(j);
                            }
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
            PrintStream out = new PrintStream(new FileOutputStream("Graph_Gilbert_500.gv"));
            
            if (dirigido == false) {
                out.println("graph {");
                for(int i = 0; i < nodes_fin.size(); i++) {
                    out.println(nodes_inicio.get(i) + "--" + nodes_fin.get(i));
                }
                
                // Agrega las keys que faltan para completar todos los nodos
                for (int i = 0; i < number_of_edges; i++){
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
                for (int i = 0; i < number_of_edges; i++){
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
        
        //System.out.println(nodes);
        
    }
    
}