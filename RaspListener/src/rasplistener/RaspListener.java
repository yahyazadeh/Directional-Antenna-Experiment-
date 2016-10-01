/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rasplistener;

import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class RaspListener {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args[0].equals("-h")) {
            System.out.println("Usage: java -jar RaspListener.jar <PortNumber> <FilePath>");
            return;
        }

        final int myPort = Integer.valueOf(args[0]);
        ServerSocket ssock;
        try {
            ssock = new ServerSocket(myPort);
            System.out.println("Log:: Port " + myPort + " opened.");

            while (true) {
                Socket sock = ssock.accept();
                System.out.println("Log:: Someone has made socket connection.");

                SocketHelper sh = new SocketHelper(sock);
                sh.wirteToFile(args[1]);
                sock.close();
            }
        } catch (BindException ex) {
            System.out.println("Run using sudo!");
        }
        catch (Exception ex) {
            System.out.println("Exception Occured!");
        }
    }

}
