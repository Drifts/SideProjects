/**
 *
 * @author David Craig w1354193 | Bsc Computer Systems Engineering
 */
 
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.net.*;
import java.io.*;

/**
     * A user defined constructor is required for RMI
     * because creating a remote object may cause an exception.
     * @exception java.rmi.RemoteException
     */

/**
     * This is the implementation of the interface
     */

public class MessagingImpl extends UnicastRemoteObject implements Messaging
{
    private MessagingServer cs;
 
    public MessagingImpl(MessagingServer cs) throws RemoteException
    {
        super();
        this.cs = cs;
        
    }
 
    public void sendPublicMessage(String keyword, String username, String message) throws RemoteException
    {
        cs.sendPublicMessage(keyword, username, message);
    }
 
    public ArrayList getClientList() throws RemoteException
    {
        return cs.getClientList();
    }
 
    public void connect(SerializedClass obj) throws RemoteException
    {
        cs.connect(obj);
    }
 
    public void disconnect(String username) throws RemoteException
    {
        cs.disconnect(username);
    }
    
    
}
