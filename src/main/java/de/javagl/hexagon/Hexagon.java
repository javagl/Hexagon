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

/**
 * Interface describing the geometric properties of a Hexagon.
 * Note that this does <b>not</b> represent an <i>instance</i> 
 * of a hexagon. It only describes the geometric properties
 * of a hexagon with a certain size and orientation, centered 
 * at the origin.<br>
 * <br>
 * The orientation of a hexagon may either be vertical or horizontal:<br>
 * <br>
 * <b>Vertical:</b>
 * <pre><code>
 *  __
 * /  \
 * \__/
 * </code></pre>
 * <br>
 * <b>Horizontal:</b>
 * <pre><code>
 *  /\
 * |  |
 *  \/
 * </code></pre>  
 */
public interface Hexagon
{
    /**
     * Returns the radius of the hexagon (the distance of
     * the corners from the center)
     * 
     * @return The radius of the hexagon
     */
    double getRadius();
    
    /**
     * Returns the size of the hexagon, in x-direction
     * 
     * @return The size of the hexagon, in x-direction
     */
    double getSizeX();

    /**
     * Returns the size of the hexagon, in y-direction
     * 
     * @return The size of the hexagon, in y-direction
     */
    double getSizeY();

    /**
     * Returns the horizontal distance between the center of the hexagon
     * and the center of a horizontally adjacent hexagon
     * 
     * @return The spacing, in x-direction
     */
    double getSpacingX();

    /**
     * Returns the horizontal distance between the center of the hexagon
     * and the center of a vertically adjacent hexagon
     * 
     * @return The spacing, in y-direction
     */
    double getSpacingY();
    
    /**
     * Returns the x-coordinate of the corner with the given index
     * 
     * @param index The index
     * @return The x-coordinate of the corner
     */
    double getCornerX(int index);
    
    /**
     * Returns the y-coordinate of the corner with the given index
     * 
     * @param index The index
     * @return The y-coordinate of the corner
     */
    double getCornerY(int index);
}