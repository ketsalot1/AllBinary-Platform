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
package org.allbinary.game.input;

import javax.microedition.lcdui.Canvas;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.system.security.licensing.LockedFeatureNotificationUtil;
import org.allbinary.logic.system.security.licensing.LockedUtil;
import org.allbinary.canvas.Processor;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.displayable.canvas.PreGameSelectorPaintable;
import org.allbinary.game.input.GameInputProcessorComposite;
import org.allbinary.game.input.GameInputProcessorInterface;
import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.game.input.PlayerGameInputCompositeInterface;
import org.allbinary.game.input.event.GameKeyEventHandler;
import org.allbinary.game.input.event.GameKeyEventUtil;
import org.allbinary.game.state.GameState;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.media.audio.SecondaryPlayerQueueFactory;
import org.allbinary.media.audio.SelectSound;
import org.allbinary.time.TimeDelayHelper;

public class PreGameSelectionGameInputProcessor extends Processor implements
        GameInputProcessorInterface, PlayerGameInputCompositeInterface
{
    private final AllBinaryGameCanvas gameCanvas;
    private final PreGameSelectorPaintable preGameSelectorPaintable;
    private final TimeDelayHelper inputTimeHelper = new TimeDelayHelper(650);

    private final GameState nextGameState;

    private final PlayerGameInput playerGameInput;

    private final int lockedIndex;
    
    public PreGameSelectionGameInputProcessor(
            AllBinaryGameCanvas gameCanvas,
            PreGameSelectorPaintable preGameSelectorPaintable, 
            GameState nextGameState,
            int lockedIndex)
    {
        this.gameCanvas = gameCanvas;
        this.preGameSelectorPaintable = preGameSelectorPaintable;
        this.nextGameState = nextGameState;

        this.playerGameInput = new GameInputProcessorComposite("Pre Game Selection", this);
        
        this.lockedIndex = lockedIndex;
    }

    public void process() throws Exception
    {
        this.getPlayerGameInput().update();
        // AllBinaryGameLayerManager gameLayerManager =
        // this.gameCanvas.getLayerManager();
        // this.processInput(gameLayerManager);
    }

    public void onInput(BasicArrayList list) throws Exception
    {
        int size = list.size();
        for (int index = 0; index < size; index++)
        {
            Object object = list.get(index);
            int key = GameKeyEventUtil.getKey(object);

            if (key == Canvas.LEFT || key == Canvas.RIGHT || key == Canvas.UP || key == Canvas.DOWN)
            {
                if (inputTimeHelper.isTime())
                {

                    SecondaryPlayerQueueFactory.getInstance().add(SelectSound.getInstance());

                    this.getPreGameSelectorPaintable().getPreGameSelectionForm().processInput(key);

                    break;

                }
            } else if (key == Canvas.KEY_NUM0)
            // if (key == GameKey.KEY_NUM0.getId().intValue())
            {
                int selectedIndex = this.preGameSelectorPaintable.getPreGameSelectionForm().getSelectedIndex();
                
                if(selectedIndex < this.lockedIndex || !LockedUtil.getInstance().isLockedFeature())
                {
                    //PreLogUtil.put("selectedIndex: " + selectedIndex + " LockedUtil.getInstance().isLockedFeature(): " + LockedUtil.getInstance().isLockedFeature(), this, "onInput");

                    SecondaryPlayerQueueFactory.getInstance().add(SelectSound.getInstance());

                    this.gameCanvas.setGameState(this.nextGameState);

                    GameKeyEventHandler.getInstance().removeListener(this.getPlayerGameInput());

                    break;
                }
                else
                {
                    LockedFeatureNotificationUtil.getInstance().fire();
                }
            }
        }

        list.clear();
    }

    /**
     * @return the preGameSelectorPaintable
     */
    public PreGameSelectorPaintable getPreGameSelectorPaintable()
    {
        return preGameSelectorPaintable;
    }

    public void processInput(AllBinaryLayerManager layerManager) throws Exception
    {
        
    }
    
    public void initInputProcessors()
    {
        
    }
    
    public String getName()
    {
        return this.toString();
    }

    public PlayerGameInput getPlayerGameInput()
    {
        return playerGameInput;
    }
}
