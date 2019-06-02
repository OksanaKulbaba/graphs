package Lab1;


import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public class Adjacency {


    public int[][] getMatrix() {
        return matrix;
    }

    private int[][] matrix;
    private HashMap<Integer,Integer>  verTemp ;


    public void initMatrix(){
        verTemp = new HashMap<>();
        int size = (int)(Math.random()*10)+2;
        int chislo;
        int[][] tempMatrix = new int[size][size];
        for (int i=0; i < size; i++){
            for (int j=0; j <= i; j++){

            chislo = (int)(Math.random()*2);
            if (i==j){tempMatrix[i][j] = chislo;}
            else {
                tempMatrix[i][j] = chislo;
                tempMatrix[j][i] = chislo;}
            }
        }
        this.matrix = tempMatrix;
        initInerMatrix();
        printMatrix();
    }

    public void printMatrix(){
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j <matrix.length; j++){
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println("");
        }
    }


    public void initMatrix(int[][] mat){
        verTemp = new HashMap<>();
       this.matrix = mat;
        initInerMatrix();
        printMatrix();
    }
    public void initInerMatrix(){
        int temp = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j <matrix.length; j++){
                temp += matrix[j][i];
            }
            verTemp.put(i,temp);
            temp = 0;
        }
    }

    public void verticesGraph(){
       SortedSet<Integer> vertices = new ConcurrentSkipListSet<Integer>() {
       };

        for (int value: verTemp.values()){
            vertices.add(value);
        }
        System.out.println(vertices.toString());
    }

    public  void isolated(){
        ArrayList<Integer> isol = new ArrayList<>();
        for (Map.Entry entry: verTemp.entrySet()){
            if (entry.getValue().equals(0)){
                isol.add((Integer) entry.getKey());
            }
            }
        System.out.println(isol.toString());
    }

    public  void finishVertices() {

        ArrayList<Integer> isol = new ArrayList<>();
        for (Map.Entry entry: verTemp.entrySet()){
            if (entry.getValue().equals(1)){
                isol.add((Integer) entry.getKey());
            }
        }
        System.out.println(isol.toString());
    }
}




