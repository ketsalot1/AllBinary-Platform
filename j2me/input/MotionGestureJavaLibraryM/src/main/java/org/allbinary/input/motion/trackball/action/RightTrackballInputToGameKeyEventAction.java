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
package org.allbinary.input.motion.trackball.action;

import org.allbinary.game.input.PlatformInputMappingFactory;
import org.allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;
import org.allbinary.input.motion.gesture.TrackballMotionGestureFactory;

public class RightTrackballInputToGameKeyEventAction extends GameKeyCompleteMotionGestureInputEvent
{
    private static final GameKeyCompleteMotionGestureInputEvent SINGLETON = new RightTrackballInputToGameKeyEventAction();
    
    public static GameKeyCompleteMotionGestureInputEvent getInstance()
    {
        return SINGLETON;
    }

    private RightTrackballInputToGameKeyEventAction()
    {
        super("Right Action", TrackballMotionGestureFactory.getInstance().RIGHT,
                PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping());
    }    
}
