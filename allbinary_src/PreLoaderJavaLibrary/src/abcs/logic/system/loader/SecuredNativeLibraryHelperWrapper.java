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
package abcs.logic.system.loader;

import abcs.logic.system.security.AbKeys;
import abcs.logic.system.security.licensing.LicensingException;

public class SecuredNativeLibraryHelperWrapper extends NativeLibraryHelperWrapper
{
    private SecuredNativeLibraryInterface securedNativeLibraryInterface;
    
    public SecuredNativeLibraryHelperWrapper(
        SecuredNativeLibraryInterface securedNativeLibraryInterface)
    {
        super(securedNativeLibraryInterface.getLibraryName());
        this.securedNativeLibraryInterface = securedNativeLibraryInterface;
    }
    
    public boolean unlock()
    throws LicensingException
    {
        return securedNativeLibraryInterface.unlock(
            AbKeys.getKey(securedNativeLibraryInterface.getName()));
    }
    
}
