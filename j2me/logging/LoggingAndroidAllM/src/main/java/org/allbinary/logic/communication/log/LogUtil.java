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
package org.allbinary.logic.communication.log;

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogFormatUtil;

public class LogUtil
{
   private LogUtil()
   {
   }

   private static final String EMPTY = "Empty";
   private static final String LABEL = "allbinary";
   private static final String LOGGING_LABEL = "Logging Successful: ";

   public synchronized static void put(Log log)
   {
      String specialMessage = log.getSpecialMessage();
      Object object = log.getObject();
      String functionName = log.getFunctionName();
      Throwable exception = log.getThrowable();

      String className = EMPTY;
      /*
      if(object != null && object.getClass().getName() != null)
      {
         className = new String(object.getClass().getName());
      }
      */
      
      className = object.getClass().getName();
      
      String message = LogFormatUtil.getInstance().get(
         className, functionName, specialMessage, exception);
      
      android.util.Log.i(LABEL, LOGGING_LABEL + message);
   }
}
