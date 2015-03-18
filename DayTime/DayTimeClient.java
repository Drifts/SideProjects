// DayTimeClient.java
import java.util.*;
import java.rmi.Naming;
//import java.rmi.RMISecurityManager;

public class DayTimeClient
{
    public static void main(String[] args)
    {
      if( args.length != 1 )
      {
          System.out.println("java DayTimeClient <host_url>");
          System.out.println("Example: java DayTimeClient "+
                             "rmi://tiger.wmin.ac.uk/daytime");
          System.exit(0);
      }

      try {
        //  System.setSecurityManager( new RMISecurityManager() );

 
          DayTime dt = (DayTime) Naming.lookup( args[0] );

 
          System.out.println( dt.getDate().toString() );
        }
        catch (Exception e) {
          System.out.println(""+e);
        }
    }
}
