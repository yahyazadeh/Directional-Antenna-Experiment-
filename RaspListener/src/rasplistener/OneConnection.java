/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rasplistener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Daniel
 */
public class OneConnection {

    Socket sock;
    BufferedReader in = null;
    DataOutputStream out = null;
    FileOutputStream fop = null;
    File file;

    OneConnection(Socket sock) throws Exception {
        this.sock = sock;
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        out = new DataOutputStream(sock.getOutputStream());
    }

    String getRequest(String filePath) throws Exception {
        file = new File(filePath);
        fop = new FileOutputStream(file);
        if (!file.exists()) {
            file.createNewFile();
        }
        String s = null;
        while ((s = in.readLine()) != null) {
            System.out.println("Log:: " + s);
            byte[] contentInBytes = s.getBytes();
            fop.write(contentInBytes);
        }
        fop.flush();
        fop.close();
        System.out.println("Log:: Finished. Your file in the path " + filePath + " is now ready.");
        return s;
    }
}