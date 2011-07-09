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
package allbinary.game.input.mapping;

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.NotImplemented;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.Input;

public class InputMapping
{    
   //TWB - Use MapList someday
    private final Hashtable hashtable = new Hashtable();
    private final BasicArrayList mappedList = new BasicArrayList();

    protected InputMapping()
    {
    }

    public int getTotalMapped()
    {
        return this.getHashtable().size();
    }
    
    protected boolean isDefaultNew()
    {
        return false;
    }
    
    protected InputToGameKeyMapping getDefault()
    {
        return null;
    }
    
    protected void removeAll()
    {
        this.getHashtable().clear();
        this.mappedList.clear();
    }

    public void remove(Input input, Input mappedToInput)
    {
        StringBuilder stringBuffer = new StringBuilder();
        
        stringBuffer.append(CommonStrings.getInstance().START_LABEL);
        stringBuffer.append(input);
        stringBuffer.append(" == ");
        stringBuffer.append(mappedToInput);
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "InputMapping::remove"));
        
        BasicArrayList list = this.getMappedInput(input);
        if(list.contains(mappedToInput))
        {
            list.remove(mappedToInput);
            this.mappedList.remove(mappedToInput);
            
            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append("Removed: ");
            stringBuffer.append(input);
            stringBuffer.append(" == ");
            stringBuffer.append(mappedToInput);
            
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "InputMapping::remove"));
        }
        else
        {
            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append("Not Deleted: ");
            stringBuffer.append(input);
            stringBuffer.append(" == ");
            stringBuffer.append(mappedToInput);
            
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "InputMapping::remove"));
        }
    }
    
    public void add(InputToGameKeyMapping inputToGameKeyMapping)
    throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }
    
    public void add(Input input, Input mappedToInput)
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + input + " == " + mappedToInput, this, "InputMapping::add"));
        BasicArrayList list = this.getMappedInput(input);
        if(!list.contains(mappedToInput))
        {
            list.add(mappedToInput);
            this.mappedList.add(mappedToInput);
        }
    }

    public boolean isMapped(Input input)
    {
        return this.mappedList.contains(input);
    }
    
    public BasicArrayList getMappedInput(Input id)
    {
        BasicArrayList list = (BasicArrayList) this.getHashtable().get(id);

        if (list == null)
        {
            list = new BasicArrayList();
            this.getHashtable().put(id, list);
        }

        return list;
    }

    //TWB - Hack Method for Plaform Independence - Maybe a better place for this
    public boolean isDelete(Input input) throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    public boolean isSystemInput(Input input) throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    public Hashtable getHashtable()
    {
        return hashtable;
    }
}
