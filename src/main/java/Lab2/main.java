package Lab2;

import java.io.IOException;

public class main  {
    public static void main(String[] args)  {
        Control control = new Control();
       try  {
       control.enter();}
       catch (IOException e){
           System.out.println("ОШИБКА");
       }

    }
}
