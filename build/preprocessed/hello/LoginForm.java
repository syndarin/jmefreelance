/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.*;
import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;

/**
 *
 * @author Syndarin
 */
public class LoginForm extends Form implements CommandListener{

    private final static String title = "Авторизуйтесь, пожалуйста";
    
    private MainScreen root;
    
    private TextField login;
    private TextField password;

    public LoginForm(MainScreen root) {
        super(title);
        this.root=root;

        login=new TextField("Login", "", 12, TextField.ANY);
        password=new TextField("Password", "", 12, TextField.ANY);
        
        append(login);
        append(password);

        setCommandListener(this);

        addCommand(new Command("Enter", Command.OK, 1));
        
        addCommand(new Command("Exit", Command.EXIT, 1));
    }

    public void commandAction(Command c, Displayable d) {
        int commandType=c.getCommandType();
        
        switch(commandType){
            case Command.EXIT:
                root.destroyApp(true);
                break;
            case Command.OK:
                initAuth();
                break;
        }
    }
    
    private void initAuth(){
        String loginString=login.getString();
        String passwordString=password.getString();
        
        String[] nodes={"type", "user", "pass"};
        String[] values={"", loginString, passwordString};
        
        XMLHelper helper=new XMLHelper();
        String xml=helper.generateXML("request", nodes, values);
        
        try {
            sendRequest(xml);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void sendRequest(String xml) throws IOException{
        HttpConnection http=(HttpConnection)Connector.open("http://62.149.12.172:8084");
        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type", "text/xml");
        http.setRequestProperty("Content-length", ""+xml.getBytes().length);
        
        OutputStream out=http.openOutputStream();
        out.write(xml.getBytes());
        out.flush();
//        if(http.getResponseCode()==HttpConnection.HTTP_OK){
//            System.out.println(http.getResponseMessage());
//        }else{
//            System.out.println(http.getResponseCode());
//        }
//        InputStream is=http.openInputStream();
//        if(is!=null){
//            System.out.println("is != null");
//        }else{
//            System.out.println("is == null");
//        }
    }
}
