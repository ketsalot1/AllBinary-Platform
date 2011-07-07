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
package allbinary.data.tree.dom;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import abcs.globals.URLGLOBALS;
import abcs.logic.basic.path.AbFilePath;
import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.globals.FREEBLISKET_PATH_GLOBALS;

import allbinary.logic.control.crypt.file.CryptFileReader;
import allbinary.logic.visual.transform.info.template.TransformInfoTemplateData;

public class ContextUriResolver implements URIResolver
{
   private BasicUriResolver basicURIResolver;
   
   public ContextUriResolver(BasicUriResolver basicURIResolver)
   {
      this.basicURIResolver = basicURIResolver;
   }
   
   public Source resolve(String href, String base) throws TransformerException
   {
      try
      {
    	  StringBuffer stringBuffer = new StringBuffer();
    	  
    	  stringBuffer.append(URLGLOBALS.getMainPath());
    	  stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
    	  stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().INSTALLPATH);
    	  stringBuffer.append(AbPathData.getInstance().SEPARATOR);
    	  stringBuffer.append(href);

         AbPath fileAbPath = new AbFilePath(stringBuffer.toString());
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.XMLLOGGING))
         {
        	 stringBuffer.delete(0, stringBuffer.length());
        	 
        	 stringBuffer.append("attempt to use xsl:import: href=");
        	 stringBuffer.append(href);
        	 stringBuffer.append("\nBase= ");
        	 stringBuffer.append(base);
        	 stringBuffer.append("\nNew path= ");
        	 stringBuffer.append(fileAbPath.toString());
        	 stringBuffer.append("\nNote: ");
        	 stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
        	 stringBuffer.append(" is a urlglobal");
        	 stringBuffer.append("\nRequired Extension: ");
        	 stringBuffer.append(this.basicURIResolver.getExtension());
             
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "resolve"));
         }
         
         return new StreamSource(new CryptFileReader(
            TransformInfoTemplateData.getInstance().UNCRYPTED_EXTENSION,
            TransformInfoTemplateData.getInstance().ENCRYPTED_EXTENSION
            ).getInputStream(fileAbPath));
      }
      catch(TransformerException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         throw new TransformerException(e);
      }
   }
      
   public String toString()
   {
      try
      {
         return URLGLOBALS.getMainPath() + FREEBLISKET_PATH_GLOBALS.getInstance().INSTALLPATH + "/{import url}";
      }
      catch(Exception e)
      {
         //Log Error
         return "ContextUriResolver - Does not work without webapp path should be changed";
      }
   }
}
