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

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Implementation of a {@link HexagonGrid} with vertical hexagons where
 * the even columns are shifted along the positive y-axis
 */
class VerticalEvenShiftedHexagonGrid implements HexagonGrid
{
    /**
     * The neighbor offsets
     */
    private static final int NEIGHBORS[][][] = new int[][][]{
        { {+1, +1}, {+1,  0}, { 0, -1},
          {-1,  0}, {-1, +1}, { 0, +1} },
        { {+1,  0}, {+1, -1}, { 0, -1},
          {-1, -1}, {-1,  0}, { 0, +1} }
    };
    
    /**
     * The template {@link Hexagon}
     */
    private final Hexagon hexagon;
    
    /**
     * Creates a new hexagon grid
     * 
     * @param radius The radius of the {@link #getHexagon() Hexagon}
     */
    VerticalEvenShiftedHexagonGrid(double radius)
    {
        this.hexagon = Hexagons.createVertical(radius);
    }

    @Override
    public Hexagon getHexagon()
    {
        return hexagon;
    }

    @Override
    public Point2D getCenter(int x, int y, Point2D p)
    {
        Hexagon hexagon = getHexagon();
        double cx = x*hexagon.getSpacingX();
        double cy = y*hexagon.getSpacingY();
        
        boolean oddX = ((x & 1) == 1);
        if (!oddX)
        {
            cy += 0.5 * hexagon.getSizeY();
        }
        if (p == null)
        {
            p = new Point2D.Double(cx, cy);
        }
        else
        {
            p.setLocation(cx, cy);
        }
        return p;
    }
    
    @Override
    public Point getNeighbor(int x, int y, int direction, Point p)
    {
        if (direction < 0 || direction > 6)
        {
            throw new IllegalArgumentException(
                "Direction must be in [0,6), but is "+direction);
        }
        int parity = x & 1;
        int d[] = NEIGHBORS[parity][direction];        
        if (p == null)
        {
            p = new Point();
        }
        p.x = x + d[0];
        p.y = y + d[1];
        return p;
    }

    @Override
    public Point convertOffsetToCubeCoordinates(int ox, int oy, Point p) 
    {
        int cx = ox;
        int cz = oy - (ox + (ox&1)) / 2;
        int cy = -cx-cz;
        if (p == null)
        {
            p = new Point();
        }
        p.x = cx;
        p.y = cy;
        return p;

    }

    @Override
    public Point convertCubeToOffsetCoordinates(int cx, int cy, Point p) 
    {
        int cz = -cx-cy;
        int ox = cx;
        int oy = cz + (cx + (cx&1)) / 2;
        if (p == null)
        {
            p = new Point();
        }
        p.x = ox;
        p.y = oy;
        return p;
    }
    
    @Override
    public int computeStepsDistance(int x0, int y0, int x1, int y1)
    {
        int cx0 = x0;
        int cz0 = y0 - (x0 + (x0&1)) / 2;
        int cy0 = -cx0-cz0;
        int cx1 = x1;
        int cz1 = y1 - (x1 + (x1&1)) / 2;
        int cy1 = -cx1-cz1;
        int dx = Math.abs(cx0 - cx1); 
        int dy = Math.abs(cy0 - cy1); 
        int dz = Math.abs(cz0 - cz1); 
        return Math.max(dx, Math.max(dy, dz));
    }
    
    
    @Override
    public double computeSquaredCentersDistance(int x0, int y0, int x1, int y1)
    {
        Hexagon hexagon = getHexagon();
        double cx0 = x0*hexagon.getSpacingX();
        double cy0 = y0*hexagon.getSpacingY();
        boolean oddX0 = ((x0 & 1) == 1);
        if (!oddX0)
        {
            cy0 += 0.5 * hexagon.getSizeY();
        }
        double cx1 = x1*hexagon.getSpacingX();
        double cy1 = y1*hexagon.getSpacingY();
        boolean oddX1 = ((x1 & 1) == 1);
        if (!oddX1)
        {
            cy1 += 0.5 * hexagon.getSizeY();
        }
        double dx = cx1 - cx0;
        double dy = cy1 - cy0;
        return dx * dx + dy * dy;
    }
    
    
}

