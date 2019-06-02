package Lab3;


import javax.swing.*;
import java.awt.*;


import guru.nidi.graphviz.engine.Graphviz;
import org.jgrapht.*;
import org.jgrapht.ext.JGraphXAdapter;

public class Print {



    public static void printGraf(Graph j){
        JFrame jFrame  = new JFrame();

       jFrame.setTitle("Graph");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(1000, 1000);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);







    }

}
