// DayTime.java
import java.util.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DayTime extends Remote
{
    public Date getDate() throws RemoteException;
}
