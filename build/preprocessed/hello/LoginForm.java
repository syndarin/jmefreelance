/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.*;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;

/**
 *
 * @author Syndarin
 */
public class LoginForm extends Form implements CommandListener {

    private final static String title = "Авторизуйтесь, пожалуйста";
    private MainScreen root;
    private TextField login;
    private TextField password;

    public LoginForm(MainScreen root) {
        super(title);
        this.root = root;

        login = new TextField("Login", "", 12, TextField.ANY);
        password = new TextField("Password", "", 12, TextField.ANY);

        append(login);
        append(password);

        setCommandListener(this);

        addCommand(new Command("Enter", Command.OK, 1));

        addCommand(new Command("Exit", Command.EXIT, 1));
    }

    public void commandAction(Command c, Displayable d) {
        int commandType = c.getCommandType();

        switch (commandType) {
            case Command.EXIT:
                root.destroyApp(true);
                break;
            case Command.OK:
                initAuth();
                break;
        }
    }

    private void initAuth() {
        String loginString = login.getString();
        String passwordString = password.getString();

        String[] nodes = {"type", "user", "pass"};
        String[] values = {"bonuslogin", loginString, passwordString};

        XMLHelper helper = new XMLHelper();
        String xml = helper.generateXML("request", nodes, values);
        
        try {
            sendPostRequest(xml);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

//    private void sendAuthRequest(String xml) {
//        HttpClient client = new DefaultHttpClient();
//        HttpPost request = new HttpPost("http://62.149.12.172:8084");
//        try {
//            StringEntity entity = new StringEntity(xml, "text/xml", "utf-8");
//            request.setEntity(entity);
//            HttpResponse response = client.execute(request);
//            HttpEntity answerEntity = response.getEntity();
//            String answer = EntityUtils.toString(answerEntity, "windows-1251");
//            System.out.println(answer);
//        } catch (UnsupportedEncodingException ex) {
//            ex.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void sendPostRequest(String xml) throws IOException {
        
        DataInputStream dis = null;
        DataOutputStream dos = null;

        //HttpConnection http = (HttpConnection) Connector.open("http://www.intercitypost.com", Connector.READ);
        HttpConnection http = (HttpConnection) Connector.open("http://62.149.12.172:8084", Connector.READ_WRITE);
        http.setRequestMethod(HttpConnection.GET);
        http.setRequestProperty("Content-Type", "text/xml");
        http.setRequestProperty("Content-length", "" + xml.getBytes().length);
        http.setRequestProperty("User-Agent", "MobileClient");

        dos = http.openDataOutputStream();
        byte[] body = xml.getBytes();
        for (int i = 0; i < body.length; i++) {
            dos.write(body[i]);
        }
        //dos.flush();

        System.out.println("data writed");
        System.out.println(""+http.getResponseCode());

        StringBuffer responseMessage = new StringBuffer();
        dis = http.openDataInputStream();
        int ch;
        while ((ch = dis.read()) != -1) {
            responseMessage.append((char) ch);
        }
        
        System.out.println(responseMessage.toString());

        dis.close();        
        dos.close();
        http.close();
    }

    private void sendRequest(String xml) throws IOException {
        HttpConnection http = (HttpConnection) Connector.open("http://intercitypost.com");
        //HttpConnection http = (HttpConnection) Connector.open("http://195.225.157.182:8090");
        http.setRequestMethod(HttpConnection.GET);
        http.setRequestProperty("Content-Type", "text/xml");
        http.setRequestProperty("Content-length", "" + xml.getBytes().length);
        http.setRequestProperty("User-Agent", "MobileClient");

        OutputStream out = http.openOutputStream();
        out.write(xml.getBytes());
        out.flush();

        if (http.getResponseCode() == HttpConnection.HTTP_OK) {
            System.out.println("200!");
            //System.out.println(http.getResponseMessage());
        } else {
            System.out.println(http.getResponseCode());
        }
//        InputStream is=http.openInputStream();
//        if(is!=null){
//            System.out.println("is != null");
//        }else{
//            System.out.println("is == null");
//        }
    }
}
