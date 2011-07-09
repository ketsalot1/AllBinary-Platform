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
package allbinary.game.combat.destroy;

import abcs.logic.basic.NotImplemented;

public class DestroyableBaseBehavior implements DestroyableInterface
{
    private static final DestroyableBaseBehavior instance = new DestroyableBaseBehavior();
    
    public static DestroyableBaseBehavior getInstance()
    {
        return instance;
    }

    public String getName()
    {
        return this.getClass().getName();
    }
    
    public boolean isDestroyed() throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }
    
    public void setDestroyed(boolean destroyed)
    {
    }    
}
