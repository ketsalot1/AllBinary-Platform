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
package org.allbinary.game.input.motion.action;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.CompleteMotionGestureInputEvent;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.input.motion.gesture.MotionGestureInput;

public class GameKeyCompleteMotionGestureInputEvent extends
        CompleteMotionGestureInputEvent implements GameKeyEventSourceInterface
{
    private GameKey gameKey;
    private GameKeyEvent gameKeyEvent;

    private InputToGameKeyMapping inputToGameKeyMapping;

    public GameKeyCompleteMotionGestureInputEvent(String name,
            MotionGestureInput motionGestureInput, InputToGameKeyMapping inputToGameKeyMapping)
    {
        super(name, motionGestureInput);

        this.inputToGameKeyMapping = inputToGameKeyMapping;

        GameKeyCompleteMotionGestureInputEventFactory.getInstance().add(this);
        
        this.update();
        //LogUtil.put(LogFactory.getInstance(name + " mapped to gameKey: " + this.getGameKey(), this, CommonStrings.getInstance().CONSTRUCTOR));
    }

    public int getSourceId()
    {
        return 2;
    }

    public void update()
    {
        try
        {
            this.setGameKey(inputToGameKeyMapping.getInstance(
                    this.getMotionGestureInput().getId()));

            this.setGameKeyEvent(GameKeyEventFactory.getInstance().getInstance(this, getGameKey()));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().UPDATE, e));
        }
    }

    protected void setGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        this.gameKeyEvent = gameKeyEvent;
    }

    public GameKeyEvent getGameKeyEvent()
    {
        return gameKeyEvent;
    }

    protected void setGameKey(GameKey gameKey)
    {
        this.gameKey = gameKey;
    }

    public GameKey getGameKey()
    {
        return gameKey;
    }
}
