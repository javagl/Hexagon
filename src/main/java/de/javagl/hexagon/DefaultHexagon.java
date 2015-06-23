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
 * Implementation of a {@link Hexagon}
 */
class DefaultHexagon implements Hexagon
{
    /**
     * The radius 
     */
    private final double radius;
    
    /**
     * The size in x-direction
     */
    private final double sizeX;

    /**
     * The size in y-direction
     */
    private final double sizeY;
    
    /**
     * The spacing in x-direction
     */
    private final double spacingX;

    /**
     * The spacing in y-direction
     */
    private final double spacingY;
    
    /**
     * The offset of the angle step. This is 0.0 for vertical hexagons,
     * and 0.5 for horizontal hexagons.
     */
    private final double angleStepOffset;
    
    /**
     * Creates the hexagon with the given radius 
     * 
     * @param radius The radius
     * @param horizontal Whether this hexagon should be horizontal
     */
    DefaultHexagon(double radius, boolean horizontal)
    {
        this.radius = radius;
        if (horizontal)
        {
            this.sizeY = radius * 2;
            this.sizeX = Math.sqrt(3.0)/2.0 * sizeY;
            this.spacingX = sizeX;
            this.spacingY = 3.0/4.0 * sizeY;        
            this.angleStepOffset = 0.5;
        }
        else
        {
            this.sizeX = radius * 2;
            this.sizeY = Math.sqrt(3.0)/2.0 * sizeX;
            this.spacingX = 3.0/4.0 * sizeX;
            this.spacingY = sizeY;
            this.angleStepOffset = 0.0;
        }
    }
    
    @Override
    public double getRadius() 
    {
        return radius;
    }
    
    @Override
    public double getSizeX()
    {
        return sizeX;
    }

    @Override
    public double getSizeY()
    {
        return sizeY;
    }

    @Override
    public double getSpacingX()
    {
        return spacingX;
    }

    @Override
    public double getSpacingY()
    {
        return spacingY;
    }

    @Override
    public double getCornerX(int index)
    {
        double angleRad = Hexagons.ANGLE_STEP_RAD * (index + angleStepOffset);
        double x = Math.cos(angleRad);
        return x * radius;
    }

    @Override
    public double getCornerY(int index)
    {
        double angleRad = Hexagons.ANGLE_STEP_RAD * (index + angleStepOffset);
        double y = Math.sin(angleRad);
        return y * radius;
    }
}