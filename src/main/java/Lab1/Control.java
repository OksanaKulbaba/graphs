package Lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Control {
    private int[][] mass;
    Adjacency adjacency  = new Adjacency();

    public void control () throws IOException

    {

        System.out.println("Для ввода матрици введите  \n \"m\" \n и саму матрицу смежности (Матриця должна быть квадратной) по примеру:");
        System.out.println(" # # # # # \n # # # # #\n # # # # #\n # # # # # \n # # # # #");
        System.out.println("в конце укажите \"f\"");
        System.out.println("Для генерации произвольной матрици введите \n\"p\" ");
        System.out.println("Для определение количества вершин \n \"v\" ");


        System.out.println("Для вывода ступений вершин графов введите \n \"s\"");
        System.out.println("Для выводу изолированых вершин \n\"i\"");
        System.out.println("Для выводу конечных вершин \n\"k\"");
        System.out.println("Завершить работу введите \n\"e\" ");

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s;
            String[] sMass;
           boolean l = true;
            while (l) {
                s = reader.readLine();
                switch (s){
                    case ("m"):
                    {   int j = 0;
                        s = reader.readLine();
                        String smass[] = s.split(" ");
                        mass = new int[smass.length][smass.length];
                       while (true){
                       if (s.equals("f")){
                            break;
                        }
                        for (int i = 0; i < smass.length; i++) {
                           try {
                               mass[i][j] = parseInt(smass[i]);
                           }
                          catch (NumberFormatException e){
                             System.out.println("В матрицу введено не число");
                           }

                        }
                        j++;
                        s = reader.readLine();
                        smass= s.split(" ");
                    }
                        int control = 0;
                        for (int i=0; i < mass.length; i++){
                            for (int z =0; z <= i; z++){
                                if (mass[i][z] != mass[z][i]){
                                    System.out.println("Матрица не семетрична");
                                    control++;
                                    break;
                                }
                                if(control !=0){break;}
                            }
                            if (control != 0) {break;}
                        }
                        if (control==0){
                      adjacency.initMatrix(mass);
                      System.out.println("corect");}
                    break;
                    }
                    case("s"):{
                       adjacency.verticesGraph();
                        break;

                    }
                    case("p"):{

                        adjacency.initMatrix();
                        System.out.println("corect");
                        break;

                    }
                    case ("i"):{
                        adjacency.isolated();
                        break;

                    }
                    case ("k"):{
                        adjacency.finishVertices();
                        break;
                        
                    }
                    case("e"):{
                        l = false;
                    }


                }

            }

        }
    }
}
