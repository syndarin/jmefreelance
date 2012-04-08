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
public class LoginForm extends Form implements CommandListener, ItemStateListener {

    private final static String title = "Авторизуйтесь, пожалуйста";

    public LoginForm() {
        super(title);

        // добавляем поле ввода
        append(new TextField("Login", "", 12, TextField.ANY));

        append(new TextField("Password", "", 12, TextField.ANY));


        setCommandListener(this);

        setItemStateListener(this);

        addCommand(new Command("Enter", Command.OK, 1));
        
        addCommand(new Command("Exit", Command.EXIT, 1));
    }

    public void commandAction(Command c, Displayable d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void itemStateChanged(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
