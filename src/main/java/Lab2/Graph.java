package Lab2;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Graph {
    private Type type;
    private int adjMat[][]; // adjacency matrix

    private Vertex vertexList[]; // list of vertices

    public int getSizeM() {
        return sizeM;
    }

    private int  sizeM; // размер матрици, количество вершин
    private int nVerts =0; // current number of vertices

    private static final Random random = new Random();
    private HashMap<Integer,Integer> verTemp ; // для подсчета степеней
    List<Integer> tempVertex = new ArrayList<>(); // для пути
    private Queue queue;


    public Graph(Type type, int[][] adjMat) {
        this.type = type;
        this. adjMat = adjMat;
        this.sizeM = adjMat.length;
        initVerties();

    }

    public Graph(Type type, int sizeM) {
        this.type = type;
        this.sizeM = sizeM;
            if(type.equals(Type.ORIENT)){
                initOrientMatrix(sizeM);
            }
            else {
                initNotOrientMatrix(sizeM);
            }
        initVerties();
    }

    public Graph(int sizeM) {
        type =Type.values()[random.nextInt(2)];
        this.sizeM = sizeM;

        if(type.equals(Type.ORIENT)){
            initOrientMatrix(sizeM);
        }
        else {
            initNotOrientMatrix(sizeM);
        }
        initVerties();

    }

    public void initOrientMatrix(int sizeM){
        adjMat = new int [sizeM][sizeM];

        verTemp = new HashMap<>();
          int chislo;
          int z = 65;
        int[][] tempMatrix = new int[sizeM][sizeM];
        for (int i=0; i < sizeM; i++){
            for (int j=0; j <= i; j++){

                chislo = (int)(Math.random()*2);
                if (i==j){tempMatrix[i][j] = chislo;}
                else {
                    tempMatrix[i][j] = chislo;
                    tempMatrix[j][i] = chislo;
                    }
            }
        }
        this.adjMat = tempMatrix;

        initInerMatrix(); // для подсчета степеней

    }
    // нет реализации
    public void initNotOrientMatrix(int sizeM){
        adjMat = new int [sizeM][sizeM];


    }
    public void initInerMatrix(){  // для подсчета степеней
        int temp = 0;
        for (int i = 0; i < adjMat.length; i++){
            for (int j = 0; j <adjMat.length; j++){
                temp += adjMat[j][i];
            }
            verTemp.put(i,temp);
            temp = 0;
        }
    }  // Для подсчета стпени венршини

    public void initVerties(){
        vertexList = new Vertex[adjMat.length];
        int v = 1;
        for (int i = 0; i<adjMat.length; i++){
            addVertex(v++, i);
        }
        queue = new Queue(sizeM);
    } //  для автоматической генерации названий вершин
    public void addVertex(int l, int i) {
        vertexList[nVerts++] = new Vertex(l, i);
    } // добавление новой вершини

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    } // добавление нового ребра
    // печать вершини
    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }
    // печать индекса вонлны вершины
    public void displayVertexWave(int v) {
        System.out.print("("+ vertexList[v].wave + ") ");
    }

    // breadth-first search
    public void waves(int s, int e) { // begin at vertex 0
       int v, v1;
       // обнуление посещение вершин
        for (int j = 0; j < sizeM; j++) {
            vertexList[j].wasVisited = false;
        }

        v1 = -1;
        int wave = 0;// индекс волни
        vertexList[s].wasVisited = true; // mark it
        displayVertex(s);
        displayVertexWave(s);// display it
        queue.insert(s); // insert at tail
        vertexList[s].wave = wave;

        while (true) // until queue empty,
        {
            if (queue.isEmpty()) {
                System.out.println("Путь не найден");
                break;}
            if (v1 == e) {
                System.out.print("Путь найден: ");
               tempVertex.add(v1);

                getPath(v1);
                printPath();
                System.out.println();
                break;
            }

            v = queue.remove(); // remove vertex at head
            // until it has no unvisited neighbors
            for (int j = 0; j < sizeM; j++) {
                if (adjMat[j][v] == 1 && vertexList[j].isAvailable() == true
                        && vertexList[j].wasVisited == false) {
                    queue.insert(j);
                    vertexList[j].wasVisited = true;
                    vertexList[j].parent = vertexList[v];
                    vertexList[j].wave = (vertexList[j].getParentWave())+1;

                    if (e == j) {
                        v1 = j;
                    }
                }
            }

        }
        // queue is empty, so we're done
        for (int j = 0; j < nVerts; j++) {
            // reset flags
            vertexList[j].wasVisited = false;
            vertexList[j].parent = null;
        }

        this.queue = new Queue(sizeM);
    }


    private void getPath(int vertex) {

        int v = vertexList[vertex].getParentIndex();
        if (v == -1) {
            System.out.println("нет ");
            return;
        }
        tempVertex.add(v);
        getPath(v);
    }
    // returns an unvisited vertex adj to v
    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < nVerts; j++)
            if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
                return j;
        return -1;
    }


    public void printPath(){
        for (int i = tempVertex.size()-1; i>= 0; i-- ){

            displayVertex(tempVertex.get(i));
            displayVertexWave(tempVertex.get(i));

        }
        tempVertex = new ArrayList<>();
    }
    public void printMatriza(){
        System.out.println();
        System.out.print("  ");
        for (int i =0; i < adjMat.length; i++){
            displayVertex(i);
            System.out.print(" ");
        }
        System.out.println();
        for (int i=0; i <adjMat.length;i++ ){
            displayVertex(i);
            for (int j =0; j < adjMat.length; j++){
                System.out.print(" ");
                System.out.print(adjMat[j][i]);
            }
            System.out.println();
        }

    }


    public void  foundloops(int v){
        if (adjMat[v][v] ==1){
            System.out.println("Путь найден");
            displayVertex(v);
            displayVertexWave(v);
        }
    }
}





