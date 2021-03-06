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
package admin.taghelpers;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import javax.servlet.jsp.PageContext;


import org.allbinary.logic.system.security.licensing.LicensingException;


public class CategoryRequestHelperFactory implements TagHelperFactoryInterface
{
   private static final String CLASSNAME = "admin.taghelpers.CategoryRequestHelper";
   private static final String FACTORYNAME = "CategoryRequestHelperFactory";
   
   public CategoryRequestHelperFactory()
   {
   }
   
   public Object getInstance(
      HashMap hashMap, PageContext pageContext) 
      throws LicensingException
   {
      return HelperFactory.getInstance(FACTORYNAME, CLASSNAME, hashMap, pageContext);
   }  
}
