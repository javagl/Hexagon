package de.javagl.hexagon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Integration test for the {@link Hexagon} and {@link HexagonGrid} classes
 */
@SuppressWarnings({"javadoc"})
public class HexagonPaintTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI()
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        HexagonPaintTestPanel p = new HexagonPaintTestPanel();
        f.getContentPane().add(p);
        
        HexagonGrid hexagonGrid = HexagonGrids.create(30, true, true);
        p.setHexagonGrid(hexagonGrid);
        
        f.setSize(600,600);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}


@SuppressWarnings({"javadoc", "serial"})
class HexagonPaintTestPanel extends JPanel
{
    private HexagonGrid hexagonGrid;
    
    public void setHexagonGrid(HexagonGrid hexagonGrid)
    {
        this.hexagonGrid = hexagonGrid;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics gr)
    {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D)gr;
        
        if (hexagonGrid == null)
        {
            return;
        }
        Hexagon hexagon = hexagonGrid.getHexagon();
        for (int x=0; x<100; x++)
        {
            for (int y=0; y<100; y++)
            {
                Point2D center = hexagonGrid.getCenter(x, y, null);
                Shape shape = Hexagons.createShape(hexagon, center);
                g.setColor(Color.LIGHT_GRAY);
                g.fill(scaleAboutCenter(shape, 0.9));
                g.setColor(Color.BLACK);
                g.draw(scaleAboutCenter(shape, 0.9));
            }
        }
        
        int cx = 5;
        int cy = 5;
        for (int d=0; d<6; d++)
        {
            Point2D c = hexagonGrid.getCenter(cx, cy, null);
            g.drawString(cx+","+cy, (int)c.getX(), (int)c.getY());

            Point n = hexagonGrid.getNeighbor(cx, cy, d, null);
            Point2D nc = hexagonGrid.getCenter(n.x, n.y, null);
            g.drawString(""+d, (int)nc.getX(), (int)nc.getY());
        }
    }
    
    private static Shape scaleAboutCenter(Shape shape, double factor)
    {
        Rectangle2D bounds = shape.getBounds2D();
        double cx = bounds.getCenterX();
        double cy = bounds.getCenterY();
        AffineTransform at0 = AffineTransform.getTranslateInstance(cx, cy);
        AffineTransform at1 = AffineTransform.getScaleInstance(0.9, 0.9);
        AffineTransform at2 = AffineTransform.getTranslateInstance(-cx, -cy);
        AffineTransform at = new AffineTransform();
        at.concatenate(at0);
        at.concatenate(at1);
        at.concatenate(at2);
        return at.createTransformedShape(shape);
    }
}


