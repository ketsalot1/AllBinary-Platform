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
package org.allbinary.game.layer.special;

import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.layer.AllBinaryLayerManager;

public class Special1GameInputProcessor
extends SpecialGameInputProcessor
{
    public Special1GameInputProcessor(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer)
    {
        super(collidableDestroyableDamageableLayer);
    }
    
    public void process(AllBinaryLayerManager allbinaryLayerManager, GameKeyEvent gameKeyEvent) 
    throws Exception
    {
        this.collidableDestroyableDamageableLayer.special1(allbinaryLayerManager, gameKeyEvent);
    }    
}
