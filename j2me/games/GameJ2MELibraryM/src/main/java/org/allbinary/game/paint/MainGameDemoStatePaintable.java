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
package org.allbinary.game.paint;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.paint.Paintable;

public class MainGameDemoStatePaintable extends Paintable
{
    private final Paintable ownershipPaintableInterface;
    private final Paintable menuPaintableInterface;

    public MainGameDemoStatePaintable(Paintable ownershipPaintableInterface,
            Paintable menuPaintableInterface)
    {
        this.ownershipPaintableInterface = ownershipPaintableInterface;
        // new AllBinaryImageAnimation(new OwnershipScreen().getImage());

        this.menuPaintableInterface = menuPaintableInterface;
    }

    public void paint(Graphics graphics)
    {
        this.menuPaintableInterface.paint(graphics);

        this.ownershipPaintableInterface.paint(graphics);
    }

    /**
     * @return the ownershipPaintableInterface
     */
    public Paintable getOwnershipPaintableInterface()
    {
        return ownershipPaintableInterface;
    }

    /**
     * @return the menuPaintableInterface
     */
    public Paintable getMenuPaintableInterface()
    {
        return menuPaintableInterface;
    }

}
