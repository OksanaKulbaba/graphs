package Lab3;

import java.io.*;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class creatMatrix {

    public static void createMatrix(){
        File  file = new File("D://KPI//Summer 2019//Diskretka//graphs", "adjMatrix.txt");
        FileWriter fileWriter;

        try {

            fileWriter = new FileWriter (file);
            fileWriter.write("");
            fileWriter.close();

            fileWriter =  new FileWriter(file, true);

            int b = 10;
            int N = 10;
            String s = "";
            for (int i =0; i <N; i++){

                for (int j =0; j <N; j++){

                    if (j ==0){
                        s =(int) (Math.random()*b)+"";
                    }
                        else{
                            s += " " +  (int) (Math.random()*b)+"";
                    }

                }
                fileWriter.write(s);
                fileWriter.write("\n");
                s="";
            }

            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("ошибка в файле");
        }


    }

}
