/**
 *
 * @author David Craig w1354193 | Bsc Computer Systems Engineering
 */

/**
     * This is the file used to Serialize the objects
     */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class SerializedClass implements Serializable
{
   public String chatMessage="";
   public String clientname="";

    
    void setMessage(String chatMString){
        this.chatMessage = chatMString;
         
    }
    
    String getMessage(){
        return chatMessage;
    }

    void setClientName(String chatMString){
        this.clientname = chatMString;
    }
    
    String getClientName(){
        return clientname;
    }
    
}