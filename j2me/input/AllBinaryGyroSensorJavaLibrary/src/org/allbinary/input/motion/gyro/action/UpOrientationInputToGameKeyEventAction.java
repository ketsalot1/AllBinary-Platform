/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 05/19/06
 *
 *
 * Modified By         When       ?
 *
 */
package org.allbinary.input.motion.gyro.action;

import org.allbinary.input.gyro.OrientationMotionGestureFactory;

import allbinary.game.input.PlatformInputMappingFactory;
import allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;

public class UpOrientationInputToGameKeyEventAction extends GameKeyCompleteMotionGestureInputEvent
{
    private static final GameKeyCompleteMotionGestureInputEvent instance = new UpOrientationInputToGameKeyEventAction();

    public static GameKeyCompleteMotionGestureInputEvent getInstance()
    {
        return instance;
    }

    private UpOrientationInputToGameKeyEventAction()
    {
        super("Up Action", OrientationMotionGestureFactory.getInstance().UP,
                PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping());
    }
}
