import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class App {
    //Image initialization
    Image targetImage;
    Image bulletHoleImage;
    
    public static void main(String[] args) throws Exception 
    {
        // Main variables
        int targetDistance = 100; // initial distance in meters
        double scopeZoom = 1.0; // initial zoom level
        double bulletVelocity = 800; // m/s, example value
        double gravity = 9.81; // m/s^2
        int WIDTH = 800;
        int HEIGHT = 800;

        JFrame frame = new JFrame();// Create window
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Real 2D Shooter");
        frame.setResizable(false);
        frame.setSize(WIDTH,HEIGHT);
        frame.setVisible(true);

        
    }
}
