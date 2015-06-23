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
 * Default implementation of a {@link HexagonGrid}
 */
class HorizontalEvenShiftedHexagonGrid implements HexagonGrid
{
    /**
     * The neighbor offsets
     */
    private static final int NEIGHBORS[][][] = new int[][][]{
        { {+1,  0}, {+1, -1}, { 0, -1},
          {-1,  0}, { 0, +1}, {+1, +1} },
        { {+1,  0}, { 0, -1}, {-1, -1},
          {-1,  0}, {-1, +1}, { 0, +1} }
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
    HorizontalEvenShiftedHexagonGrid(double radius)
    {
        this.hexagon = Hexagons.createHorizontal(radius);
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

        boolean oddY = ((y & 1) == 1);
        if (!oddY)
        {
            cx += 0.5 * hexagon.getSizeX();
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
        int parity = y & 1;
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
        int cx = ox - (oy + (oy&1)) / 2;
        int cz = oy;
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
        int cz =-cx-cy;
        int ox = cx + (cz + (cz&1)) / 2;
        int oy = cz;
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
        int cx0 = x0 - (y0 + (y0&1)) / 2;
        int cz0 = y0;
        int cy0 = -cx0-cz0;
        int cx1 = x1 - (y1 + (y1&1)) / 2;
        int cz1 = y1;
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
        boolean oddY0 = ((y0 & 1) == 1);
        if (!oddY0)
        {
            cx0 += 0.5 * hexagon.getSizeX();
        }
        double cx1 = x1*hexagon.getSpacingX();
        double cy1 = y1*hexagon.getSpacingY();
        boolean oddY1 = ((y1 & 1) == 1);
        if (!oddY1)
        {
            cx1 += 0.5 * hexagon.getSizeX();
        }
        double dx = cx1 - cx0;
        double dy = cy1 - cy0;
        return dx * dx + dy * dy;
    }
}
