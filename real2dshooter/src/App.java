import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class App {
    

    // Main variables
    int targetDistance = 100; // initial distance in meters
    double scopeZoom = 1.0; // initial zoom level
    double bulletVelocity = 800; // m/s, example value
    double gravity = 9.81; // m/s^2


    //Image initialization
    Image targetImage;
    Image bulletHoleImage;
    
    // Initialize GUI components
    void initializeGUI() {
        JFrame frame = new JFrame();// Create window
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Real 2D Shooter");
        frame.setResizable(false);
        frame.setSize(800,800);
        frame.setVisible(true);

        //Load Images
        targetImage = new ImageIcon(getClass().getResource("./resources/targetIMG")).getImage();

        
        // Add distance adjustment buttons
        // Add zoom adjustment buttons
        // Add fire button (space bar event)
    }
    


    public static void main(String[] args) throws Exception 
    {
        
    }
}
