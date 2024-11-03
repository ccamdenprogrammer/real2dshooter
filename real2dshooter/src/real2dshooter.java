import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class real2dshooter extends JPanel implements KeyListener {
    // Image initialization
    Image backgroundImage;
    Image targetImage;
    Image bulletHoleImage;

    // Screen dimensions
    int WIDTH = 800;
    int HEIGHT = 800;

    // Target variable initialization
    int targetX = 300;
    int targetY = 300;
    int targetWidth = 100;
    int targetHeight = 100;
    int targetDistance = 100; // initial target distance in meters
    int initialTargetSize = 50; // size at 100 meters in pixels

    // Bullet hole and drop calculations
    boolean drawBulletHole = false;
    int bulletHoleX;
    int bulletHoleY;
    int bulletHoleWidth;
    int bulletHoleHeight;
    double drop_cm = 0;
    int muzzleVelocity = 884; // m/s
    double gravity = 9.81;
    double elevationAngle = 0;

    // Hit/miss message variables
    private String hitMissMessage = "";
    private Timer messageTimer;

    real2dshooter() {
        // Add key listener
        this.addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        // Set panel size
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Load images
        try {
            targetImage = new ImageIcon(getClass().getResource("./resources/targetIMG.png")).getImage();
            bulletHoleImage = new ImageIcon(getClass().getResource("./resources/bulletHole.png")).getImage();
            backgroundImage = new ImageIcon(getClass().getResource("./resources/backgroundIMG.jpg")).getImage();
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
        }

        // Message timer to show hit/miss
        messageTimer = new Timer(3000, e -> {
            hitMissMessage = ""; // Clear message after 3 seconds
            drawBulletHole = false; // Optionally reset bullet hole
            repaint();
        });
        messageTimer.setRepeats(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null); // background
        targetReCenter(); // recenters target
        g.drawImage(targetImage, targetX, targetY, targetWidth, targetHeight, null); // target
        if (drawBulletHole) {
            g.drawImage(bulletHoleImage, bulletHoleX, bulletHoleY, bulletHoleWidth, bulletHoleHeight, null);
        }

        // Draw the current distance in meters in the top left corner
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        String distanceText = "Distance: " + targetDistance + " meters";
        g.drawString(distanceText, 10, 20);

        // Display the current elevation angle in the bottom left corner
        String angleText = "Angle: " + String.format("%.1f", elevationAngle) + "°";
        g.drawString(angleText, 10, 40); // 40 pixels down from the top left corner

        // Draw the hit/miss message in the top right corner if it's set
        if (!hitMissMessage.isEmpty()) {
            FontMetrics metrics = g.getFontMetrics();
            int messageWidth = metrics.stringWidth(hitMissMessage);
            g.setColor(Color.YELLOW);
            g.drawString(hitMissMessage, WIDTH - messageWidth - 10, 20); // 10 pixels from the right edge
        }
    }

    public void targetReCenter() {
        targetX = (WIDTH / 2) - (targetWidth / 2);
        targetY = (HEIGHT / 2) - (targetHeight / 2);
    }

    public void changeTargetDistance() {
        double scale = 100.0 / targetDistance;
        targetWidth = (int) (initialTargetSize * scale);
        targetHeight = (int) (initialTargetSize * scale);
        targetReCenter();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_PLUS || e.getKeyCode() == KeyEvent.VK_EQUALS) { // '+' key
            targetDistance += 100;
            changeTargetDistance();
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_MINUS) { // '-' key
            targetDistance -= 100;
            if (targetDistance < 100) targetDistance = 100;
            changeTargetDistance();
            repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            calculateBulletDrop(); // Calculate bullet drop with current angle
            bulletHoleWidth = targetWidth / 10;
            bulletHoleHeight = targetHeight / 10;
            bulletHoleX = targetX + (targetWidth / 2) - (bulletHoleWidth / 2);
            bulletHoleY = targetY + (targetHeight / 2) - (bulletHoleHeight / 2) + (int)(drop_cm / (100.0 / targetDistance));
            drawBulletHole = true;
            hitMissMessage();
            repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) { // Increase angle
            elevationAngle += 0.1; // Increment angle by 0.1 degree
            calculateBulletDrop(); // Recalculate drop with new angle
            repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) { // Decrease angle
            elevationAngle -= 0.1; // Decrement angle by 0.1 degree
            if (elevationAngle < 0) elevationAngle = 0; // Prevent negative angles
            calculateBulletDrop(); // Recalculate drop with new angle
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public void calculateBulletDrop() {
        double angleInRadians = Math.toRadians(elevationAngle); // Convert angle to radians
        double horizontalDistance = targetDistance / Math.cos(angleInRadians); // Effective distance

        double time = horizontalDistance / muzzleVelocity; // Adjusted time of travel
        drop_cm = (0.5 * gravity * time * time - muzzleVelocity * Math.sin(angleInRadians) * time) * 100;
    }

    public boolean isTargetHit() {
        int targetRight = targetX + targetWidth;
        int targetBottom = targetY + targetHeight;
        int bulletHoleRight = bulletHoleX + bulletHoleWidth;
        int bulletHoleBottom = bulletHoleY + bulletHoleHeight;

        return bulletHoleX >= targetX && bulletHoleX <= targetRight && bulletHoleY >= targetY && bulletHoleY <= targetBottom;
    }

    public void hitMissMessage() {
        if (isTargetHit()) {
            hitMissMessage = "Hit!";
        } else {
            hitMissMessage = "Miss!";
        }
        messageTimer.restart(); // Restart message timer
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Shooter Game");
        real2dshooter gamePanel = new real2dshooter();
        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
