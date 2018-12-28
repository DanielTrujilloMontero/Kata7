package watch2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WatchDisplay extends JPanel {
    private final Image background;
    private Point[] points;

    public WatchDisplay() {
        this.background = load("background.png");
    }
        
    
    public void paint(Point[] points) {
        this.points = points;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Point o = new Point(getWidth()/2, getHeight()/2);
        g.drawImage(background, 0, 0, this);
        g.setColor(Color.white);
        int width = 1;
        for (Point point : points) {
            g2(g).setStroke(new BasicStroke(width));
            width += 2;
            g.drawLine(o.x, o.y, o.x+point.x, o.y-point.y);
        }
        
    }

    private Image load(String filename) {
        try {
            return ImageIO.read(new BufferedInputStream(new FileInputStream(filename)));
        } catch (IOException e) {
            Logger.getLogger(WatchDisplay.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    private Graphics2D g2(Graphics g) {
        return (Graphics2D) g;
    }
    
    
    
}
