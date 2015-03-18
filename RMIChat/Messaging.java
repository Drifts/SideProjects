/**
 *
 * @author David Craig w1354193 | Bsc Computer Systems Engineering
 */


import java.rmi.*;
import java.util.*;
 
public interface Messaging extends Remote
{
    public void sendPublicMessage(String keyword, String username, String message) throws RemoteException;
    public ArrayList getClientList() throws RemoteException;
    public void connect(SerializedClass obj) throws RemoteException;
    public void disconnect(String username) throws RemoteException;
    
    
}