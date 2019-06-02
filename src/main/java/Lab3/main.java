package Lab3;

import org.jgrapht.DirectedGraph;


import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


import static java.lang.Integer.parseInt;


public class main {

    public static void main(String[] args) {


        File file = new File("D://KPI//Summer 2019//Diskretka//graphs", "adjMatrix.txt");
        FileInputStream fileInputStream;

        DirectedGraph<String, DefaultEdge> directedGraph1
                = new DefaultDirectedGraph<>(DefaultEdge.class);
        DefaultDirectedWeightedGraph <String,DefaultEdge> directedGraph = new DefaultDirectedWeightedGraph<String, DefaultEdge>(DefaultEdge.class);

        int mass[][] = null;
        // creatMatrix.createMatrix();

        try {
            fileInputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            String s;


            while (reader.ready()) {
                s = reader.readLine();
                String smass[] = s.split(" ");

                mass = new int[smass.length][smass.length];
                int j =0;

                while (j <smass.length) {

                    for (int i = 0; i < smass.length; i++) {
                        try {
                            mass[j][i] = parseInt(smass[i]);

                        } catch (NumberFormatException e) {
                            System.out.println("В матрицу введено не число");
                        }
                    }
                    directedGraph.addVertex("v" + (j+1));
                    j++;
                   if(j <smass.length){
                    s = reader.readLine();
                    smass = s.split(" ");}
                }
            }
            for(int i =0; i < mass.length; i++){
                for (int j =0 ; j < mass.length; j++){
                    if (mass[j][i] > 0){
                        directedGraph.addEdge("v" + (j+1),"v" + (i+1));

                    }
                }
            }

            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("ошибка в файле");
        }
       // DefaultDirectedWeightedGraph = geaph1

       DirectedGraph graph1 =directedGraph;


        System.out.println("Введіть дві вершини у форматі v1 v5, де цифра відповідає номеру вершини");
        String ss[]= null;
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s;
            boolean l = true;
            while (l) {
                s = reader.readLine();
                ss = s.split(" ");
                l = false;
            }
        }
        catch (IOException e){
            System.out.println("Введено не верное значення");
        }
            if(ss[0]==ss[1]){

                getPathBeetweOneVertex(directedGraph,ss[0]);
            }
            else

            getPath(directedGraph, ss[0], ss[1]);

    }

    public static  void getPathBeetweOneVertex (DefaultDirectedWeightedGraph graph, String v1) {
        List<List<GraphPath>> allPath = new ArrayList<>();
        AllDirectedPaths allDirectedPaths;

        try {
            while (true) {
                allDirectedPaths = new AllDirectedPaths(graph);
                List<GraphPath> shortestPath = allDirectedPaths
                        .getAllPaths(v1,v1,true,100);
                    allPath.add(shortestPath);
               // graph.removeAllEdges(shortestPath);
            }

        } catch (NullPointerException e) {

            for (List<GraphPath> list : allPath
            ) {
                System.out.println(list);

            }

            System.out.println("Больше путей нет");


        }
    }



    public static void getPath(DefaultDirectedWeightedGraph graph, String v1, String v2){
        List<List<String>> allUnikPath = new ArrayList<>();
        DijkstraShortestPath dijkstraShortestPath;
        try {
            while (true) {
                 dijkstraShortestPath = new DijkstraShortestPath(graph);
                 List<String> shortestPath = dijkstraShortestPath
                            .getPath(v1, v2).getEdgeList();
                    allUnikPath.add(shortestPath);
                    graph.removeAllEdges(shortestPath);
                }

        }
        catch (NullPointerException e){

            for (List<String> list: allUnikPath
                 ) {
                System.out.println(list);

            }

            System.out.println("Больше путей нет");

        }

    }




}
