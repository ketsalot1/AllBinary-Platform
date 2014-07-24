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
package org.allbinary.game.layer.pickup;

import allbinary.game.layer.pickup.PickupLayer;
import allbinary.game.tick.TickableInterface;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.view.ViewPosition;
import org.allbinary.game.multiplayer.layer.RemoteInfo;

public class FallingPickupLayer 
extends PickupLayer 
implements TickableInterface
{
    public FallingPickupLayer(RemoteInfo remoteInfo)
            throws Exception
    {
        super(remoteInfo, new ViewPosition());
    }

    public FallingPickupLayer() throws Exception
    {
        super(new ViewPosition());
    }

    /*
     * public FallingPickupLayer( int total,
     * PickedUpLayerInterfaceFactoryInterface
     * pickedUpLayerInterfaceFactoryInterface, int x, int y) throws Exception {
     * super(total, pickedUpLayerInterfaceFactoryInterface, x, y); }
     */

    /*
     * public AnimationPickupLayer(int total,
     * PickedUpLayerInterfaceFactoryInterface
     * pickedUpLayerInterfaceFactoryInterface, int x, int y) throws Exception {
     * super(total, pickedUpLayerInterfaceFactoryInterface,
     * pickedUpLayerInterfaceFactoryInterface.getAnimationInterface(), new
     * Rectangle(PointFactory.getInstance(x, y), 10, 10), new ViewPosition()); }
     */

    public void processTick(AllBinaryLayerManager allBinaryLayerManager)
    {
        this.setPosition(this.x, this.y + 1, this.z);
    }

    public boolean implmentsTickableInterface()
    {
        return true;
    }
}
