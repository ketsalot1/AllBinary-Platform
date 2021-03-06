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
package org.allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Graphics;

import org.allbinary.J2MEUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.animation.vector.RectangleFilledAnimation;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.draw.DrawStringUtil;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.Paintable;

/**
 *
 * @author user
 */
public class BasicPopupMenuPaintable extends Paintable
{
    private final String label;

    private static final String NAME = "MENU";

    protected final BasicColorSetUtil basicColorUtil = 
        BasicColorSetUtil.getInstance();

    private final BasicColor foregroundBasicColor;
    
    private Rectangle rectangle;
    private int offset;

    private Animation animationInterface;

    public BasicPopupMenuPaintable(Rectangle rectangle, 
            BasicColor backgroundBasicColor, 
            BasicColor foregroundBasicColor)
            throws Exception
    {
        this.foregroundBasicColor = foregroundBasicColor;

        /*
        if(AppletUtil.isAppletLoader(this))
        {
            label = NAME + " Esc";
        }
        else
        {
            label = NAME + " *";
        }
 */
        label = NAME;

        this.rectangle = rectangle;
        
        if(J2MEUtil.isJ2ME())
        {
            //Image image = ImageCacheFactory.getInstance().get(
              //      this, this.rectangle.getWidth(), this.rectangle.getHeight());
            //image.getGraphics().setColor(BasicColor.TRANSPARENT_GREY.intValue());
            //image.getGraphics().drawRect(0, 0, this.rectangle.getWidth(), this.rectangle.getHeight());
            //this.animationInterface = new AllBinaryImageAnimation(image);
            this.animationInterface = NullAnimationFactory.getFactoryInstance().getInstance();
        }
        else
        {
            this.animationInterface = new RectangleFilledAnimation(
                this.rectangle.getWidth(), this.rectangle.getHeight(), 
                BasicColorFactory.getInstance().TRANSPARENT_GREY);
        }
        
        this.init(rectangle);
    }

    public void init(Rectangle rectangle) throws Exception
    {
        this.rectangle = rectangle;
        
        int heightOffset = rectangle.getHeight() - (MyFont.getInstance().DEFAULT_CHAR_HEIGHT * NAME.length());
        
        this.offset = (heightOffset >> 1);

       int width = this.rectangle.getWidth();
       int height = this.rectangle.getHeight();

        if(J2MEUtil.isJ2ME())
        {
            //Image image = ImageCacheFactory.getInstance().get(this, width, height);
            //image.getGraphics().setColor(BasicColor.TRANSPARENT_GREY.intValue());
            //image.getGraphics().drawRect(0, 0, width, height);
            //this.animationInterface = new AllBinaryImageAnimation(image);
        }
        else
        {
           RectangleFilledAnimation rectangleFilledAnimation =
                   (RectangleFilledAnimation) this.animationInterface;
           rectangleFilledAnimation.setWidth(width);
           rectangleFilledAnimation.setHeight(height);
        }
    }
    
   private final DrawStringUtil drawStringUtil = DrawStringUtil.getInstance();
    
   public void paint(Graphics graphics)
   {
       GPoint point = this.rectangle.getPoint();
       int x = point.getX();
       int y = point.getY();

       int width = this.rectangle.getWidth();
       int height = this.rectangle.getHeight();

       this.animationInterface.paint(graphics, x, y);

       this.basicColorUtil.setBasicColor(graphics, this.foregroundBasicColor);
       
       drawStringUtil.paintVerticle(graphics, label, x + 4, y + offset, 0);
       graphics.drawRect(x, y, width, height);
   }
   
}
