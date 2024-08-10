import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class real2dshooter extends JPanel 
{
    //image initialization
    Image backgroundImage;
    Image targetImage;
    Image bulletHolImage;
    
    int WIDTH = 800; //board size init
    int HEIGHT = 800;

    //target variable initialization
    int targetX = 300;
    int targetY = 300;
    int targetWidth = 10;
    int targetHeight = 10;

    real2dshooter()
    {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        //Load images

        targetImage = new ImageIcon(getClass().getResource("./resources/targetIMG.png")).getImage();
        bulletHolImage = new ImageIcon(getClass().getResource("./resources/bulletHole.png")).getImage();
        backgroundImage = new ImageIcon(getClass().getResource("./resources/backgroundIMG.jpg")).getImage();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    {
        g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null); //background
        targetReCenter(); // recenters target
        g.drawImage(targetImage, targetX, targetY, targetWidth, targetHeight, null); //target
    }

    public void targetReCenter()
    {
        //recenters target after resizing for distance
        targetX = (WIDTH/2)-(targetWidth/2);
        targetY = (HEIGHT/2)-(targetHeight/2);
    }

    public void changeTargetDistance()
    {
        //add button that changes target distance 100 meters per click.
    }
}
