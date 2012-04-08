/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

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
        
        System.out.println(loginString+" "+passwordString);//************************************************
    }
}
