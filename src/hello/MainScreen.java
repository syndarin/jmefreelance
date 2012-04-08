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
        setLoginPage();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void setLoginPage(){
        Display.getDisplay(this).setCurrent(new LoginForm(this));
    }
    
    public void setActionPage(){
        Display.getDisplay(this).setCurrent(new ActionForm(this));
    }
}
