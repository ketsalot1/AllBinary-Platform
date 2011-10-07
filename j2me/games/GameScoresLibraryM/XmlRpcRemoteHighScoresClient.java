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
package allbinary.game.score.remote;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import abcs.init.crypt.jcehelper.CryptInterface;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.xmlrpc.XmlRpcAbeClient;
import abcs.logic.java.exception.ExceptionUtil;
import abcs.logic.system.security.crypt.jcehelper.BasicCrypt;
import abcs.logic.system.security.licensing.AbeClientInformationInterface;

public class XmlRpcRemoteHighScoresClient extends XmlRpcAbeClient
{
    private String page;
    
    public XmlRpcRemoteHighScoresClient(AbeClientInformationInterface clientInfo, String page, String remoteMethod)
    {
        super(clientInfo, remoteMethod);
        
        this.page = page;
        
        //Start with the first server
        this.setServer(0);
    }

    public Object get(Object object) throws Exception
    {
        try
        {            
            Vector param = new Vector();

            String serverUrl = getClientInfo().getLicenseServer(this.getServer());
            
            int index = serverUrl.lastIndexOf("/");

            serverUrl = serverUrl.substring(0, index + 1) + page;
            
            StringBuilder stringBuffer = new StringBuilder();
            stringBuffer.append(TRYING);
            stringBuffer.append(this.getServer());
            stringBuffer.append(SEP);
            stringBuffer.append(serverUrl);

            //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSING))
            // {
            //System.out.println(stringBuffer.toString());
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "get()"));
            // }

            //System.out.println("Renamed Server: " + serverUrl);

            this.setClient(new XmlRpcClient(serverUrl));
            this.getClient().setBasicAuthentication(null, null);

            Hashtable hashtable = (Hashtable) object;
            // if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSING))
            // {
            //System.out.println("Client Info: \n" + hashtable.toString());
            LogUtil.put(LogFactory.getInstance(CLIENT_INFO + hashtable.toString(), this, "get()"));
            // }

            /*
             * if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSING)) {
             * LogUtil.put(new Log("Xml-Rpc Client Liscense Request: \n" +
             * client.toString(), this,"get()")); }
             */

            param.add(hashtable);
            // KeySpecFactory.DES,
            Object result = getClient().execute(this.getRemoteMethod(), param,
                (CryptInterface) new BasicCrypt(PASS));

            /*
             * this could return without trying all servers if(result==null) {
             * //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSING))
             * //{ LogUtil.put(new Log("Empty Result\n", this,"get()")); //}
             * return null; }
             */

            // if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSING))
            // {
            //System.out.println("Result: \n" + result.toString());
            LogUtil.put(LogFactory.getInstance(RESULT + result.toString(), this, "get()"));
            // }

            // if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSING))
            // {
            //System.out.println("End Trying Server #" + this.getServer() + ": " + getClientInfo().getLicenseServer(this.getServer()));
            //LogUtil.put(new Log("End Trying Server #" + this.getServer() + ": " + getClientInfo().getLicenseServer(this.getServer()), this, "get()"));
            // }

            //Hashtable resultHashtable = (Hashtable) result;

            isOnline = true;
            return result;

        } catch (IOException e)
        {
            // if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSINGERROR))
            // {
            //String message = LogFormatUtil.get(error, "", "", e);
            //System.out.println(message);
            //LogUtil.put(LogFactory.getInstance(error, this, "get", e));
            // }

            // if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSING))
            // {
            //System.out.println("IOException Trying Other Servers");
            LogUtil.put(LogFactory.getInstance(TRYING_OTHER_SERVERS + ExceptionUtil.getStackTrace(e), this, "get()"));
            // }

            if(!e.getMessage().startsWith(HOST_NOT_RESOLVED))
            {
                return this.tryAnother(object);
            }
            else
            {
                throw new Exception(HOST_NOT_RESOLVED_MSG);
            }

        // return this.tryOtherServers();
        } catch (XmlRpcException e)
        {
            // if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSINGERROR))
            // {
            //Can't log this since that is what the log is using
            //String message = LogFormatUtil.get(error, "", "", e);
            //System.out.println(message);
            LogUtil.put(LogFactory.getInstance(SERVER_REPORTED_ERROR, this, "get", e));
            // }
            return this.tryAnother(object);
        } catch (Exception e)
        {
            // if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSINGERROR))
            // {
            //String message = LogFormatUtil.get(error, "", "", e);
            //System.out.println(message);
            LogUtil.put(LogFactory.getInstance(UNKNOWN_ERROR, this, "get", e));
            // }
            return this.tryAnother(object);
        }
    }
}
