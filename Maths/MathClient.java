//========================================
// David Craig
// w1354193
// Bsc Computer Systems Engineering
//========================================

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MathClient {

    public static void main(String[] args) throws Exception {
        // part 1: initialization
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        System.out.print("What's the question? ");
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();
        System.out.println("Attempting to connect the server at port " + 9876);
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);

        System.out.println("Initial greeting sent... Waiting for response...");

        // part 2: receive the question from server
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String question = new String(receivePacket.getData());
        System.out.println("From Server:" + question);

        String yes2 = inFromUser.readLine();
        sendData = yes2.getBytes();
        DatagramPacket sendPacket1 = 
            new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket1);


        // part 4: get the price from server
        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String rep = new String(receivePacket.getData());
        System.out.println("the answer is " + rep);

        // part 4: close the socket
        clientSocket.close();

    } // main()
}