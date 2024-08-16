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

    // bullet hole flag and coordinates
    boolean drawBulletHole = false;
    int bulletHoleX;
    int bulletHoleY;
    int bulletHoleWidth;
    int bulletHoleHeight;

    //bullet physical parameters (winchester 308)
    int muzzleVelocity = 884; //m/s
    int weight_grains = 150;
    double gravity = 9.81;
    double drop_cm = 0; //placeholder value

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
        bulletHolImage = new ImageIcon(getClass().getResource("./resources/bulletHole.png")).getImage();
        
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
        if (drawBulletHole) 
        {
            g.drawImage(bulletHolImage, bulletHoleX, bulletHoleY, bulletHoleWidth, bulletHoleHeight,null);
        }
        
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
    public void keyTyped(KeyEvent e) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
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

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            //paint bullethole on target (dont worry about drop for now, just paint at center)
            calculateBulletDrop();
            bulletHoleWidth = targetWidth / 10;
            bulletHoleHeight = targetHeight / 10;
            bulletHoleX = targetX + (targetWidth / 2) - (bulletHoleWidth / 2);
            bulletHoleY = targetY + (targetHeight / 2) - (bulletHoleHeight / 2);
            
            drawBulletHole = true;
            hitMissMessage();
            repaint();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) 
    {

        
    }


    public void calculateBulletDrop()
    {
     //scrapped what I had. will write again later.  
    }


    public boolean isTargetHit()
    {
       // Calculate the bounds of the target
    int targetRight = targetX + targetWidth;
    int targetBottom = targetY + targetHeight;

    // Calculate the center of the bullet hole
    int bulletHoleCenterX = bulletHoleX + bulletHoleWidth / 2;
    int bulletHoleCenterY = bulletHoleY + bulletHoleHeight / 2;

    // Check if the bullet hole's center is within the target bounds
    if (bulletHoleCenterX >= targetX && bulletHoleCenterX <= targetRight &&
        bulletHoleCenterY >= targetY && bulletHoleCenterY <= targetBottom) 
    {
        return true;
         // Bullet hole is within target bounds
    } 
    else 
    {
        return false; // Bullet hole is outside target bounds
    }
        
    }

    public void hitMissMessage()
    {
        isTargetHit();  //calling the isTargetHit method to determine whether or not the target is hit
        if (isTargetHit() == true)  //if it is hit, a hit message is displayed to console
        {
            System.out.println("Hit!");
            //displayInfo()
        }
        else if (isTargetHit() == false)    //if missed, miss message is displayed to console
        {
            System.out.println("Miss...");
            //displayInfo()
        }
    }

    public void displayInfo()
    {
        //show target distance
        //show hit or miss
        //show bullet drop
    }

    

    
}
