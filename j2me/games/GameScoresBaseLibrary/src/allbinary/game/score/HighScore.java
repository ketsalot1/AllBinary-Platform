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
package allbinary.game.score;

//import com.sun.org.apache.bcel.internal.util.ByteSequence;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import allbinary.game.GameInfo;

public class HighScore
{
    private final int id;
    private String name;
    private final GameInfo gameInfo;
    private final long score;

    private final String scoreString;
    
    public HighScore(int id, String name, GameInfo gameInfo, long score)
    {
        this.id = id;
        this.setName(name);
        this.gameInfo = gameInfo;
        this.score = score;
        this.scoreString = Long.toString(this.getScore());
    }

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public long getScore()
    {
        return this.score;
    }

    public byte[] getBytes() throws Exception
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);
        outputStream.writeUTF(this.getName());
        outputStream.writeLong(this.getScore());
        return byteArrayOutputStream.toByteArray();
    }

    public GameInfo getGameInfo()
    {
        return gameInfo;
    }

    public String getScoreString()
    {
        return scoreString;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
