/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.animation.compound;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.lcdui.Graphics;

import allbinary.animation.RotationAnimation;
import allbinary.direction.Direction;
import allbinary.logic.math.PrimitiveIntUtil;
import allbinary.math.Angle;
import allbinary.math.AngleInfo;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class SimultaneousCompoundRotationAnimation 
extends RotationAnimation
{
    private RotationAnimation[] animationInterfaceArray;

    public SimultaneousCompoundRotationAnimation(RotationAnimation[] animationInterfaceArray)
    {
        this.animationInterfaceArray = animationInterfaceArray;
    }
    
    public void setFrame(int frameIndex)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].setFrame(frameIndex);
        }
    }

    public int getFrame()
    {
        return this.animationInterfaceArray[0].getFrame();
    }

    public int getAnimationSize() throws Exception
    {
        return this.animationInterfaceArray[0].getAnimationSize();
    }    
    
    public int getSize()
    {
        return this.animationInterfaceArray[0].getSize();
    }

    public void previousFrame()
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].previousFrame();
        }
    }

    public void nextFrame()
    throws Exception
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].nextFrame();
        }
    }

    public void previousRotation()
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].previousRotation();
        }
    }

    public void nextRotation()
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].nextRotation();
        }
    }

    public void setSequence(int[] sequence)
    {
    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    public void paint(Graphics graphics, int x, int y)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {        
            this.animationInterfaceArray[index].paint(graphics, x, y);
        }
    }

    public void paintThreed(Graphics graphics, int x, int y, int z)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].paintThreed(graphics, x, y, z);
        }
    }

    public void setFrame(Direction direction)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].setFrame(direction);
        }
    }

    public void setFrame(Angle angle)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].setFrame(angle);
        }
    }

    public void adjustFrame(Angle newAngle)
    {
        this.adjustFrame(newAngle.getValue());
    }

    public void adjustFrame(short angle)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].adjustFrame(angle);
        }
    }

    public AngleInfo getAngleInfo()
    {
        return this.animationInterfaceArray[0].getAngleInfo();
    }
    
    /**
     * @return the animationInterfaceArray
     */
    public RotationAnimation[] getAnimationInterfaceArray()
    {
        return animationInterfaceArray;
    }

    /**
     * @param animationInterfaceArray the animationInterfaceArray to set
     */
    public void setAnimationInterfaceArray(RotationAnimation[] animationInterfaceArray)
    {
        this.animationInterfaceArray = animationInterfaceArray;
    }
    
    public void set(GL gl) throws Exception
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].set(gl);
        }
    }
}
