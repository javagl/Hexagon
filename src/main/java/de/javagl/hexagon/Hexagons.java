/*
 * www.javagl.de - Hexagon
 *
 * Copyright (c) 2013-2015 Marco Hutter - http://www.javagl.de
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package de.javagl.hexagon;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Methods related to {@link Hexagon} instances
 */
public class Hexagons
{
    /**
     * A default instance of the origin
     */
    static final Point2D ORIGIN = new Point2D.Double();
    
    /**
     * A constant for the angle between two corners of a {@link Hexagon},
     * in radians
     */
    static final double ANGLE_STEP_RAD = 2 * Math.PI / 6;
    
    /**
     * Create a vertical {@link Hexagon}.<br>
     * <br>
     * <b>Vertical:</b>
     * <pre><code>
     *  __
     * /  \
     * \__/
     * </code></pre>
     * 
     * @param radius The radius) of the hexagon
     * @return The {@link Hexagon}
     */
    static Hexagon createVertical(double radius)
    {
        return new DefaultHexagon(radius, false);
    }
    
    /**
     * Create a horizontal {@link Hexagon}.<br>
     * <br>
     * <b>Horizontal:</b>
     * <pre><code>
     *  /\
     * |  |
     *  \/
     * </code></pre>
     * 
     * @param radius The radius of the hexagon
     * @return The {@link Hexagon}
     */
    static Hexagon createHorizontal(double radius)
    {
        return new DefaultHexagon(radius, true);
    }

    /**
     * Create the shape for the given {@link Hexagon}
     * 
     * @param hexagon The {@link Hexagon}
     * @return The shape
     */
    public static Shape createShape(Hexagon hexagon)
    {
        return createShape(hexagon, ORIGIN);
    }
    
    /**
     * Create the shape for the given {@link Hexagon} when its center
     * is at the given location
     * 
     * @param hexagon The {@link Hexagon}
     * @param center The center
     * @return The shape
     */
    public static Shape createShape(Hexagon hexagon, Point2D center)
    {
        Path2D p = new Path2D.Double();
        for (int i=0; i<6; i++)
        {
            double x = hexagon.getCornerX(i) + center.getX();
            double y = hexagon.getCornerY(i) + center.getY();
            if (i == 0)
            {
                p.moveTo(x, y);
            }
            else
            {
                p.lineTo(x, y);
            }
        }
        p.closePath();
        return p;
    }
    
    
    /**
     * Create a list containing the corner points of the given {@link Hexagon}
     * when its center is at the given location
     * 
     * @param hexagon The {@link Hexagon}
     * @param center The center
     * @return The list containing the corner points
     */
    static List<Point2D> createCorners(Hexagon hexagon, Point2D center)
    {
        List<Point2D> corners = new ArrayList<Point2D>(6);
        for (int i=0; i<6; i++)
        {
            double x = hexagon.getCornerX(i) + center.getX();
            double y = hexagon.getCornerY(i) + center.getY();
            corners.add(new Point2D.Double(x,y));
        }
        return corners;
    }

    
    /**
     * Computes the bounds of the given hexagon when its center is 
     * at the given position, and writes them into the given rectangle.
     * If the given rectangle is <code>null</code>, then a new rectangle 
     * will be created and returned
     * 
     * @param hexagon The {@link Hexagon}
     * @param center The center
     * @param bounds The result
     * @return The result
     */
    static Rectangle2D computeBounds(
        Hexagon hexagon, Point2D center, Rectangle2D bounds)
    {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;
        double maxY = -Double.MAX_VALUE;
        for (int i=0; i<6; i++)
        {
            double x = hexagon.getCornerX(i) + center.getX();
            double y = hexagon.getCornerY(i) + center.getY();
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }
        if (bounds == null)
        {
            bounds = new Rectangle2D.Double(); 
        }
        bounds.setRect(minX, minY, maxX-minX, maxY-minY);
        return bounds;
    }
    
    /**
     * Private constructor to prevent instantiation
     */
    private Hexagons()
    {
        // Private constructor to prevent instantiation
    }
    
}