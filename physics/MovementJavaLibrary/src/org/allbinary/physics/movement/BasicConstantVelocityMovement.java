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
package org.allbinary.physics.movement;

import allbinary.game.layer.AllBinaryGameLayer;
import allbinary.game.physics.velocity.BasicVelocityProperties;
import allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import allbinary.layer.AllBinaryLayer;
import allbinary.logic.math.BasicDecimal;
import allbinary.logic.math.vector.AxisMathVectorUtil;
import allbinary.math.AngleFactory;

/**
 * 
 * @author user
 */
public class BasicConstantVelocityMovement 
extends Movement 
implements VelocityInterfaceCompositeInterface
{
    private BasicVelocityProperties velocityProperties;

    private BasicDecimal speedBasicDecimal;

    private final AxisMathVectorUtil axisMathVectorUtil = AxisMathVectorUtil.getInstance();
    
    public BasicConstantVelocityMovement(BasicDecimal basicDecimal, BasicVelocityProperties velocityProperties)
    {
        this.setSpeedBasicDecimal(basicDecimal);
        this.velocityProperties = velocityProperties;
    }
    
    public BasicConstantVelocityMovement()
    {
        this.setSpeedBasicDecimal(ZERO_BIGDECIMAL);
        this.velocityProperties = new BasicVelocityProperties();
    }

    public void init(BasicDecimal speedBasicDecimal, short angle, short otherAngle)
    {
        this.speedBasicDecimal = speedBasicDecimal;
        
        AngleFactory angleFactory = AngleFactory.getInstance();
        
        this.velocityProperties.setVelocity(speedBasicDecimal, 
                angleFactory.getInstance(angle), angleFactory.getInstance(otherAngle));
    }

    public void moveOutsideRadius(AllBinaryLayer layer, int radius, short angle, short otherAngle)
    {
        int scaleFactorValue = this.speedBasicDecimal.getScaledFactorValue();
        
        int xVector = (int) (axisMathVectorUtil.calculateX(radius, angle) / scaleFactorValue);
        int yVector = (int) (axisMathVectorUtil.calculateY(radius, angle) / scaleFactorValue);
        int zVector = (int) (axisMathVectorUtil.calculateZ(radius, otherAngle) / scaleFactorValue);

        layer.move(xVector, yVector, zVector);
    }

    public void process(AllBinaryGameLayer layer) throws Exception
    {
        layer.move(
                this.velocityProperties.getVelocityXBasicDecimal().getScaled(),
                this.velocityProperties.getVelocityYBasicDecimal().getScaled(),
                this.velocityProperties.getVelocityZBasicDecimal().getScaled()
                );
    }

    public String toString()
    {
    	return this.velocityProperties.toString();
    }

    public void stop()
    {
        this.velocityProperties.zero();
    }

    public BasicVelocityProperties getVelocityProperties()
    {
        return velocityProperties;
    }

    public void setVelocityProperties(BasicVelocityProperties velocityProperties)
    {
        this.velocityProperties = velocityProperties;
    }

    protected void setSpeedBasicDecimal(BasicDecimal speedBasicDecimal)
    {
        this.speedBasicDecimal = speedBasicDecimal;
    }

    protected BasicDecimal getSpeedBasicDecimal()
    {
        return speedBasicDecimal;
    }

}
