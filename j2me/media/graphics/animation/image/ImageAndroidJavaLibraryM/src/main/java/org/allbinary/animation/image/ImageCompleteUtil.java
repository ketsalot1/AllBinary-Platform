package org.allbinary.animation.image;

import javax.microedition.lcdui.Image;

/**
 *
 * @author user
 */
public class ImageCompleteUtil
{
    private static final ImageCompleteUtil instance = new ImageCompleteUtil();

    /**
     * @return the instance
     */
    public static ImageCompleteUtil getInstance()
    {
        return instance;
    }

    public void waitFor(Image image)
            throws Exception
    {
    }
}
