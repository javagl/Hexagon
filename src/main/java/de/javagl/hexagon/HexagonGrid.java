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
 * Interface describing the geometric properties of a grid of {@link Hexagon}s.
 * <br>
 * <br>
 * Note that this does <b>not</b> represent an <i>instance</i> of 
 * a hexagon grid. It only offers information about the geometric properties 
 * of a (virtually infinitely large) hexagon grid.<br>
 * <br>
 * Instances of this class may be created with the {@link HexagonGrids} 
 * class.<br> 
 * <br>
 * Hexagon grids may differ in the orientation of their hexagons, which
 * may be {@link Hexagon vertical or horizontal}. Additionally, they may 
 * differ in the shifting of rows and columns. Namely, whether the odd 
 * rows/columns are shifted, or whether the even rows/columns
 * are shifted. 
 */
public interface HexagonGrid
{
    /**
     * Returns the {@link Hexagon} that the grid consists of
     * 
     * @return The {@link Hexagon}
     */
    Hexagon getHexagon();
    
    /**
     * Returns position of the center of a {@link Hexagon} with the given 
     * offset coordinates
     * 
     * @param x The x-coordinate of the hexagon
     * @param y The y-coordinate of the hexagon
     * @param p The point that will store the result. If this point is
     * <code>null</code>, then a new point will be created and returned
     * @return The position of the center of the hexagon
     */
    Point2D getCenter(int x, int y, Point2D p);
    
    /**
     * Returns the coordinates of the neighbor of the {@link Hexagon} with
     * the given offset coordinates in the given direction. The direction
     * is a value between 0 (inclusive) and 6 (exclusive), enumerating
     * the directions in counterclockwise order. For grids of  
     * {@link Hexagon vertical} hexagons, direction 0 will correspond to 
     * the lower right neighbor. For grids of {@link Hexagon horizontal}
     * hexagons, direction 0 will correspond to the right neighbor.
     * 
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param direction The direction
     * @param p The point that will store the result. If this is
     * <code>null</code>, then a new point will be created and
     * returned.
     * @return The coordinates of the neighbor
     * @throws IllegalArgumentException If the given direction is not in
     * the interval [0,6) 
     */
    Point getNeighbor(int x, int y, int direction, Point p);
 
    /**
     * Converts the point specified by the given x- and y offset
     * coordinates into a point in cube coordinates, and stores
     * it in the given point. The z-component of the resulting
     * cube coordinate will be <code>-p.x-p.y</code>. 
     * If the given point is <code>null</code>, then a new point will be 
     * created and returned.
     * 
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param p The point that will store the result
     * @return The result point
     */
    Point convertOffsetToCubeCoordinates(int x, int y, Point p);

    /**
     * Converts the point specified by the given x- and y cube
     * coordinates into a point in offset coordinates, and stores
     * it in the given point. 
     * If the given point is <code>null</code>, then a new point will be 
     * created and returned.
     * 
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param p The point that will store the result
     * @return The result point
     */
    Point convertCubeToOffsetCoordinates(int x, int y, Point p);
    
    /**
     * Computes the distance, in number of steps, between the cells which
     * are specified in offset coordinates
     * 
     * @param x0 The x-coordinate of the first cell
     * @param y0 The y-coordinate of the first cell
     * @param x1 The x-coordinate of the second cell
     * @param y1 The y-coordinate of the second cell
     * @return The distance, in number of steps, between the cells
     */
    int computeStepsDistance(
        int x0, int y0, int x1, int y1);
    
    /**
     * Computes the squared euclidean distance between the centers two cells
     * which are specified in offset coordinates
     * 
     * @param x0 The x-coordinate of the first cell
     * @param y0 The y-coordinate of the first cell
     * @param x1 The x-coordinate of the second cell
     * @param y1 The y-coordinate of the second cell
     * @return The squared distance
     */
    double computeSquaredCentersDistance(
        int x0, int y0, int x1, int y1);
    
}