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
package workflows.template.data;

import abcs.logic.communication.log.LogFactory;
import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import javax.servlet.jsp.tagext.TagSupport;

import allbinary.logic.visual.transform.TransformFactory;

import allbinary.logic.control.validate.ValidationComponentInterface;

import allbinary.logic.control.workflow.WorkFlowInterface;
import allbinary.logic.control.workflow.WorkFlowData;

import abcs.logic.communication.log.LogUtil;

public class BasicWorkFlow implements WorkFlowInterface
{
   private ValidationComponentInterface validationDomNodeInterface;
   private HashMap propertiesHashMap;
   private PageContext pageContext;
   
   public BasicWorkFlow(HashMap propertiesHashMap, PageContext pageContext) throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.WORKFLOW))
      {
         LogUtil.put(LogFactory.getInstance("Properties: " + propertiesHashMap.toString(), this, "BasicWorkFlow()"));
      }
      
      this.propertiesHashMap = propertiesHashMap;
      this.pageContext = pageContext;
      
      this.validationDomNodeInterface = (ValidationComponentInterface)
         TransformFactory.getInstance(propertiesHashMap, pageContext);
   }

   public String getName() throws Exception
   {
      return (String) this.getPropertiesHashMap().get(WorkFlowData.getInstance().NAME);
   }

   protected HashMap getPropertiesHashMap()
   {
      return this.propertiesHashMap;
   }     

   protected PageContext getPageContext()
   {
      return this.pageContext;
   }     
   
   public Integer process() throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.WORKFLOW))
      {
         LogUtil.put(LogFactory.getInstance("Start",this,"process()"));
      }
      
      if(this.validationDomNodeInterface.isValid().booleanValue())
      {
         pageContext.getOut().print(this.validationDomNodeInterface.view());
         return Integer.valueOf(TagSupport.EVAL_BODY_INCLUDE);
      }
      else
      {
         //TWB - convert valid text to dom node and remove validationInfo
         //super.doStartTag();
         pageContext.getOut().print(this.validationDomNodeInterface.validationInfo());
         return Integer.valueOf(TagSupport.SKIP_BODY);
      }
   }
}