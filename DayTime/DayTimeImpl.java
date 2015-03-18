// DayTimeImpl.java

import java.util.Date;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
 * The DayTimeImpl class is compiled a second time
 * with rmic to obtain a stub class file and skeleton 
 * class file for use by the RMI communications system.
 *   rmic DayTimeImpl
 *
 * Produces DayTimeImpl_Stub.class and DayTimeImpl_Skel.class.
 * The stub is copied to the client along with DayTimeClient
 * class and DayTime interface class
 *
 * Make sure that the classpath is set for both the
 * rmic compiler and the rmiregistry if the system
 * throws class not found messages.
 *
 * a classpath for Win might look like this:
 *
 * set CLASSPATH=.;c:\JDK1.1.4\lib\classes.zip
 *
 * Run rmiregistry on the server, first:
 * UNIX  > rmiregistry &
 * Win > start rmiregistry
 *
 * Then run the server:
 * UNIX  > java DayTimeServer
 * Win > start java DayTimeServer
 *
 * Then run the client from another command window:
 * UNIX  > java DayTimeClient
 * Win > java DayTimeClient
 *
 * Ctrl-C kills the servers in Win.
 */

public class DayTimeImpl
    extends UnicastRemoteObject
    implements DayTime
{
    /**
     * A user defined constructor is required for RMI
     * because creating a remote object may cause an exception.
     * @exception java.rmi.RemoteException
     */
    public DayTimeImpl() throws RemoteException {}

    /**
     * This is the implementation of the interface
     * DayTime.  SuperDate is a homegrown class that extends
     * Date and is designed to test the autodownload facility of
     * RMI if the client receives an instance that it does not have
     * the code for yet.
     *
     * @exception java.rmi.RemoteException
     * @return a Date object with the current date and time.
     */
    public Date getDate() throws RemoteException
    {
        Date dt = new  MyDate();
        System.out.println(dt.toString());
        return dt;
    }
}

class MyDate extends Date
{
    final String EOL = System.getProperty("line.separator");
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("================");
        sb.append(EOL);
        sb.append("The current date is: ");
        sb.append(super.toString());
        sb.append(EOL);
        sb.append("Wow - that's cool");
        sb.append(EOL);
        sb.append("================");
        return sb.toString();
    }
}

