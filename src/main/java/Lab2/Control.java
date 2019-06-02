package Lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Control {
    private int[][] mass;


    public void enter() throws IOException {
        Graph graph =null;

        {

            System.out.println("Для ввода матрици введите  \n \"m\" \n и саму матрицу смежности (Матриця должна быть квадратной) по примеру:");
            System.out.println(" # # # # # \n # # # # #\n # # # # #\n # # # # # \n # # # # #");
            System.out.println("в конце укажите \"f\"");

            System.out.println("для печати матрици введити  \n\"print\" ");
            System.out.println("для печати пути между вершинами введите  \n\"path\" ");


            System.out.println("Завершить работу введите \n\"exit\" ");

            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                String s;
                boolean l = true;
                while (l) {
                    s = reader.readLine();
                    switch (s) {
                        case ("m"): {
                            int j = 0;
                            s = reader.readLine();
                            String smass[] = s.split(" ");
                            mass = new int[smass.length][smass.length];
                            while (true) {
                                if (s.equals("f")) {
                                    break;
                                }
                                for (int i = 0; i < smass.length; i++) {
                                    try {
                                        mass[i][j] = parseInt(smass[i]);
                                    } catch (NumberFormatException e) {
                                        System.out.println("В матрицу введено не число");
                                    }

                                }
                                j++;
                                s = reader.readLine();
                                smass = s.split(" ");
                            }
                            System.out.println("Если граф ориентирован укажите \"o\"" );
                            System.out.println("Если граф неориентирован укажите \"n\"");

                            s= reader.readLine();

                            if (s.equals("n")) {
                                int control = 0;
                                for (int i = 0; i < mass.length; i++) {
                                    for (int z = 0; z <= i; z++) {
                                        if (mass[i][z] != mass[z][i]) {
                                            System.out.println("Матрица не семетрична");
                                            control++;
                                            break;
                                        }
                                        if (control != 0) {
                                            break;
                                        }
                                    }
                                    if (control != 0) {
                                        break;
                                    }
                                }
                                if (control == 0) {
                                    graph = new Graph(Type.NOTORIENT, mass);

                                }
                            }

                           else if (s.equals("o"))
                             { graph =  new Graph(Type.ORIENT, mass);
                             }
                            else {
                                System.out.println("Введено не верное значение");
                            }
                            break;
                        }


                        case ("print"): {

                            graph.printMatriza();

                            break;

                        }

                        case ("path"): {

                            System.out.println("Введите индекс(номер) начальной вершины");
                            s = reader.readLine();
                            int first =0;
                            int end= 0;
                            try {
                                first= parseInt(s);
                                first = first -1;
                            } catch (NumberFormatException e) {
                                System.out.println("Введено не число");
                            }

                            System.out.println("Введите индекс(номер) конечной вершины");
                            s = reader.readLine();

                            try {
                                end= parseInt(s);
                                end = end -1;
                            } catch (NumberFormatException e) {
                                System.out.println("Введено не число");
                            }
                            if (end==first){
                                    graph.foundloops(end);
                            }
                            else if (first >=0&& first<= graph.getSizeM() && end >=0 && end<= graph.getSizeM())
                            {graph.waves(first,end);}
                            else
                            {
                                System.out.println("в графе нет таких вершин");
                            }
                            break;

                        }
                        case ("exit"): {
                            l = false;
                        }


                    }

                }

            }


        }
    }
}
