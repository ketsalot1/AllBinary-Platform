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
package org.allbinary.image.opengles;

import javax.microedition.lcdui.Image;

public class OpenGLESGL10ImageFactory extends OpenGLImageFactory
{
    /*
    public Image getInstance(GL10 gl, Image image, boolean matchColor)
    {
        return new OpenGLESGL10Image(gl, image, matchColor);
    }
    */

    public Image getInstance(Image image)
    {
        return new OpenGLESGL10Image(image);
    }
    
}
