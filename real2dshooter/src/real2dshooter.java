import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class real2dshooter extends JPanel implements KeyListener
{
    // image initialization
    Image backgroundImage;
    Image targetImage;
    Image bulletHolImage;
    
    int WIDTH = 800; // board size init
    int HEIGHT = 800;

    // target variable initialization
    int targetX = 300;
    int targetY = 300;
    int targetWidth = 100;
    int targetHeight = 100;
    int targetDistance = 100; // initial target distance, set to 100 meters
    int initialTargetSize = 50; // Set this to reflect the desired size at 100 meters (in pixels)
    double realTargetSize = 1.0; // 1m x 1m in real life

    // bullet hole flag and coordinates
    boolean drawBulletHole = false;
    int bulletHoleX;
    int bulletHoleY;
    int bulletHoleWidth;
    int bulletHoleHeight;

    // bullet physical parameters (winchester 308)
    int muzzleVelocity = 884; // m/s
    int weight_grains = 150;
    double gravity = 9.81;
    double drop_cm = 0; // placeholder value

    // hit/miss message variables
    private String hitMissMessage = "";
    private Timer messageTimer;

    real2dshooter()
    {
        // adding key listener
        this.addKeyListener(this);
        
        // Make the panel focusable and request focus
        setFocusable(true);
        requestFocusInWindow();
        
        // set pref size
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Load images
        targetImage = new ImageIcon(getClass().getResource("./resources/targetIMG.png")).getImage();
        bulletHolImage = new ImageIcon(getClass().getResource("./resources/bulletHole.png")).getImage();
        backgroundImage = new ImageIcon(getClass().getResource("./resources/backgroundIMG.jpg")).getImage();

        // Initialize the message timer
        messageTimer = new Timer(3000, e -> {
            hitMissMessage = ""; // Clear the message after 3 seconds
            repaint();
        });
        messageTimer.setRepeats(false); // Timer should only run once
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    {
        g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null); // background
        targetReCenter(); // recenters target
        g.drawImage(targetImage, targetX, targetY, targetWidth, targetHeight, null); // target
        if (drawBulletHole) {
            g.drawImage(bulletHolImage, bulletHoleX, bulletHoleY, bulletHoleWidth, bulletHoleHeight, null);
        }
        
        // Draw the current distance in meters in the top left corner
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        String distanceText = "Distance: " + targetDistance + " meters";
        g.drawString(distanceText, 10, 20);

        // Draw the hit/miss message in the top right corner if it's set
        if (!hitMissMessage.isEmpty()) {
            FontMetrics metrics = g.getFontMetrics();
            int messageWidth = metrics.stringWidth(hitMissMessage);
            g.setColor(Color.YELLOW);
            g.drawString(hitMissMessage, WIDTH - messageWidth - 10, 20); // 10 pixels from the right edge
        }
    }

    public void targetReCenter()
    {
        // recenters target after resizing for distance
        targetX = (WIDTH / 2) - (targetWidth / 2);
        targetY = (HEIGHT / 2) - (targetHeight / 2);
    }

    public void changeTargetDistance()
    {
        // Calculate the size of the target based on its distance
        double scale = 100.0 / targetDistance; // Scaling factor, 100m is the baseline
        targetWidth = (int) (initialTargetSize * scale);
        targetHeight = (int) (initialTargetSize * scale);
        targetReCenter(); // Re-center the target after resizing
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
            // Ensure target distance doesn't go negative
            if (targetDistance < 100) targetDistance = 100; // Prevent distance from going below 100m
            changeTargetDistance(); // Update target size
            repaint(); // Redraw with new size
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            // Paint bullet hole on target (ignore drop for now, just paint at center)
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
    public void keyReleased(KeyEvent e) {
    }

    public void calculateBulletDrop()
    {
        // scrapped what I had. will write again later.  
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
        return bulletHoleCenterX >= targetX && bulletHoleCenterX <= targetRight &&
               bulletHoleCenterY >= targetY && bulletHoleCenterY <= targetBottom;
    }

    public void hitMissMessage()
    {
        if (isTargetHit())
        {
            hitMissMessage = "Hit!";
        }
        else
        {
            hitMissMessage = "Miss...";
        }
        
        messageTimer.restart(); // Restart the timer to display the message for 3 seconds
        repaint();
    }

}