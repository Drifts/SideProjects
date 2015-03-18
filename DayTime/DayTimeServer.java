// DayTimeServer.java
import java.util.*;
import java.rmi.*;

public class DayTimeServer
{
    public static void main( String[] args )
    {
        try {

            Naming.bind( "daytime", new DayTimeImpl() );

            System.out.println("Server waiting for requests...");

             try {
               Runtime.getRuntime().addShutdownHook(
                 // Insert an anonymous inner class that extends Thread
                 new Thread() {
                     public void run () {
                         try {
                             Naming.unbind("daytime");
                             System.out.println(
                             "Server has shut down successfully");
                         } catch (Exception e) {
                             System.out.println(
                             "Server could not unbind from registry - giving up");
                         }
                     }
                 }
               );
             } catch(RuntimeException e) {
                 System.out.println("Could not add a shutdown hook: " + 
                                                 e.getMessage());
             }
        }
        catch (StubNotFoundException e) {
            System.out.println("You forgot to generate the stubs with RMIC");
        }
        catch (ConnectException e) {
            System.out.println("Could not connect to registry. "+
                               "Is it running and on the right port?");
            System.exit(-1);
        }
        catch (ServerException e) {
            System.out.println("Registry reports a problem: ");
            System.out.println(
            "Maybe the registry cannot find the stub.  Did you set the classpath?");
            System.out.println(
            "You can avoid this if you start the registry in the same folder");
            System.out.println(
            "as the server's stub, or copy the stub to the folder the registry");
            System.out.println("was started in.");
            System.exit(-1);
        }
        catch (ServerError e) {
            System.out.println("Registry reports an error: ");
            System.out.println(
            "Maybe the registry cannot find the DayTime interface."+
             "  Did you set the classpath?");
            System.out.println("You can avoid this if you start the "+
                               "registry in the same folder");
            System.out.println("as the server's files, or copy the "+
                               "interface to the folder the registry");
            System.out.println("was started in.");
            System.exit(-1);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
