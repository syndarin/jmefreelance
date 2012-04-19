/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author Syndarin
 */
public class HttpWorker {
    
    public String sendRequest(String host, String xml) throws IOException{
        DataInputStream dis = null;
        DataOutputStream dos = null;

        HttpConnection http = (HttpConnection) Connector.open(host, Connector.READ_WRITE);
        http.setRequestMethod(HttpConnection.GET);
        http.setRequestProperty("Content-Type", "text/xml");
        http.setRequestProperty("Content-length", "" + xml.getBytes().length);
        http.setRequestProperty("User-Agent", "MobileClient");

        dos = http.openDataOutputStream();
        byte[] body = xml.getBytes();
        for (int i = 0; i < body.length; i++) {
            dos.write(body[i]);
        }

        StringBuffer responseMessage = new StringBuffer();
        dis = http.openDataInputStream();
        int ch;
        while ((ch = dis.read()) != -1) {
            responseMessage.append((char) ch);
        }

        dis.close();
        dos.close();
        http.close();

        return responseMessage.toString();
    }
    
}
