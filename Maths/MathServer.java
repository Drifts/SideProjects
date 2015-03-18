//========================================
// David Craig
// w1354193
// Bsc Computer Systems Engineering
//========================================

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class MathServer {

  //private static final int yes = 0;
    private static int yes2;

    public static void main(String[] args) throws Exception {
        // part 1: initialization
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        InetAddress IPAddressList;
        int portList = -1;

        // part 2: receive the greeting from clients
        System.out.println("Ready to receive connections at port " + serverSocket.getLocalPort());
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        String greeting = new String(receivePacket.getData());
        System.out.println("From Client: " + greeting);

        IPAddressList = receivePacket.getAddress();
        portList= receivePacket.getPort();

        // part 3: broadcast the question to all clients
        String question1 = "Q1: (A + B)*(A+B)" + " 1.A*A+B*B "+ " 2.A*A+A*B+B*B " + " 3.A*A+2*A*B+B*B \n";
        sendData = question1.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddressList, portList);
        serverSocket.send(sendPacket);

    // part 5: receive the age of client (B)
        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        String ageStr = new String(receivePacket.getData());

        try {
            yes2 = Integer.parseInt(ageStr);   //<<<----- WILL NEVER GET THE VALUE... LEAVING IT AS AN EXERCISE....

        } catch (NumberFormatException nfe) {
            yes2 = 0;
        }

        receivePacket.getAddress();
        receivePacket.getPort();

        // part 6: compute the score (C)
        double count= 0; 
        double no = 0;

        if (yes2 >= 1 ) count = 1;
        else 
            if (yes2 <= 0 ) no = 1;

        // part 7: send the score to client
        // ALSO FIXING SOME CODE HERE AS WELL....
        String rep = null;
        rep = no < count ? "Is a good kid" : "is a bad kid";
        rep += " Server Count: " + count;

        sendData = rep.getBytes();
        DatagramPacket sendPacket1 = new DatagramPacket(sendData, sendData.length, IPAddressList, portList);
        serverSocket.send(sendPacket1);
    }
}