import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class real2dshooter extends JPanel implements KeyListener
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
    int targetWidth = 100;
    int targetHeight = 100;
    int targetDistance = 0;

    
    
    real2dshooter()
    {
        //adding key listener
        this.addKeyListener(this);
        
        // Make the panel focusable and request focus
        setFocusable(true);
        requestFocusInWindow();
        
        //set pref size
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
        
        //increase distance by 100m each click
        //target width = distance/10 (arbitrary conversion factor... will do real math later)
        //target height = distance/10 (also arbitrary)
        // Increase or decrease target size based on distance
        // Example: Reduce size as distance increases
        targetWidth = 200 - (targetDistance / 10); // Arbitrary conversion factor
        targetHeight = 200 - (targetDistance / 10);

        // Ensure the target doesn't get too small or too large
        if (targetWidth < 20) targetWidth = 20;
        if (targetHeight < 20) targetHeight = 20;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_PLUS || e.getKeyCode() == KeyEvent.VK_EQUALS) // '+' key
        {
            targetDistance += 100;
            changeTargetDistance(); // Update target size
            repaint(); // Redraw with new size
        }
        if (e.getKeyCode() == KeyEvent.VK_MINUS) // '-' key
        {
            targetDistance -= 100;
            changeTargetDistance(); // Update target size
            repaint(); // Redraw with new size
        }

        System.out.println(targetDistance);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        
    }

    
}
