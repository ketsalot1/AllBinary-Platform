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
package allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringMaker;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.commands.GameCommandsFactory;
import allbinary.game.input.GameKey;
import allbinary.game.input.GameKeyFactory;
import allbinary.game.input.Input;
import allbinary.game.input.InputFactory;
import allbinary.game.input.PlatformInputMappingFactory;
import allbinary.game.input.mapping.InputMappingInterface;
import allbinary.game.input.mapping.InputToGameKeyMapping;
import allbinary.game.input.mapping.PersistentInputMapping;
import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.paint.ColorFillPaintable;
import allbinary.game.paint.ColorFillPaintableFactory;
import allbinary.game.paint.help.HelpPaintable;
import allbinary.game.paint.help.InputMappingHelpPaintable;
import allbinary.graphics.paint.ProcessPaintable;

public class GameInputMappingCanvas extends GameCommandCanvas
implements InputMappingInterface
{   
    private final ProcessPaintable paintable;

    public static final Command DISPLAY  = new Command("Input Mapping", Command.SCREEN, 2);
    public static final Command DEFAULT  = new Command("Default", Command.SCREEN, 2);
    
    private final InputMappingHelpPaintable helpPaintable;
    
    protected final ColorFillPaintable colorFillPaintable;
    
    private final PersistentInputMapping inputMapping;
    
    private final InputToGameKeyMapping inputToGameKeyMapping = 
        PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping();

    private final GameKey NONE = GameKeyFactory.getInstance().NONE;
    
    private GameKey selectedGameKey = NONE;
    private Input selectedInput = NONE;
    
    public GameInputMappingCanvas(CommandListener commandListener,
            AllBinaryGameLayerManager allBinaryGameLayerManager,
            HelpPaintable helpPaintable) throws Exception
    {
        super(commandListener,
                allBinaryGameLayerManager.getBackgroundBasicColor(),
                allBinaryGameLayerManager.getForegroundBasicColor());

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));

        if(helpPaintable == null)
        {
        	throw new Exception("Help Paintable Exception");
        }
        
        this.helpPaintable = (InputMappingHelpPaintable) helpPaintable;
        
        this.inputMapping = PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance();
        
  //      DemoGameMidletEventHandler.getInstance().fireEvent(
    ///            new DemoGameMidletEvent(this,
       //                 DemoGameMidletStateFactory
         //                       .getInstance().START_INPUT_MAPPING));
     
        this.paintable = new ProcessPaintable();
            //TouchButtonsPaintableCompositeFactory.getInstance(
              //      this, allBinaryGameLayerManager.getForegroundBasicColor());
        
        this.colorFillPaintable = 
            ColorFillPaintableFactory.getInstance(
                    allBinaryGameLayerManager.getBackgroundBasicColor());
    }

    public void close() throws Exception
    {
        super.close();
        
        this.paintable.process();
        
     //   DemoGameMidletEventHandler.getInstance().fireEvent(
       //         new DemoGameMidletEvent(this,
         //               DemoGameMidletStateFactory
           //                     .getInstance().START_DEMO));
        
        this.selectedGameKey = NONE;
        this.selectedInput = NONE;
        
        this.update();
    }
    
    public void initCommands(CommandListener cmdListener)
    {
        this.removeAllCommands();

        this.addCommand(GameCommandsFactory.getInstance().CLOSE_AND_SHOW_GAME_CANVAS);
        this.addCommand(GameInputMappingCanvas.DEFAULT);
        this.addCommand(GameInputMappingInstructionsCanvas.DISPLAY);

        this.setCommandListener(cmdListener);
    }

    public void keyPressed(int keyCode)
    {
        this.keyPressed(keyCode, 0);
    }
    
    public void keyReleased(int keyCode)
    {
        this.keyReleased(keyCode, 0);
    }

    public void keyRepeated(int keyCode)
    {
        this.keyRepeated(keyCode, 0);
    }
    
    public void keyPressed(int keyCode, int deviceId)
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "keyPressed"));        
        this.addGameKeyEvent(keyCode, false);

        super.keyPressed(keyCode);
    }

    private final InputFactory inputFactory = InputFactory.getInstance();
    
    private void addGameKeyEvent(int keyCode, boolean repeated)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Raw Device Key Code: " + Integer.toHexString(keyCode), this, "addGameKeyEvent"));

            GameKey gameKey = this.inputToGameKeyMapping.getInstance(this, keyCode);

            //TWB - Hack for MicroEmulator negative values on directional controls
            Input input = inputFactory.getInstance(keyCode);

            this.process(gameKey, input);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Key Event Error", this, "addGameKeyEvent", e));
        }
    }
    
    public void process(GameKey gameKey, Input input) throws Exception
    {
        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("Start Passed GameKey: ");
        stringBuffer.append(gameKey);
        stringBuffer.append(" Input: ");
        stringBuffer.append(input);
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, CommonStrings.getInstance().PROCESS));
        
        if (this.selectedGameKey != NONE)
        {
            this.gameActionCrud(gameKey, input);
        }
        else
        {
            this.setSelectedAction(gameKey);
        }
    }

    private void setSelectedAction(GameKey gameKey)
    {
        LogUtil.put(LogFactory.getInstance("Selected GameKey: " + gameKey, this, "setSelectedAction"));
        
        this.selectedGameKey = gameKey;
        this.selectedInput = NONE;
        this.helpPaintable.update(this.selectedGameKey, this.selectedInput);
        this.repaint();
    }

    private void gameActionCrud(GameKey gameKey, Input input) throws Exception
    {
        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("Start GameKey: ");
        stringBuffer.append(this.selectedGameKey);
        stringBuffer.append(" Input: ");
        stringBuffer.append(this.selectedInput);
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "gameActionCrud"));

        // If action is selected and input is not then select the input
        // if it is mapped for the selected action
        if (this.selectedInput == NONE)
        {
            BasicArrayList list = inputMapping.getInputMapping().getMappedInput(this.selectedGameKey);
            boolean isInputAlreadyMappedToSelectedAction = list.contains(input);
            
            if (isInputAlreadyMappedToSelectedAction)
            {
                LogUtil.put(LogFactory.getInstance("Already Mapped Input: " + input, this, "gameActionCrud"));

                this.selectedInput = input;
                this.helpPaintable.update(this.selectedGameKey, this.selectedInput);
                this.repaint();
            }
            else
            // Add new mapping
            {
                this.addNewMapping(gameKey, input);
            }
        }
        else
        // If deleting a current mapping
        if (this.inputMapping.getInputMapping().isDelete(input))
        {
            this.deleteCurrentMapping();
        }
        else
        {
            this.setSelectedAction(gameKey);
        }
    }

    private void addNewMapping(GameKey gameKey, Input input) throws Exception
    {
        final String METHOD_NAME = "addNewMapping";
            
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + "Dissallow if " + input + " is in { " + AndroidKeyFactory.getInstance().MENU + " }", this, "addNewMapping"));
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, METHOD_NAME));

        boolean isInputAlreadyMapped = inputMapping.getInputMapping().isMapped(input);

        if (!isInputAlreadyMapped && !this.inputMapping.getInputMapping().isSystemInput(input))
        {
            StringMaker stringBuffer = new StringMaker();

            stringBuffer.append("Add Key Mapping : GameKey: ");
            stringBuffer.append(this.selectedGameKey);
            stringBuffer.append(" Input: ");
            stringBuffer.append(this.selectedInput);
            
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_NAME));
            
            inputMapping.getInputMapping().add(this.selectedGameKey, input);
            this.selectedInput = input;
            this.update();
        }
        else
        {
            LogUtil.put(LogFactory.getInstance(
                    "Unable to add Mapping since one already exists or is MENU, HOME, or BACK key and setting selected action to what it is already mapped to", this,
                    METHOD_NAME));
            this.setSelectedAction(gameKey);
        }
    }

    private void deleteCurrentMapping() throws Exception
    {
        final String METHOD_NAME = "deleteCurrentMapping";

        BasicArrayList list = inputMapping.getInputMapping().getMappedInput(this.selectedGameKey);

        if (list.size() > 1)
        {
            StringMaker stringBuffer = new StringMaker();

            stringBuffer.append("Start GameKey: ");
            stringBuffer.append("Remove Key Mapping: GameKey: ");
            stringBuffer.append(this.selectedGameKey);
            stringBuffer.append(" Input: ");
            stringBuffer.append(this.selectedInput);
            
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, METHOD_NAME));

            inputMapping.getInputMapping().remove(this.selectedGameKey, this.selectedInput);
            this.selectedInput = NONE;
            this.update();
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("Can't Remove Last Key Mapping", this, METHOD_NAME));
        }
    }
    
    public void setDefault() throws Exception
    {
        inputMapping.setDefault();
                //(InputToGameKeyMapping) PlatformInputMappingFactory.getInstance());
        this.helpPaintable.update(NONE, NONE);
        this.repaint();
    }
    
    public void update() throws Exception
    {
        inputMapping.update();
                //(InputToGameKeyMapping) PlatformInputMappingFactory.getInstance());
        this.helpPaintable.update(this.selectedGameKey, this.selectedInput);
        this.repaint();
    }

    public void paint(Graphics graphics)
    {
        this.colorFillPaintable.paint(graphics);
        
        this.helpPaintable.paint(graphics);
        
        this.paintable.paint(graphics);

        super.paint(graphics);
    }
}
