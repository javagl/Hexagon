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

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Methods to create {@link HexagonGrid} instances
 */
public class HexagonGrids
{
    /**
     * Creates a new default {@link HexagonGrid}
     * 
     * @param radius The radius of the hexagons 
     * @param vertical Whether the {@link Hexagon}s are oriented vertically
     * @param evenShifted Whether the even rows/columns are shifted
     * @return The {@link HexagonGrid}
     */
    public static HexagonGrid create(
        double radius, boolean vertical, boolean evenShifted)
    {
        if (vertical)
        {
            if (evenShifted)
            {
                return new VerticalEvenShiftedHexagonGrid(radius);
            }
            return new VerticalOddShiftedHexagonGrid(radius);
        }
        if (evenShifted)
        {
            return new HorizontalEvenShiftedHexagonGrid(radius);
        }
        return new HorizontalOddShiftedHexagonGrid(radius);
    }
    
    
    /**
     * Computes the bounds of the specified portion of the given 
     * {@link HexagonGrid}, and writes them into the given rectangle.
     * If the given rectangle is <code>null</code>, then a new rectangle 
     * will be created and returned
     * 
     * @param hexagonGrid The {@link HexagonGrid}
     * @param x0 The upper left x-coordinate
     * @param y0 The upper left y-coordinate
     * @param x1 The lower right x-coordinate (inclusive!)
     * @param y1 The lower right y-coordinate (inclusive!)
     * @param bounds The result
     * @return The result
     */
    public static Rectangle2D computeBounds(
        HexagonGrid hexagonGrid, int x0, int y0, int x1, int y1, 
        Rectangle2D bounds)
    {
        Rectangle2D r = new Rectangle2D.Double();
        Point2D center = new Point2D.Double();

        hexagonGrid.getCenter(x0, y0, center);
        Hexagons.computeBounds(hexagonGrid.getHexagon(), center, r);
        if (bounds == null)
        {
            bounds = new Rectangle2D.Double(); 
        }
        bounds.setRect(r);
        
        hexagonGrid.getCenter(x1, y1, center);
        Hexagons.computeBounds(hexagonGrid.getHexagon(), center, r);
        Rectangle2D.union(r, bounds, bounds);
        
        if (x0 + 1 < x1 - 1)
        {
            hexagonGrid.getCenter(x0+1, y0, center);
            Hexagons.computeBounds(hexagonGrid.getHexagon(), center, r);
            Rectangle2D.union(r, bounds, bounds);
        }
        if (x1 - 1 > x0 + 1)
        {
            hexagonGrid.getCenter(x1-1, y1, center);
            Hexagons.computeBounds(hexagonGrid.getHexagon(), center, r);
            Rectangle2D.union(r, bounds, bounds);
        }
        if (y0 + 1 < y1 - 1)
        {
            hexagonGrid.getCenter(x0, y0+1, center);
            Hexagons.computeBounds(hexagonGrid.getHexagon(), center, r);
            Rectangle2D.union(r, bounds, bounds);
        }
        if (y1 - 1 > y0 + 1)
        {
            hexagonGrid.getCenter(x1, y1-1, center);
            Hexagons.computeBounds(hexagonGrid.getHexagon(), center, r);
            Rectangle2D.union(r, bounds, bounds);
        }
        
        return bounds;
    }

    /**
     * Private constructor to prevent instantiation
     */
    private HexagonGrids()
    {
        // Private constructor to prevent instantiation
    }
}
