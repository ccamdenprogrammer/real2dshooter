import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class App { 
    public static void main(String[] args) throws Exception 
    {
        // Main variables
        int WIDTH = 800; //frame width
        int HEIGHT = 800; //frame height

        JFrame frame = new JFrame();// Create window
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Real 2D Shooter");
        frame.setResizable(false);
        frame.setSize(WIDTH,HEIGHT);
        

        real2dshooter R2DS = new real2dshooter();
        frame.add(R2DS);
        frame.pack();
        frame.setVisible(true);

        

    }
}
