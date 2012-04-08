/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author Syndarin
 */
public class MainScreen extends MIDlet {

    public void startApp() {
        Display.getDisplay(this).setCurrent(new LoginForm());
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
